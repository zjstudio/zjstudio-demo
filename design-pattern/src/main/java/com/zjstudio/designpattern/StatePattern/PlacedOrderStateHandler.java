package com.zjstudio.designpattern.StatePattern;

import org.springframework.stereotype.Component;

@OrderStateAnnotation(orderState = OrderState.PLACED)
@Component
public class PlacedOrderStateHandler implements OrderStateHandler{
    @Override
    public void handleOrder() {
        System.out.println("已下单逻辑处理中。。。");
    }
}
