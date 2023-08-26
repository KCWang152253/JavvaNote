package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/12 下午5:37
 *
 *  不同的微服务框架的区别：大流量的时候更稳定、吞吐量更高
 *
 *
 *    微服务间的通信协议：
 *        目前主流的分布式服务框架使用两种传输协议。
 *          1）RPC协议：以HSF、Dubbo、Thrift、gRPC、Motan为代表的框架使用的协议。
 *          2）HTTP REST协议：以Spring Cloud为代表的框架使用的协议。
 *
 *    服务调用：OpenFegin、RestTemplate
 *            在使用restTemplate访问远程接口的时候，我们难以将接口管理起来，当接口变动的时候我们可能会修改多处。Spring Cloud 提供OpenFeign来解决这个问题。
 *
 *            OpenFeign是一种声明式、模板化的HTTP客户端。在Spring Cloud中使用OpenFeign，可以做到使用HTTP请求访问远程服务，就像调用本地方法一样的。
 *
 *    服务注册：consul、Nacos组件、sofa、Zookeeper、Eureka组件、
 *
 *    服务治理:限流、熔断、降级
 *
 *          限流：
 *             1、计数器算法（固定窗口）
 *                在一段时间间隔内（时间窗）处理请求的最大数量固定，超过部分不做处理。
 *                计数器算法的缺点
 *                在两个间隔之间，如果有密集的请求。则会导致单位时间内的实际请求超过阈值。
 *                例如，限制每分钟100个请求，在第一个1分钟时间窗里临界点的时候（比如 0:59时刻），
 *                瞬间来了 100 个请求，这时能够正常处理请求，然后在1:01时刻的时候，第二个1分钟时间窗里又来了100个请求，
 *                这时也能够正常处理这些请求。但在 3s 内，一共处理 200 个请求，但是时间跨度小于1分钟，可能会造成后端过载。
 *
 *                如何处理临界问题，可以通过滑动窗口、漏铜、令牌桶三种算法解决。
 *
 *             2、滑动窗口算法
 *                滑动窗口算法把间隔时间划分成更小的粒度，当更小粒度的时间间隔过去后，把过去的间隔请求数减掉，再补充一个空的时间间隔。
 *             3、漏桶算法
 *                请求直接进入到漏桶里，漏桶以一定的速度对请求进行放行，当请求数量递增，漏桶内的总请求量大于桶容量就会直接溢出，请求被拒绝
 *                缺点
 *                漏桶的出水速度是固定的，也就是请求放行速度是固定的，故漏桶不能有效应对突发流量，但是能起到平滑突发流量（整流）的作用。
 *             4、令牌桶算法
 *                令牌桶算法以一个设定的速率产生令牌并放入令牌桶，每次用户请求都得申请令牌，如果令牌不足，则拒绝请求。
 *                令牌的数量与时间和发放速率强相关，流逝的时间越长，往桶里加入令牌的越多，如果令牌发放速度比申请速度快，
 *                则令牌会放入令牌桶，直到占满整个令牌桶，令牌桶满了，多的令牌就直接丢弃。
 *            熔断：
 *            分布式系统中的熔断
 *            在分布式系统中，不同的服务互相依赖，某些服务需要依赖下游服务，下游服务不管是内部系统还是第三方服务，如果出现问题，
 *            我们还是盲目地持续地去请求，即使失败了多次，还是不断的去请求，去等待，就可能造成系统更严重的问题。
 *            1、等待增加了整个链路的请求时间。
 *            2、下游系统有问题，不断的请求导致下游系统有持续的访问压力难以恢复。
 *            3、等待时间过长造成上游服务线程阻塞，CPU被拉满等问题。
 *            4、可能导致服务雪崩。
 *            熔断的作用
 *            熔断模式可以防止应用程序不断地尝试请求下游可能超时和失败的服务，可以不必等待下游服务的修复而达到应用程序可执行的状态。
 *            熔断有三种状态：
 *            1.Closed：关闭状态
 *            所有请求都正常访问。
 *            2.Open：打开状态 所有请求都会被降级
 *            在关闭状态下，熔断器会对请求情况计数，当一定时间内失败请求百分比达到阈值，则触发熔断，断路器会完全打开。一般默认失败比例的阈值是50%，
 *            请求次数最少不低于20次。
 *            3.Half Open：半开状态 允许部分请求通过
 *            open状态不是永久的，在熔断器开启状态打开后会进入休眠时间（一般默认是5S）。随后断路器会自动进入半开状态。此时会释放部分请求通过，
 *            若这些请求都是健康的，则会完全关闭断路器，否则继续保持打开，再次进行休眠计时
 *            熔断器开源组件
 *            1、Hystrix
 *            2、Resilience4j
 *            3、Sentinel（阿里巴巴开源组件）
 *            回退机制：当请求失败、超时、被拒绝，或当断路器打开时，执⾏回退逻辑。回退逻辑由开发⼈员 ⾃⾏提供，例如返回⼀个缺省值。
 *
 *            降级：
 *            什么是降级？为什么要降级？
 *            降级最主要解决的是资源不足和访问量增加的矛盾，在有限的资源情况下，可以应对高并发大量请求。那么在有限的资源情况下，
 *            想要达到以上效果就需要对一些服务功能进行一些限制，放弃一些功能，保证整个系统能够平稳运行。
 *            降级的方式有哪些？
 *            1、将强一致性变成最终一致性，不需要强一致性的功能，可以通过消息队列进行削峰填谷，变为最终一致性达到应用程序想要的效果。
 *            2、停止访问一些次要功能，释放出更多资源。比如双十一不让退货等。
 *            3、简化功能流程，把一些复杂的流程简化。提高访问效率。
 *           自动降级的条件
 *           调用失败次数达到阈值
 *           请求响应超时时间达到阈值
 *           请求下游服务发生故障通过状态码
 *           流量达到阈值触发服务降级
 *
 *           降级开源组件：sentinel和Hystrix
 *
 *           总结一下，限流、熔断和降级经常组合出现，是在服务不同条件下进行的服务保护机制，同时限流可能触发降级，熔断又是服务降级的一种方式，所以相辅相成，互相关联。
 *
 */
public class MicroService {

}
