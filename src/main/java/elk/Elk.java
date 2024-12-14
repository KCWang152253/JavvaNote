package elk;

/**
 * @author KCWang
 * @version 1.0
 * @date 2024/12/12 上午10:29
 */
public class Elk {

    /**
     *

     elasticsearch:
        需要切换普通用户
        无文件操作权限：
         切换 root 用户下授权
         1.su root
         2.chown -R esuser:esuser /usr/local/elasticsearch-7.4.2
         3、启动   ./bin/elasticsearch

     logstash：
         启动失败：rm data/.lock
         启动：./bin/logstash -f config/logstash-sample.conf

     filebeat
        启动：sudo  ./filebeat -e -c filebeat.yml

     kibana：
         需要切换普通用户
         ./bin/kibana




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
     *
     *
     *
     *
     *
     *
     */
}
