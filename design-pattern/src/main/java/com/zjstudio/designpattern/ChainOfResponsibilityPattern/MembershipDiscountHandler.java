package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(100)
@Component
public class MembershipDiscountHandler extends DiscountHandler{

    @Override
    public double applyDiscount(double price, int membershipLevel, List<Double> couponList, List<Double> promotionList) {
        if (membershipLevel > 0) {
            price = (1 - membershipLevel * 0.1) * price;
            System.out.println("执行会员等级折扣："+price);
        }
        if (nextHandler != null) {
            price = nextHandler.applyDiscount(price, membershipLevel, couponList, promotionList);
        }
        return price;
    }
}
