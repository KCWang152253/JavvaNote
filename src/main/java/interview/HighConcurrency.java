package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/9 下午12:18
 *  解决并发
 *         1，通过事务，使得多个操作打包成一个事务，确保他们原子执行，
 *         2，乐观锁，在执行更新前确认当前操作的数据是否被其他操作更新 ，如果被更新，则操作失败，并进行重试，这样不会影响其他客户端的操作
 *         3，悲观锁，操作前加锁，会降低性能，造成阻塞
 *         4，使用集群，将数据分散在多个节点上，降低并发的概率
 *         5，使用lua命令
 *   Java并发的三种处理方式
 *      1. volatile修饰共享变量
 *      2. ThreadLocal操作共享数据
 *      3. synchronize锁操作共享变量
 *      最后总结：
 *      volatile 是为了保证资源被多个线程并发正确操作。（可见性，禁止重排序）
 *      ThreadLocal是为了保证资源不被多线程同时操作。（牺牲空间）
 *      synchronize是为了多线程下 同一时刻只有一个线程操作资源。（牺牲时间，保证原子性）

 *
 *
 *
 *
 */
public class HighConcurrency {
}
