package com.zjstudio.designpattern.StatePattern;

import org.springframework.stereotype.Component;

@OrderStateAnnotation(orderState = OrderState.PAID)
@Component
public class PaidOrderStateHandler implements OrderStateHandler{
    @Override
    public void handleOrder() {
        System.out.println("已支付订单逻辑处理中。。。");
    }
}
