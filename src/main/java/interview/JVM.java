package interview;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/17 下午2:09
 *
 * jvm内存模型:
 *      线程私有：虚拟机栈、本地方法栈、程序计数器
 *              栈：这是我们的代码运行空间，我们编写的每个方法都会放到栈里执行
 *              虚拟机栈：局部变量表、操作数栈、动态链接、方法出口等。
 *      线程共享（线程不安全）：堆区、方法区
 *              堆区：类的实例、数组，在Jvm启动时被创建
 *              方法区：常量、静态变量、类信息、运行时常量池、编译后代码等，操作的是直接内存
 *
 *
 */
public class JVM {
}
