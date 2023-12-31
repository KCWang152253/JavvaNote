package interview;


import java.util.concurrent.*;


/**
 * @author KCWang
 * @version 1.0
 *
 *              线程终止：
 *                  终止线程的三种方法
 *                  1. 使用退出标志，使线程正常退出，也就是当run方法完成后线程终止。
 *                  2. 使用stop方法强行终止线程（这个方法不推荐使用，因为stop和suspend、resume一样，也可能发生不可预料的结果）。
 *                     线程中不推荐使用Thread.stop()，它会释放所有monitor。可能会导致数据不一致性。
 *                     其实stop方法天生就不安全，因为它在终止一个线程时会强制中断线程的执行，不管run方法是否执行完了，并且还会释放这个线程所持有的所有的锁对象。
 *                  3. 使用interrupt方法中断线程。
 *              线程的状态 创建->准备就绪->执行->阻塞->准备就绪->执行->死亡
 *              线程间的通信：Object中的wait、notify、notifyAll
 *              线程顺序调度、控制的方法：sleep、yield、join
 *              join；一个线程等待另一个线程（直到结束或者持续一段时间）才执行，那么谁等待谁？在哪个线程调用，哪个线程就会等待；调用的哪个Thread对象，就会等待哪个线程结束；
 *                   join 判断isAlive()->已经启动。但是未终止，start方法需要在join方法之前
 *              wait和sleep都会使线程进入阻塞状态，都是可中断方法，被中断后会抛出异常
 *              wait是Object的方法，sleep是Thread的方法
 *              wait会释放锁，sleep不会释放锁
 *              wait会持续阻塞，必须等待唤醒，sleep必然有超时，超时后自己醒来
 *              yield 只是让出时间片，而且让出后自己也参与竞争，时间片被哪个线程抢到不能控制
 *
 *              四、synchronized锁的底层实现
 *
 *              在理解锁实现原理之前先了解一下Java的对象头和Monitor，在JVM中，对象是分成三部分存在的：对象头、实例数据、对其填充
 *              实例数据和对其填充与synchronized无关，这里简单说一下（我也是阅读《深入理解Java虚拟机》学到的，读者可仔细阅读该书相关章节学习）。
 *              实例数据存放类的属性数据信息，包括父类的属性信息，如果是数组的实例部分还包括数组的长度，这部分内存按4字节对齐；对其填充不是必须部分，由于虚拟机要求对象起始地址必须是8字节的整数倍，对齐填充仅仅是为了使字节对齐。
 *              对象头是我们需要关注的重点，它是synchronized实现锁的基础，因为synchronized申请锁、上锁、释放锁都与对象头有关。
 *              对象头主要结构是由Mark Word 和 Class Metadata Address组成，其中Mark Word存储对象的hashCode、锁信息或分代年龄或GC标志等信息，Class
 *              Metadata Address是类型指针指向对象的类元数据，JVM通过该指针确定该对象是哪个类的实例。
 *              锁也分不同状态，JDK6之前只有两个状态：无锁、有锁（重量级锁），而在JDK6之后对synchronized进行了优化，新增了两种状态，总共就是四个状态：无锁状态、偏向锁、轻量级锁、重量级锁，
 *              其中无锁就是一种状态了。锁的类型和状态在对象头Mark Word中都有记录，在申请锁、锁升级等过程中JVM都需要读取对象的Mark Word数据。
 *              每一个锁都对应一个monitor对象，在HotSpot虚拟机中它是由ObjectMonitor实现的（C++实现）。每个对象都存在着一个monitor与之关
 *              联，对象与其monitor之间的关系有存在多种实现方式，如monitor可以与对象一起创建销毁或当线程试图获取对象锁时自动生成，但当一个monitor被某个线程持有后，它便处于锁定状态。
 *              ObjectMonitor中有两个队列_WaitSet和_EntryList，用来保存ObjectWaiter对象列表(每个等待锁的线程都会被封装ObjectWaiter对象)，_owner指向持有ObjectMonitor对象的线程，
 *              当多个线程同时访问一段同步代码时，首先会进入_EntryList 集合，当线程获取到对象的monitor 后进入 _Owner 区域并把monitor中的owner变量设置为当前线程同时monitor中的计数器count加1，
 *              若线程调用 wait() 方法，将释放当前持有的monitor，owner变量恢复为null，count自减1，同时该线程进入 WaitSe t集合中等待被唤醒。若当前线程执行完毕也将释放monitor(锁)并复位变量的值，
 *              以便其他线程进入获取monitor(锁)。monitor对象存在于每个Java对象的对象头中(存储的指针的指向)，synchronized锁便是通过这种方式获取锁的，也是为什么Java中任意对象可以作为锁的原因，
 *              同时也是notify/notifyAll/wait等方法存在于顶级对象Object中的原因(关于这点稍后还会进行分析)
 *
 *              5.1 锁膨胀
 *
 *              上面讲到锁有四种状态，并且会因实际情况进行膨胀升级，其膨胀方向是：无锁——>偏向锁(一个线程使用)——>轻量级锁（两个线程，但是无竞争关系）——>重量级锁（两个线程，有竞争关系），
 *              并且膨胀方向不可逆。
 *
 *              一、线程池执行流程分析：
 *
 *                  1、线程池中线程数小于corePoolSize时，新任务将创建一个新线程执行任务，不论此时线程池中存在空闲线程；
 *                  2、线程池中线程数达到corePoolSize时，新任务将被放入workQueue中，等待线程池中任务调度执行；
 *                  3、当workQueue已满，且maximumPoolSize>corePoolSize时，新任务会创建新线程执行任务；
 *                  4、当workQueue已满，且提交任务数超过maximumPoolSize，任务由RejectedExecutionHandler处理；
 *                  5、当线程池中线程数超过corePoolSize，且超过这部分的空闲时间达到keepAliveTime时，回收该线程；
 *                  6、如果设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize范围内的线程空闲时间达到keepAliveTime也将回收；
 *
 *              二、
 *              五、workQueue 工作队列
 *
 *                  新任务被提交后，会先进入到此工作队列中，任务调度时再从队列中取出任务。jdk中提供了四种工作队列：
 *
 *                  ①ArrayBlockingQueue
 *                  基于数组的有界阻塞队列，按FIFO排序。新任务进来后，会放到该队列的队尾，有界的数组可以防止资源耗尽问题。
 *                  当线程池中线程数量达到corePoolSize后，再有新任务进来，则会将任务放入该队列的队尾，等待被调度。如果队列已经是满的，
 *                  则创建一个新线程，如果线程数量已经达到maxPoolSize，则会执行拒绝策略。
 *
 *                  ②LinkedBlockingQuene
 *                  基于链表的无界阻塞队列（其实最大容量为Interger.MAX），
 *                  按照FIFO排序。由于该队列的近似无界性，当线程池中线程数量达到corePoolSize后，
 *                  再有新任务进来，会一直存入该队列，而基本不会去创建新线程直到maxPoolSize（很难达到Interger.MAX这个数），
 *                  因此使用该工作队列时，参数maxPoolSize其实是不起作用的。
 *
 *                  ③SynchronousQuene
 *                  一个不缓存任务的阻塞队列，生产者放入一个任务必须等到消费者取出这个任务。也就是说新任务进来时，不会缓存，
 *                  而是直接被调度执行该任务，如果没有可用线程，则创建新线程，如果线程数量达到maxPoolSize，则执行拒绝策略。
 *
 *                  ④PriorityBlockingQueue
 *                  具有优先级的无界阻塞队列，优先级通过参数Comparator实现。
 *
 * @date 2023/8/21 下午4:07
 */
public class ThreadPool {
    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
            }
        }).start();
        new Thread(new ThreadPool()::t1,"t2").start();
        //lambda一行的时候默认有返回值
        new ThreadPool().a(() ->{
            int a =1;
        });
        //new ThreadPool().a(() ->1);
        //接口抽象方法没有返回值，引用的方法可以有返回值也可以没有
        new ThreadPool().a(new ThreadPool()::t1);

        /**
         * 创建固定线程池
         *
         * 线程池不允许使用Executors去创建，而是通过ThreadPoolExecutor的方式，这样的处理方式让写的同学更加明确线程池的运行规则，规避资源耗尽的风险
         * LinkedBlockingQueue是一个用链表实现的有界阻塞队列，容量可以选择进行设置，不设置的话，Executors默认是一个无边界的阻塞队列，最大长度为Integer.MAX_VALUE。
         * 对于一个无边界队列来说，是可以不断的向队列中加入任务的，这种情况下就有可能因为任务过多而导致内存溢出问题
         *
         * 上面提到的问题主要体现在newFixedThreadPool和newSingleThreadExecutor两个工厂方法上，
         * 并不是说newCachedThreadPool和newScheduledThreadPool这两个方法就安全了，这两种方式创建的最大线程数可能是Integer.MAX_VALUE，而创建这么多线程，必然就有可能导致OOM。
         *
         */
        ExecutorService pool = Executors.newFixedThreadPool(3);
        //创建线程池
        ThreadPoolExecutor poo = new ThreadPoolExecutor(
                0,
                3,
                2L,
                TimeUnit.SECONDS,
                new SynchronousQueue<>());

        MyThread myThread = new MyThread();
        MyThread myThread1 = new MyThread();
        MyThread myThread2 = new MyThread();
        poo.execute(myThread);
        poo.execute(myThread1);
        poo.execute(myThread2);
        //待任务执行完成，关闭线程池
        poo.shutdown();
    }





    private int t1(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i+1);
        }
        return 1;
    }


    private int a(Runnable s){
        return 1;
    }
}
