package cc.wangzijie.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringHelper {

    private static ConfigurableApplicationContext APPLICATION_CONTEXT;


    public static void setApplicationContext(ConfigurableApplicationContext applicationContext) {
        APPLICATION_CONTEXT = applicationContext;
    }

    public static ConfigurableApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }


    public static void publishEvent(ApplicationEvent event) {
        APPLICATION_CONTEXT.publishEvent(event);
    }

    public static <T> T getBean(Class<T> clazz) {
        return APPLICATION_CONTEXT.getBean(clazz);
    }

    public static <T> T getBean(String name, Class<T> clazz) {
        return APPLICATION_CONTEXT.getBean(name, clazz);
    }

    public static void close() {
        APPLICATION_CONTEXT.close();
    }
}
