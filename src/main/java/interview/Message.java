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
 *  重复消费：独占队列
 *  有序性问题
 *  RabbitMq:
 *      特点：消息堆积万级别、实效us级、低延迟、适合中小型企业、主从架构部署快
 *      缺点：erlang语言开发，不好维护
 *  RocketMq:
 *      特点：消息堆积10万级别、实效ms级、主题Topic多，主题过多时吞吐量略微下降、适合业务的开发，分布式架构易扩展
 *      主题：Topic主题--逻辑分类，一个topic下对应多个队列
 *      生产者：groupA-->多个topic-->多个队列
 *      分区：topic下的队列，分区较少时，生产的速度会大于消费的速度
 *      消费者：groupA-->多个topic-->多个队列
 *              消费者组：1：业务上分组，不同的业务可以消费同一个组下的消息
 *                      2：消费消息时候必须指定从哪个组消费
 *              问题：
 *                  1：GroupB可以消费GroupA组的消息
 *                  2：GroupA组两个消费者同时监听不同的topicA、topicB,这个场景会发生消息丢失现象，这是由于负载算法引起的，
 *                      因此在业务设计上一个组消费消息只能对应一个topic
 *  Kafka:
 *      特点：Topic从几十到几百时，吞吐量会有大幅度下降，适合做专一的场景比如说日志收集
 *
 *
 * @date 2023/7/5 下午9:17
 */
public class Message {

}
