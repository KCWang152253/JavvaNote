package prometheus;

/**
 * @author KCWang
 * @version 1.0
 * @date 2024/12/23 下午10:29
 */
public class Prometheus {
    /**
     *
     *
     *
     *
     *


     docker run --name prometheus -d -p 9090:9090 prom/prometheus:v2.37.5

     docker run --name prometheus -d -p 9090:9090 prom/prometheus  --config.file=/usr/local/prometheus/prometheus.yml


     docker run -d --name prometheus -p 9090:9090 -v  /usr/local/prometheus/prometheus.yml:/etc/prometheus/prometheus.yml  prom/prometheus:v2.37.5



     sudo  find . -name prometheus.yml

     对于需要自定义配置的部署，可以将主机上的自定义 prometheus.yml 文件挂载到容器中：

         docker run -d -p 3000:3000 --name=grafana -v D:/programCoding/Docker/grafana/storage:/var/lib/grafana grafana/grafana:9.2.13


         docker run -d -p 3000:3000 --name=grafana  grafana/grafana:9.2.13







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
