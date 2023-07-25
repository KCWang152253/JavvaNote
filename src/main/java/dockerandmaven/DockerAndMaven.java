package dockerandmaven;

/**
 * @author KCWang
 * @version 1.0
 * @date 2023/7/23 下午3:28
 *
 *
 * Maven路径配置
 * Maven home directory: /Users/kcwang/java/Maven/apache-maven-3.3.9
 * user settings file:/Users/kcwang/java/Maven/apache-maven-3.3.9/conf/settings.xml
 * local repository:/Users/kcwang/repository
 *
 * Docker基本命令
 *  #启动容器
 *  docker start rmqserver rmqbroker #停止删除容器
 *  docker stop rmqbroker rmqserver
 *  docker rm rmqbroker rmqserver
 *  macos命令行启动docker服务
 *      方法 1 : 通过 launchctl 查看 docker server, 记住docker server 名
 *          launchctl list | grep docker
 * 然后关闭和启动它
 *      launchctl stop com.docker.docker.2388 && launchctl start com.docker.docker.2388
 * Rocketmq:
 *      #拉取镜像
 *      docker pull foxiswho/rocketmq:server-4.3.2
 *      docker pull foxiswho/rocketmq:broker-4.3.2
 *
 *
 *      #第二种更简单的创建方式(上面那种创建方式，不是很好使)
        #创建broker-server
        docker run -di -p 9876:9876 --name=rmqserver \
        -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m -Xmn128m" \
        -e "JAVA_OPTS=-Duser.home=/opt" \
        foxiswho/rocketmq:server-4.3.2

        #创建broker
        docker run -di -p 10911:10911 -p 10909:10909 --name=rmqbroker -e "JAVA_OPTS=-Duser.home=/opt" -e "JAVA_OPT_EXT=-server -Xms128m -Xmx128m -Xmn128m" foxiswho/rocketmq:broker-4.3.2

        #配置broker容器的配置文件
        docker exec -it rmqbroker /bin/bash
        cd /etc/rocketmq/
        vi broker.conf

        #配置内容
        brokerIP1=172.17.0.3
        #内网IP
        namesrvAddr=192.168.66.66:9876
        brokerName=kkb-a
 *
 */
public class DockerAndMaven {
}
