package interview;

/**
 * @author KCWang
 * @version 1.0 分布式锁
 *
 * 题记：分布式锁三种实现方式：
 * 基于数据库实现分布式锁；
 * 基于缓存（Redis等）实现分布式锁（AP）
 * 基于Zookeeper实现分布式锁（CP）。
 * 从性能角度（从高到低）来看：“缓存方式>Zookeeper方式>=数据库方式”。
 *
 * 说起分布式的概念，首当其冲就是CAP理论，即满足一致性（Consistency）、可用性（Availability）和分区容错性（Partition tolerance）。但是CAP理论告诉我们，任何系统只能满足其中两个。
 * 一、数据库实现分布式锁
 * 1. 悲观锁
 *
 * 利用select … where … for update 排他锁。阻塞的，容易锁表。
 *
 * 2. 乐观锁
 *
 * update version通过增加递增的版本号字段实现乐观锁。
 *
 * 3、 基于数据库表获取
 * 获取锁时，只要执行insert语句
 * insert into lock_table(“method_name”,“time”)；
 * 释放锁，delete
 * 当然还有其他许多问题需要解决，可重入、定时扫描过期时间等
 *
 * 二、Redis分布式锁
 * 1、简化版setnx命令：
 *
 * if (jedis.setnx(lockKey, val) == 1) { jedis.expire(lockKey, timeout);}
 * 很显然，加锁操作和后面的设置超时时间是分开的，并非原子操作。假如加锁成功，但是设置超时时间失败了，该lockKey就变成永不失效。
 *
 * 2、set(lockKey, requestId, "NX", "PX", expireTime)命令，该命令可以指定多个参数。
 *
 * String result = jedis.set(lockKey, requestId, "NX", "PX", expireTime);if ("OK".equals(result)) {return true;}return false;
 * 其中：
 * lockKey：锁的标识
 * requestId：请求id
 * NX：只在键不存在时，才对键进行设置操作。
 * PX：设置键的过期时间为 millisecond 毫秒。
 * expireTime：过期时间
 * 满足了原子性，但是每次都要达到了超时时间才释放锁，显然也不是很合理，那么如何手工释放锁？
 *
 * 3、finally中释放锁+全局唯一标识
 *
 * try{ String result = jedis.set(lockKey, requestId, "NX", "PX", expireTime); if ("OK".equals(result)) { return true; } return false; } finally { if (jedis.get(lockKey).equals(requestId)) { jedis.del(lockKey); return true; } return false; }
 * 无论代码执行成功或失败了，都需要释放锁。如果有异常了，到达超时时间，锁还是会被redis自动释放。
 * 而requestId是全局唯一的，保证了自己只能释放自己加的锁，不存在加锁和释放别人锁的情况。
 *
 * 4、Redis+Lua
 *
 * Redis+Lua，可以说是专门为解决原子问题而生。Lua专门整合原子操作。有了 Lua 的特性，Redis 才真正在分布式锁、秒杀等场景，有了用武之地。
 * 为什么要用Lua脚本呢？因为一段复杂的业务逻辑，可以通过封装在Lua脚本中发送给Redis，保证这段复杂业务逻辑执行的原子性。
 *
 * 5、看门狗
 * 如果锁达到了超时时间，但业务代码还没执行完怎么办？
 * 看门狗可以通过定时任务不断刷新锁的获取事件，从而在用户获取锁到释放锁期间保持一直持有锁。
 *
 * eg:我们可以使用TimerTask类，来实现自动续期的功能：获取锁之后，自动开启一个定时任务，每隔10秒钟，自动刷新一次过期时间。这种机制就是redisson框架中的watch dogTimer timer = new Timer(); timer.schedule(new TimerTask() { @Overridepublicvoidrun(Timeout timeout)throws Exception { //自动续期逻辑 } }, 10000, TimeUnit.MILLISECONDS);
 * 三、基于Zookeeper实现分布式锁
 * ZooKeeper是一个为分布式应用提供一致性服务的开源组件，它内部是一个分层的文件系统目录树结构，规定同一个目录下只能有一个唯一文件名。基于ZooKeeper实现分布式锁的步骤如下：
 *
 * （1）创建一个目录mylock；（2）线程A想获取锁就在mylock目录下创建临时顺序节点；（3）获取mylock目录下所有的子节点，然后获取比自己小的兄弟节点，如果不存在，则说明当前线程顺序号最小，获得锁；（4）线程B获取所有节点，判断自己不是最小节点，设置监听比自己次小的节点；（5）线程A处理完，删除自己的节点，线程B监听到变更事件，判断自己是不是最小的节点，如果是则获得锁。
 * 通常使用ZooKeeper的一个客户端Curator，Curator提供的InterProcessMutex是分布式锁的实现，acquire方法用于获取锁，release方法用于释放锁。
 *
 * 四、总结（优缺点）
 * 数据库锁：
 *
 * db操作性能较差，并且有锁表的风险
 * 非阻塞操作失败后，需要轮询，占用cpu资源;
 * redis分布式锁:
 *
 * 主从切换的情况下可能出现多客户端获取锁的情况；
 * Lua脚本在单机上具有原子性，主从同步时不具有原子性
 * 基于Zookeeper的分布式锁：
 *
 * 需要引入Zookeeper集群，比较重量级；
 * 具备高可用、可重入、阻塞锁特性，可解决失效死锁问题。
 * 因为需要频繁的创建和删除节点，性能上不如Redis方式。
 * 小结：
 * 如果你的实际业务场景，更需要的是保证数据一致性。那么请使用CP类型的分布式锁，比如：zookeeper
 * 如果你的实际业务场景，更需要的是保证数据高可用性。那么请使用AP类型的分布式锁，比如：redis；
 * 通常redis足够用了，通过最终一致性大部分即可满足需求了，强一致性实时数据还是要依赖数据库的事务。
 *
 * @date 2023/7/5 下午9:17
 */
public class LockAndDistributionLock {
}
