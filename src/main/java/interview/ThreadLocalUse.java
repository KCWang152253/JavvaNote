package interview;

import java.util.HashMap;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/24 下午1:45
 *
 *   ThreadLocal：简单来说就是，ThreadLocal是依附在Thread上的局部变量，可以实现线程间的隔离，线程内部的资源共享，ThreadLocal是线程私有的
 *                Thread内部维护了一个ThreadLocalMap的属性 key为ThreadLocal
 *
 *   问题：内存泄漏问题
 *      为什么Map的key要设置成弱引用呢？
 *      因为如果我们ThreadLocalMap中的ThreadLocal不设置成弱引用，设置成强引用的话，如果外界已经将所有引用ThreadLocal的地方设置为了null(也就是不再使用了)，
 *      但是我们的Map里的key还指向堆内存里的ThreadLocal呢，而我们又不能直接操控Map。
 *      并且这个线程始终在运行(比如说线程池复用连接)，那么久而久之，堆内存里的ThreadLocal就无法被回收，造成内存泄露
 *      而设计成弱引用的话，在每次GC时，发现没有其他强引用指向ThreadLocal了，便会将其回收
 *
 *      并发面试题：ThreadLocal会出现内存泄漏吗？
 *
 *              不怡当的使用 ThreadLocalQ会造成内存泄漏的问
 *              题。主要是因为线程的私有变量ThreadL.ocalMap里
 *              面的key是-
 *              一个弱引用。而弱引用的特性就是不管
 *              是否存在直接引用的关系，当成员变量ThreadL ocal
 *              没没有其他强号1用关系的时候，这个时候对象就会被
 *              GC回收。从而导致key会变为null，造成这块内存
 *              永远无法被访问，出现内存泄漏的问题。
 *              规避内存泄漏。的问题，
 *              有两个解决方案
 *              第一个是扩大成员变量ThreadLocal的作用域，避免
 *              被GC回收。
 *              第二个就是每次使用完ThreadLocal以后，就调用remove0方法去移除对应的数据。
 *              第一种解决方案虽然不会造成key为nul的现象，但
 *              是后续线程不在继续访问这个key，也就会导致这
 *              个内存一直占用不被释放，最后也会造成 内存溢
 *              出的问题。所以说最好的解決方式，是在实际使
 *              用完以后，调用remove0方法去移除这个数据。
 *
 *
 *      解决方案：
 *
 *      内存释放时机：
 *
 *      被动 GC 释放 key
 *      仅是让 key 的内存释放，关联 value 的内存并不会释放
 *
 *      懒惰被动释放 value
 *      get key 时，发现是 null key，则释放其value内存
 *      set key 时，会使用启发式扫描，清除临近的null key的value内存，启发次数与元素个数，是否发现null key有关
 *
 *      主动 remove 释放 key，value
 *      会同时释放 key，value 的内存，也会清除临近的 null key 的 value 内存
 *      推荐使用它，因为一般使用 ThreadLocal 时都把它作为静态变量（即强引用），因此无法被动依靠 GC 回收
 *
 *      ThreadLocal 的四个方法
 *
 *      void set(Object value)
 *      设置当前线程的线程局部变量的值。
 *
 *      public Object get()
 *      该方法返回当前线程所对应的线程局部变量。
 *
 *      public void remove()
 *      将当前线程局部变量的值删除，目的是为了减少内存的占用，该方法是JDK 5.0新增的方法。需要指出的是，当线程结束后，
 *      对应该线程的局部变量将自动被垃圾回收，所以显式调用该方法清除线程的局部变量并不是必须的操作，但它可以加快内存回收的速度。
 *
 *      protected Object initialValue()
 *      返回该线程局部变量的初始值，该方法是一个protected的方法，显然是为了让子类覆盖而设计的。这个方法是一个延迟调用方法，
 *      在线程第1次调用get()或set(Object)时才执行，并且仅执行1次。ThreadLocal中的缺省实现直接返回一个null。
 *
 */
public class ThreadLocalUse {

    private static ThreadLocal<HashMap<String,Object>> contextThreadLocal = ThreadLocal.withInitial(HashMap::new);

    public static void main(String[] args) {
        ThreadLocal threadLocal = new ThreadLocal();
       contextThreadLocal.get().put("key","value");
       contextThreadLocal.set(new HashMap<>());
       contextThreadLocal.remove();
    }
}
