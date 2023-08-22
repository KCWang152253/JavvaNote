package interview;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.function.Supplier;

/**
 * @author KCWang
 * @version 1.0 线程的状态 创建->准备就绪->执行->阻塞->准备就绪->执行->死亡
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
        new ThreadPool().a(new ThreadPool()::t1);
    }



    private int t1(){
        for (int i = 0; i < 10; i++) {
            System.out.println(i+1);
        }
        return 1;
    }


    private int a(Supplier s){
        return (int) s.get();
    }
}
