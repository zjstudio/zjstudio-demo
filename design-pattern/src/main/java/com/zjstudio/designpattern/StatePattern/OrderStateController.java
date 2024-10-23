package com.zjstudio.designpattern.StatePattern;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/state/pattern/")
public class OrderStateController {
    @GetMapping("/order/handler/list")
    public Map<OrderState,OrderStateHandler> getOrderStateHandlerList(){
        return OrderProcessor.orderStateOrderStateHandlerMap;
    }

    @GetMapping("/order/placed")
    public void changeOrderPlaced(){
        OrderProcessor.orderStateOrderStateHandlerMap.get(OrderState.PLACED).handleOrder();
    }


    @GetMapping("/order/paid")
    public void changeOrderPaid(){
        OrderProcessor.orderStateOrderStateHandlerMap.get(OrderState.PAID).handleOrder();
    }
}
