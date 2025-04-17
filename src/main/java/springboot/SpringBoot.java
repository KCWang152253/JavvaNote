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


     加载tomcat  ServletWebServerFactoryAutoConfiguration 	ServletWebServerFactoryConfiguration.EmbeddedTomcat.class  和 springmvc DispatcherServletAutoConfiguration
     @AutoConfiguration(after = ServletWebServerFactoryAutoConfiguration.class)
     springboot 底层使用的ioc容器：  AnnotationConfigServletWebServerApplicationContext
                public class AnnotationConfigServletWebServerApplicationContext extends ServletWebServerApplicationContext（实现onFresh 加载tomcat）

     TomcatServletWebServerFactory: 会根据initializers 加载 DispatcherServlet  RegistrationBean -》 onStartup-〉 register(description, servletContext)  注册
         ServletContextInitializer[] initializersToUse = mergeInitializers(initializers);
         host.addChild(context);
         configureContext(context, initializersToUse);

     DispatcherServletRegistrationBean （可以自定义组件）就是  ServletContextInitializer

     FilterRegistrationBean 也可以注册原生的servlet 组件






     *
     *
     *
     *
     */
}
