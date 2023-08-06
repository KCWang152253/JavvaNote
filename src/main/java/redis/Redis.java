package redis;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/15 下午7:39
 *
 *  是什么？干什么？ 解决了哪些问题？
 *
 *  亿级系统：存的进去，取的出来，能快速统计
 *
 *
 *   启动redis服务
 *       //方式一：使用brew帮助我们启动软件
 *       brew services start redis
 *       //方式二
 *       redis-server /usr/local/etc/redis.conf
 *       //执行以下命令
 *       redis-server
 *
 *  关闭redis服务
 *      redis-cli shutdown
 *      强行终止redis
 *      sudo pkill redis-server
     登录客户端
    [root@ecs-25376 root]# redis-cli -h 127.0.0.1 -p 6379

 *
 *  数据结构：
 *      redis 9种数据结构由5种最基本的数据结构（String、List、Hash、Set、Sorted Set（zset）） 加 bitmap、geohash、hyperloglog、streams 组成。
 *      命令查看： redis 客户端 help @类型名词
 *      应用场景：
 *          String：分布式锁 转发量、点赞量
 *          set key value [NX|XX] [GET] [EX seconds|PX milliseconds|EXAT unix-time-seconds|PXAT unix-time-milliseconds|KEEPTTL]
 *          EX:key在多少秒后过期
 *          PX:key在多少毫秒后过期
 *          NX: 当key不存在时，才创建key,效果等同于setnx
 *          XX: 当key存在时，覆盖key
 *          Incrby 命令：将 key 所储存的值加上给定的增量值（increment） 。lncrby age 11 说明：将key中储存的数字值增加指定步长11，
 *                      并返回增加后的值（只能用在整型，字符串啥的会报错）
 *          适配中小型厂，太多转发量会时redis崩溃
 *
 *          Hash: 手机端的购物车
 *          数据结构 Map<string,Map<Object,Object>>
 *          hset key field value [field value ...]
 *
 *          List：微信公众号订阅的消息、商品评论列表
 *          数据结构：双端的链表结构
 *          Rpush 命令：在列表中右边添加一个或多个值 rpush list a b c d e
 *          Lrange 命令：获取列表指定下标范围内的元素 lrange list 0 -1 下标0到-1是查询所有
 *          Lpop 命令：移出并获取列表的第一个元素 lpop list
 *
 *          Set:抽奖、微信朋友圈点赞、qq、新浪微博等社交软件
 *          集合运算：差集 SDIFF 交集 SINTER 并集 SUNION
 *          Scard 命令：获取集合的成员数 scard set
 *          Srandmember 命令：返回集合中一个或多个随机数 srandmember set 2
 *
 *          zset：排行榜
 *          Zadd 命令：向有序集合添加一个或多个成员，或者更新已存在成员的分数 zadd zset a 3500 b 4000
 *          Zincrby 命令：有序集合中对指定成员的分数加上增量 increment zincrby zset 300 peter 给peter分数加上300
 *
 *          mysql:300万-500万数据需要分库分表
 *          多维度的统计：聚合、排序、二值、基数统计（去重统计）
 *          bitmap：是string的子类，不是字符串，而是按位扩容,本质是数组
 *          排序、分页、高并发、高性能：list（容易读到旧数据）、 zset（推荐）
 *          bitmap：签到、打卡、亿级数据的收集+统计、二值统计、
 *          底层编码：实质是二进制的ascii编码表对应 man ascii
 *          strlen:安装字节来统计，不是字符串长度而是字节数
 *          缺点：亿级以上的去重统计承担不了 1亿需要12M = 10^8/8/1024/1024
 *          优点：亿级 结果是精确的
 *          使用场景：日活跃3000万月活9亿，这样的数据一条bitmap就可以记录
 *          命令：setbit getbit  BITCOUNT strlen bitop(连续统计)
 *
 *          痛点：存的进、取的快、多统计
 *          一亿位的bitmap 约占用12M的内存
 *          setbit
 *          格式：SETBIT key offset value
 *          getbit
 *          格式：GETBIT key offset
 *          bitcount
 *          格式：BITCOUNT key [start] [end]
 *              start 和 end 参数都可以使用负数值： -1 表示最后一个字节， -2 表示倒数第二个字节，以此类推。另外，
 *              对于不存在的 key 被当成是空字符串来处理，因此对一个不存在的 key 进行 BITCOUNT 操作，结果为 0 。
 *          bitpos
 *          格式：BITPOS key bit [start] [end]
 *          功能：返回 key 指定的 BitMap 中第一个值为指定值 bit(非 0 即 1) 的二进制位的位置。pos，即 position，位置。在默认情况下，
 *          命令将检测整个 BitMap，但用户也可以通过可选的 start 参数和 end 参数指定要检测的范围。
 *          bitmap支持的最大位数是2的32次方，使用512m可以存储42.9亿的字节信息
 *
 *          bitop
 *          格式：BITOP operation destkey key [key …]
 *          功能：对一个或多个 BitMap 字符串 key 进行二进制位操作，并将结果保存到 destkey 上。
 *          operation 可以是 AND 、 OR 、 NOT 、 XOR 这四种操作中的任意一种：
 *          BITOP AND destkey key [key …] ：对一个或多个 BitMap 执行按位与操作，并将结果保存到 destkey 。
 *          BITOP OR destkey key [key …] ：对一个或多个 BitMap 执行按位或操作，并将结果保存到 destkey 。
 *          BITOP XOR destkey key [key …] ：对一个或多个 BitMap 执行按位异或操作，并将结果保存到 destkey 。
 *          BITOP NOT destkey key ：对给定 BitMap 执行按位非操作，并将结果保存到 destkey 。
 *
 *          名词：
 *              UV：Unique Visitor 独立访客，一般理解为客户端ip（去重）
 *              PV: Page View,页面浏览量 （不用去重）
 *              DAU：Daily Active User:日活跃用户量
 *              MAU：Monthly Active User:月活跃用户量
 *          hyperloglog：去重复统计功能的基数估计算法（去重 ）通过牺牲准确率来换取空间，误差在0.81%是一种概率算法的实现
 *          对于不要求绝对准确率的场景下可以使用，概率算法不直接存储数据本身，适用于亿级流量，如访问淘宝网页的去重独立ip的数量
 *          redis之父：安蒂雷斯
 *          用bitmap统计一万个样本（亿级），大约需要内存117.1875G将近120G,可见亿级基数统计不适合用bitmap,但是bitmap统计是精确计算的
 *          优点：在Redis里面，每个HyperLogLog键只需花费12KB内存，就可以计算接近2^64个不同元素的基数，这和计算基数时，元素越多耗费内存越多的集合形成鲜明对比
 *               因为HyperLogLog只会根据输入的元素来计算基数，而不会储存输入元素本身，所以不像其他集合那样，返回输入的各个元素。
 *          面试题：
 *              为什么redis集群的最大槽数是16384个（crc算法）？
 *              节点不超过1000个
 *           redis上的hash结构，技术上没错，但是无法落地，按照天猫淘宝的体谅一个月60G,redis被干崩
 *           命令：
 *               添加：PFADD key [element [element ...]]
 *               统计：PFCOUNT key [key ...]
 *               合并：PFMERGE destkey sourcekey [sourcekey ...]
 *
 *           geohash：美团推荐距自己多少公里以内的酒店、单车、等商品店铺
 *           Redis GEO 操作方法有：
 *              geoadd：添加地理位置的坐标。
 *              geopos：获取地理位置的坐标。
 *              geodist：计算两个位置之间的距离。
 *              georadius：根据用户给定的经纬度坐标来获取指定范围内的地理位置集合。以给定的经纬度为中心， 返回键包含的位置元素当中， 与中心的距离不超过给定最大距离的所有位置元素。
 *              georadiusbymember：根据储存在位置集合里面的某个地点获取指定范围内的地理位置集合。
 *              geohash：返回一个或多个位置对象的 geohash 值。

 *
 *
 *
 *
 * 缓存击穿：开始击中缓存redis，后面热点数据到了过期时间去访问mysql，打爆mysql
 * 缓存穿透：查询大量无用的key,缓存和mysql中都没有，数据库崩了
 */
public class Redis {
}
