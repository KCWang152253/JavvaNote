package redis;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/15 下午7:39
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
 *
 *          Hash: 手机端的购物车
 *          数据结构 Map<string,Map<Object,Object>>
 *          hset key field value [field value ...]
 *
 *          List：微信公众号订阅的消息、商品评论列表
 *          数据结构：双端的链表结构
 *
 *          Set:抽奖、微信朋友圈点赞、qq、新浪微博等社交软件
 *          集合运算：差集 SDIFF 交集 SINTER 并集 SUNION
 *
 *          zset：排行榜
 *
 *          bitmap：是string的子类，不是字符串，而是按位扩容
 *          排序、分页、高并发、高性能：list（容易读到旧数据）、 zset（推荐）
 *          bitmap：签到、打卡、亿级数据的收集+统计、二值统计、
 *          底层编码：实质是二进制的ascii编码表对应 man ascii
 *          strlen:安装字节来统计，不是字符串长度而是字节数
 *
 *          痛点：存的进、取的快、多统计
 *          一亿位的bitmap 约占用12M的内存
 *
 *          名词：
 *              UV：Unique Visitor 独立访客，一般理解为客户端ip（去重）
 *              PV: Page View,页面浏览量 （不用去重）
 *              DAU：Daily Active User:日活跃用户量
 *              MAU：Monthly Active User:月活跃用户量
 *          hyperloglog：基数统计（去重 ）
 *
 * 缓存击穿：开始击中缓存redis，后面热点数据到了过期时间去访问mysql，打爆mysql
 * 缓存穿透：查询大量无用的key,缓存和mysql中都没有，数据库崩了
 */
public class Redis {
}
