package interview;

/**
 * @author KCWang
 * @version 1.0 sql优化
 * @date 2023/7/5 下午7:05
 *
 * sql 执行索引计划，关注列：type:system->const > eq_ref（主键或者惟一索引） >ref(使用的索引列) > ref_or_null >index_merge >range > index > ALL
 *                       key:实际使用的索引
 *                       Extra:Using temporary(使用临时表)、Using filesort(使用排序)、、Using index condition(使用索引下推)、Using index、Using where
 * 索引失效的场景：未遵循最左匹配原则
 *                      1、使用函数
 *                      2、计算操作
 *                      3、like %在左边
 *                      4、使用or
 *                      5、in 使用不当  in 在结果集 大于30%的时候索引失效
 *                      6、order by
 *                         如果order by 后的排序字段用的是驱动表的排序字段, 那么这个字段的索引是不失效的, 效率高.
 *                         如果order by 后的排序字段用的不是驱动表的字段 , 那么索引不会起作用, 数据库会将驱动表查出来的数据和其他表进行关联,
 *                         关联后的结果集创建一个临时表进行存储, 然后对这个临时表按非驱动表的列进行排序 ,这样sql效率会变慢.
 *       group by : Using temporary(使用临时表) Using filesort(使用排序)
 *                  优化策略：
 *                  方向1：既然它默认会排序，我们不给它排是不是就行啦，order by null 不用排序
 *                  方向2：group by 后面的字段加索引
 *                  方向3：尽量只使用内存临时表，如果预估数据量比较大，我们使用SQL_BIG_RESULT 这个提示直接用磁盘临时表
 *
 *
 *
 *
 *
 *
 */
public class SqlOptimize {
}
