package com.zjstudio.designpattern.StatePattern;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class OrderProcessor implements BeanPostProcessor, ApplicationContextAware {
    public static Map<OrderState,OrderStateHandler> orderStateOrderStateHandlerMap = new HashMap<>();
    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof OrderStateHandler orderStateHandler) {
            OrderState orderState = bean.getClass().getAnnotation(OrderStateAnnotation.class).orderState();
            orderStateOrderStateHandlerMap.put(orderState, orderStateHandler);
            System.out.println("订单状态：【"+orderState+"】"+orderStateHandler);
        }
        return bean;
    }
}
