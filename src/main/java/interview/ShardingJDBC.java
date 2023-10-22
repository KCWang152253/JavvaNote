package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/24 下午4:02
 *
 *    多数据源的事务问题
 *
 *    三种方案优化2000w数据大表：
 *          1、数据表分区
 *          2、数据库分表
 *          3、冷热归档：比如：只需要展示近一周或一个月的数据。那么这种情况这一周喝一个月的数据我们称之为热数据，其余数据为冷数据。那
 */
public class ShardingJDBC {
}
