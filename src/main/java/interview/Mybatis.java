package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/11/3 下午2:51
 *
     一级缓存小结

      MyBatis 一级缓存的生命周期和 SqlSession 一致。
      MyBatis 一级缓存内部设计简单，只是一个没有容量限定的 HashMap，在缓存的功能性上有所欠缺。
      MyBatis 的一级缓存最大范围是 SqlSession 内部，有多个 SqlSession 或者分布式的环境下，数据库写操作会引起脏数据，建议设定缓存级别为 Statement。

    二级缓存小结

        MyBatis 的二级缓存相对于一级缓存来说，实现了 SqlSession 之间缓存数据的共享，同时粒度更加的细，能够到 namespace 级别，通过 Cache 接口实现类不同的组合，
        对 Cache 的可控性也更强。
        MyBatis 在多表查询时，极大可能会出现脏数据，有设计上的缺陷，安全使用二级缓存的条件比较苛刻。
        在分布式环境下，由于默认的 MyBatis Cache 实现都是基于本地的，分布式环境下必然会出现读取到脏数据，
        需要使用集中式缓存将 MyBatis 的 Cache 接口实现，有一定的开发成本，直接使用 Redis、Memcached 等分布式缓存可能成本更低，安全性也更高。
    总结

        本文先是介绍了 MyBatis 的缓存，MyBatis 的缓存分为一、二级缓存，一级缓存是 SqlSession 级别的缓存，二级缓存是 Mapper 级别的缓存；
        然后从工作流程、应用试验以及源码层面分析了 MyBatis 的一、二级缓存机制；最后对 MyBatis 的一、二级缓存做了相应的小结。

        老周建议 MyBatis 的一级、二级缓存只作为 ORM 框架使用就行了，线上环境得关闭 MyBatis 的缓存机制。通过全文分析，不知道你有没有觉得 MyBatis 的缓存机制很鸡肋？

        一级缓存来说对于有多个 SqlSession 或者分布式的环境下，数据库写操作会引起脏数据以及对于增删改多的操作来说，清除一级缓存会很频繁，这会导致一级缓存形同虚设。

        二级缓存来说实现了 SqlSession 之间缓存数据的共享，除了跟一级缓存一样对于增删改多的操作来说，清除二级缓存会很频繁，这会导致二级缓存形同虚设；
        MyBatis 的二级缓存不适应用于映射文件中存在多表查询的情况，由于 MyBatis 的二级缓存是基于 namespace 的，多表查询语句所在的 namspace 无法感应到其他 namespace 中的语句对多表查询中涉及的表进行的修改，引发脏数据问题。虽然可以通过 Cache ref 来解决多表的问题，但这样做的后果是，缓存的粒度变粗了，多个 Mapper namespace 下的所有操作都会对缓存使用造成影响。

        综上，生产环境要关闭 MyBatis 的缓存机制。你可能会问，老周，你说生产环境不推荐用，那为啥很多面试官很喜欢问 MyBatis 的一级、二级缓存机制呢？
        那你把老周这篇丢给他就好了，最后你再反问面试官，你们生产环境有用 MyBatis 的一级、二级缓存机制吗？大多数的答案要么是没用或者它自己也不知道用没用就随便那几道题来面你。
        如果面试官回答生产环境用了的话，那你就把这些用的弊端跟面试官交流交流。

 *
 *
 *
 */
public class Mybatis {
}
