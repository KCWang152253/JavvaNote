package redis;

/**
 * @author KCWang
 * @version 1.0
 * @date 2024/12/17 下午10:03
 */
public class 启动安装 {

    /**
     *
     *
     *
     *
     *
     *


     启动redis服务:
         //方式一：使用brew帮助我们启动软件
         brew services start redis
         //方式二
         redis-server /usr/local/etc/redis.conf

         //执行以下命令
         redis-server


     查看redis服务进程:
        ps axu | grep redis


     redis-cli连接redis服务:
        redis-cli -h 127.0.0.1 -p 6379

     启动 redis 客户端，打开终端并输入命令 redis-cli。该命令会连接本地的 redis 服务:
         $redis-cli
         redis 127.0.0.1:6379>
         redis 127.0.0.1:6379> PING
         PONG


     关闭redis服务:
        redis-cli shutdown


     强行终止redis:
        sudo pkill redis-server


     redis.conf 配置文件详解:
        redis默认是前台启动，如果我们想以守护进程的方式运行（后台运行），可以在redis.conf中将daemonize no,修改成yes即可。













     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
