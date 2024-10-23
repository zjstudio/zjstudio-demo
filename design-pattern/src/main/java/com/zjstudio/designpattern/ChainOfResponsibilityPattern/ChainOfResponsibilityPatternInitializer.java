package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChainOfResponsibilityPatternInitializer implements BeanPostProcessor, ApplicationContextAware {

    private List<DiscountHandler> discountHandlerList = new ArrayList<>();

    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof DiscountHandler) {
            DiscountHandler discountHandler = (DiscountHandler) bean;
            discountHandlerList.add(discountHandler);
            int order = discountHandler.getClass().getAnnotation(Order.class).value();
            System.out.println("系统初始化折扣处理器：【"+order+"】" + discountHandler.getClass());
        }
        return bean;
    }
}
