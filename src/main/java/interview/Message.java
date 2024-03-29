package interview;

/**
 * @author KCWang
 * @version 1.0
 * 消息中间件:
 *
 *  缺点：
 *      1.消息中间间宕机----整个服务不可用，可用性降低
 *      2、数据一致性---整个业务出处理变得复杂
 *      3、接口的幂等性---重复性消息
 *     总结：加入消息中间间会使得整个项目变得复杂
 *  消息存储：
 *     Topic 是一个逻辑上的概念，实际上 Message 是在每个 Broker 上以 Queue 的形式记录。
 *     1、消费者发送的 Message 会在 Broker 中的 Queue 队列中记录
 *     2、一个 Topic 的数据可能会存在多个 Broker 中
 *     3、一个 Broker 存在多个 Queue
 *  重复消费：平均分配算法，独占队列解决消费者重复消费消息队列的问题
 *  有序性问题:(分区、版本、是否真的需要顺讯能否根据获取种态去判断)
 *      发送时保持顺序意味着对于有顺序要求的消息，用户应该在同一个线程中采用同步的方式发送。存储保持和发送的顺序一致则要求在同一线程中被发送出来的消息A和B，
 *      存储时在空间上A一定在B之前。而消费保持和存储一致则要求消息A、B到达Consumer之后必须按照先A后B的顺序被处理。
 *      1、生产者
 *            多线程发送消息不能保证顺序性，用户应该在同一个线程中采用同步的方式发送，做唯一性队列的控制，保证有序
 *      2、broker
 *           做唯一性队列的控制，保证有序
 *           分区顺序）将消息放到一个队列里用一个线程发送，由于分配策略会分配到不同队列，消息顺序难以保证，做唯一性队列的控制，通过自定义算法（正常的取模或者平均分配算法难以保证）
 *           使得消息发送到同一个队列
 *           比如用某一个数字%到队列的长度。
 *      3.消费者
 *           消费时保证顺序的简单方式就是“什么都不做”，不对收到的消息的顺序进行调整，即只要一个分区的消息只由一个线程处理即可;当然，如果a、b在一个分区中，
 *           在收到消息后也可以将他们拆分到不同线程中处理，不过要权衡一下收益。
 *      4：让topic下所有队列中存储的消息都保存顺序性，几乎无法实现
 *  消息去重：
 *   产生原因：在网络抖动，服务器异常时出现消息重发，在broker服务器出现重复性的消息，导致业务重复执行
 *   解决方案：
 *      1、让生产者消除重复性消息----从理论上，可以解决但是比较复杂（x）
 *      2、RocketMQ自身考虑解决此问题---参考redis如果key相同只存储一份，目前mq不支持此功能，有些插件可使用
 *      3、消费者去解决，保证接口幂等性就可以
 *          通过惟一标识消息msgid去重,业务流水号
 *          数据库幂等去问题：cpu在多线程随机切换时，读到同样数据出现脏读现象
 *                  解决方案：单个消费者加同步锁，集群中加分布式锁，因为可能有多个Consumer去消费同一个消息
 *  延时消息异步解耦：下单完成--》订单超时服务异步获取mq查询修改订单状态--》支付完成
 *  事务消息：
 *  消息丢失：
 *      1、Producer生产者
 * 　　　　　　Producer生产者提供了三种发送消息的方式，分别是：同步发送、异步发送、单向发送
 * 　　　　　　解决方案：
 * 　　　　　　　　①：采用同步方式发送，send消息方法返回成功状态，就表示消息正常到达了存储端Broker
 * 　　　　　　　　②：如果send消息异常或者返回非成功状态，可以重试
 * 　　　　　　　　③：可以使用事务消息，RocketMQ的事务消息机制就是为了保证零丢失来设计的
 *      2、Broker保证消息不丢
 * 　　　　　　确保消息持久化到磁盘，即刷盘机制
 * 　　　　　　刷盘机制分同步刷盘和异步刷盘：
 * 　　　　　　　　①：生产者消息发过来时，只有持久化到磁盘，RocketMQ的存储端Broker才返回一个成功的ACK响应，这就是同步刷盘。它保证消息不丢失，但是影响了性能
 * 　　　　　　　　②：异步刷盘的话，只要消息写入PageCache缓存，就返回一个成功的ACK响应。这样提高了MQ的性能，但是如果这时候机器断电了，就会丢失消息
 *      3、Consumer
 *      消息消费者端使用同步消费机制​​
 *      在消息业务逻辑处理完成之后再给 Broker 响应，那么消费阶段消息就不会丢失。
 *
 *  高可用：集群化部署
 *  RabbitMq:
 *      特点：消息堆积万级别、实效us级、低延迟、适合中小型企业、主从架构部署快
 *      缺点：erlang语言开发，不好维护
 *  RocketMq:
 *      特点：消息堆积10万级别、实效ms级、主题Topic多，主题过多时吞吐量略微下降、适合业务的开发，分布式架构易扩展
 *      主题：Topic主题--逻辑分类，一个topic下对应多个队列
 *      生产者：groupA-->可以发送多个topic-->每个topic有多个队列
 *      分区：topic下的队列，分区较少时，生产的速度会大于消费的速度
 *      消费者：groupA-->多个topic-->多个队列
 *              消费者组：1：业务上分组，不同的业务可以消费同一个组下的消息
 *                      2：消费消息时候必须指定从哪个组消费
 *              负载均衡算法：
 *                  1、平均分配
 *                  2、环形平均分配
 *              分配策略
 *                  1、查询所在组
 *                  2、根据组做平均分配（逻辑上分配）
 *              问题：
 *                  1：GroupB可以消费GroupA组的消息，消费完A组的消息还是存在的，一个topic可以被多个Group消费，一个group只能监听一个topic
 *                  2：GroupA组两个消费者同时监听不同的topicA、topicB,GroupA中ConsumerA只监听topicA，GroupA中ConsumerB只监听topicB
 *                  这个场景会发生消息丢失现象，这是由于负载算法引起的，
 *                      因此在业务设计上一个组消费消息只能对应一个topic
 *  Kafka:
 *      特点：Topic从几十到几百时，吞吐量会有大幅度下降，适合做专一的场景比如说日志收集
 *
 *
 * @date 2023/7/5 下午9:17
 */
public class Message {

}
