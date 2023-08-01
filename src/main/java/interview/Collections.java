package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/8/1 下午5:37
 *
 * Hashmap:
 *  问题：
 *      1：1.7中扩容时，并发插入容易导致死循环问题
 *        解决方案：
 *        1.7后头插法（新旧表的指向发生变化）改成尾插法解决这个问题
 *        使用线程安全的容器来替代 HashMap，比如 ConcurrentHashMap 或 Hashtable，
 *        因为 ConcurrentHashMap 的性能远高于 Hashtable，
 *        因此推荐使用 ConcurrentHashMap 来替代 HashMap。
 *      2.数据覆盖问题
 *          解决方案：
 *          使用 ConcurrentHashMap
 *      3.无序性问题
 *          解决方案：
 *          需要将 HashMap 替换成 LinkedHashMap
 *      4.三种遍历方式
 *          第一种：遍历HashMap的entrySet键值对集合
 *          第二中：遍历keys
 *          第三种：遍历values
 */
public class Collections {
}
