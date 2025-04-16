package springboot;

/**
 * @author KCWang
 * @version 1.0
 * @date 2025/4/16 17:36
 */
public class SpringBoot {
    /**
     *
     *
     SpringBoot = spring + springmvc + tomcat + 其他场景的自动配置类

     支持的各种组件集成列表官网：  https://docs.spring.io/spring-boot/reference/using/build-systems.html

     运行原理：
         @SpringBootConfiguration  仅仅代表是一个配置类 会被配置相关后置处理器处: ConfigurationClassPostProcessor
         @EnableAutoConfiguration:
            @Import(AutoConfigurationImportSelector.class):
                Spring 家自己的 spi 机制 加载全场景的配置类
                List<String> configurations = SpringFactoriesLoader.loadFactoryNames(getSpringFactoriesLoaderFactoryClass()): 去类路径下加载 META-INF/spring.factories 的相关组件
                Enumeration<URL> urls = (classLoader != null ? classLoader.getResources(FACTORIES_RESOURCE_LOCATION) :ClassLoader.getSystemResources(FACTORIES_RESOURCE_LOCATION));

            @AutoConfigurationPackage:
                @Import(AutoConfigurationPackages.Registrar.class): 指定以后我们要扫描哪些包下的组件



     *
     *
     *
     *
     */
}
