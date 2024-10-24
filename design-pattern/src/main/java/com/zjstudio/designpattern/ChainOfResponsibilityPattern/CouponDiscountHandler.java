package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(200)
@Component
public class CouponDiscountHandler extends DiscountHandler{
    @Override
    public double applyDiscount(double price, int membershipLevel, List<Double> couponList, List<Double> promotionList) {
        if(!couponList.isEmpty()){
            for(double discountPrice : couponList){
                price = price * discountPrice;
            }
            System.out.println("执行优惠券折扣："+price);
        }
        if(nextHandler != null) {
            price = nextHandler.applyDiscount(price,membershipLevel,couponList,promotionList);
        }
        return price;
    }
}
