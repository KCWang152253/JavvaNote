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
         @SpringBootConfiguration  仅仅代表是一个配置类 会被配置相关后置处理器处: ConfigurationClassPostProcessor 会处理 @Configuration @Bean @Import @PropertySource等
         @EnableAutoConfiguration:
            @Import(AutoConfigurationImportSelector.class):
                Spring 家自己的 spi 机制 加载全场景的配置类： process 方法的处理逻辑
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



     自动配置扩展点：
         扩展点一：
         看到这里我们也可以总结出spring其中的一个扩展点： 如果业务中需要根据条件动态的控制某个类的导入，则可以实现AutoConfigurationImportFilter接口，并且实现方法match，然后放入META-INF/spring.factories配置文件中Key为：org.springframework.context.ApplicationContextInitializer的value中。spring在导入配置类的时候，会自动执行这个扩展方法。 注：spring另外封装了一个类ConfigurationClassFilter来统一处理

         扩展点二：
         看到这里我们也可以总结出spring其中的一个扩展点： 如果业务中需要监控某个类的导入，则可以实现AutoConfigurationImportListener接口，并且实现方法onAutoConfigurationImportEvent，然后放入META-INF/spring.factories配置文件中Key为：org.springframework.context.ApplicationContextInitializer的value中。spring在导入配置类的时候，会自动执行这个扩展方法。


         扩展点三：
         如果业务需要在启动的时候自定义下容器的初始化动作，则可以实现ApplicationContextInitializer接口，并且实现方法initialize，然后放入META-INF/spring.factories配置文件中Key为：org.springframework.context.ApplicationContextInitializer的value中。



     AutoConfigurationImportEvent、AutoConfigurationImportFilter、AutoConfigurationImportListener 和 AutoConfigurationImportSelector 是 Spring Boot 自动配置机制中的关键组件，它们协同工作以支持应用的自动配置功能。这些组件允许开发者在 Spring Boot 应用启动过程中更精细地控制自动配置的行为。下面将分别详细解释这些组件的作用和工作原理。

     1. AutoConfigurationImportSelector
     AutoConfigurationImportSelector 是 Spring Boot 自动配置机制的核心。它负责根据项目的依赖和条件选择需要自动配置的类，并将它们作为 Bean 导入到 Spring 应用上下文中。

     在 Spring Boot 应用启动时，@SpringBootApplication 注解会触发自动配置过程。这个注解内部使用了 EnableAutoConfiguration，而 EnableAutoConfiguration 又引用了 AutoConfigurationImportSelector。AutoConfigurationImportSelector 通过扫描类路径和检查项目依赖来确定哪些自动配置类应该被导入。

     AutoConfigurationImportSelector 可以被定制和扩展，通过实现自己的选择器来添加或排除特定的自动配置类。这可以通过实现 AutoConfigurationImportSelector 接口或继承其现有的实现类（如 SpringBootApplicationImportSelector）来完成。

     2. AutoConfigurationImportFilter
     AutoConfigurationImportFilter 是一个过滤器接口，用于在自动配置导入过程中过滤不需要的自动配置类。它提供了一种机制来排除基于特定条件的自动配置。

     AutoConfigurationImportFilter 允许开发者根据自动配置类的元数据信息（如类名、包名等）来决定是否排除某个自动配置。通过实现 AutoConfigurationImportFilter 接口，并覆盖其 match 方法，你可以定义自己的过滤逻辑。

     过滤器可以通过 @SpringBootApplication 注解的 excludeFilters 属性进行配置，或者在编程方式构建 SpringApplication 时进行添加。这提供了一种灵活的方式来定制自动配置的行为，以满足特定项目的需求。

     3. AutoConfigurationImportListener
     AutoConfigurationImportListener 是一个监听器接口，用于在自动配置导入过程中执行自定义逻辑。当 AutoConfigurationImportSelector 选择并导入自动配置类时，会触发 AutoConfigurationImportEvent 事件。AutoConfigurationImportListener 可以注册为监听这个事件的监听器，以便在自动配置导入过程中执行特定的操作。

     通过实现 AutoConfigurationImportListener 接口，并覆盖其 onAutoConfigurationImported 方法，你可以监听自动配置类的导入事件，并执行诸如日志记录、条件检查或额外的配置设置等操作。这对于需要在自动配置之后执行某些操作的场景非常有用。

     4. AutoConfigurationImportEvent
     AutoConfigurationImportEvent 是一个事件类，用于在自动配置导入过程中发布事件。当 AutoConfigurationImportSelector 完成自动配置类的选择并准备将它们导入到 Spring 应用上下文时，会发布一个 AutoConfigurationImportEvent 事件。

     这个事件可以被 AutoConfigurationImportListener 监听器捕获并处理。通过监听这个事件，开发者可以在自动配置类被导入后执行自定义逻辑，比如进行额外的配置、注册自定义 Bean 或执行其他初始化任务。

     总结
     AutoConfigurationImportSelector、AutoConfigurationImportFilter、AutoConfigurationImportListener 和 AutoConfigurationImportEvent 共同构成了 Spring Boot 自动配置机制的核心组件。它们协同工作，使得开发者能够更精细地控制自动配置的行为，以满足项目的特定需求。通过定制这些组件，开发者可以排除不必要的自动配置、添加自定义的自动配置类或在自动配置导入过程中执行自定义逻辑。这些功能使得 Spring Boot 应用更加灵活和可定制，提高了开发效率和应用的稳定性。


     *
     *
     *
     *
     */
}
