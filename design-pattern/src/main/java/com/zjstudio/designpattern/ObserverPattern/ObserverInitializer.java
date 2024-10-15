package com.zjstudio.designpattern.ObserverPattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 观察者初始化器
 * 用于在项目启动时，自动根据注解查找观察者并添加到容器中
 */
@Component
public class ObserverInitializer implements BeanPostProcessor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Observable) {
            Observable observable = (Observable) bean;
            Map<String, Object> beansWithAnnotation = applicationContext.getBeansWithAnnotation(ObserverAnnotation.class);
            for (Object observer : beansWithAnnotation.values()) {
                observable.addObserver((Observer) observer);
            }
        }
        return bean;
    }
}
