package interview;

/**
 * @author KCWang
 * @version 1.0  性能分析
 * @date 2023/9/8 下午2:52
 *
 *   性能调优-调优路上的CPU、内存和I/O
 *      CPU：
 *      通过top命令，来观测CPU的性能
 *      通过负载，评估CPU任务执行的排队情况
 *      通过vmstat，看CPU的繁忙程度
 *          一些指标：
               us：用户态所占用的CPU百分比，即引用程序所耗费的CPU
               sy：内核态所占用的CPU百分比，需要配合vmstat命令，查看上下文切换是否频繁
               ni：高优先级应用所占用的CPU百分比
               wa：等待I/O设备所占用的CPU百分比，过高输入输出设备可能存在明显瓶颈
               hi：硬中断所占用的CPU百分比
               si：软中断所占用的CPU百分比
               st：在平常的服务器上这个值很少发生变动，因为它测量的是宿主机对虚拟机的影响，即虚拟机等待宿主机CPU的时间占比
               id：空闲CPU百分比，一般我们比较关注空闲CPU的百分比，它可以从整体上体现CPU的利用情况
               如果评估CPU的任务排队情况，就要通过负载来完成
               负载（load）-- 评估CPU任务执行的排队情况
 *             使用uptime命令查看负载情况
 *             要是看CPU的繁忙程度，通过vmstat命令来看：
 *             b（Uninterruptible Sleep），等待I/O，可能是读盘或者写盘动作比较多
 *             si/so，显示了交换分区的一些使用情况，交换分区对性能的影响比较大，需要格外关注
 *             cs，每秒钟上下文切换（Context Switch）的数量，如果上下文切换的过于频繁，需要考虑是否是进程或者线程数开的过多。
 *                 每个进程上下文切换的具体数量，通过查看proc下的status内存映射文件获取。
 *      内存：内存 共享机制，上下文策略，缓存读取和更新
 *      I/O：小对象传输、范围查询
 */
public class PerformanceAnalysis {}
