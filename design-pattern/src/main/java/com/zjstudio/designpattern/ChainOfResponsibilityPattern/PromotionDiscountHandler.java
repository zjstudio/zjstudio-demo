package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(300)
@Component
public class PromotionDiscountHandler extends DiscountHandler{
    @Override
    public double applyDiscount(double price, int membershipLevel, List<Double> couponList, List<Double> promotionList) {
        if(!promotionList.isEmpty()){
            for(double discountPrice : promotionList){
                price = discountPrice * price;
            }
            System.out.println("执行促销折扣:"+price);
        }
        if(nextHandler != null) {
            price = nextHandler.applyDiscount(price,membershipLevel,couponList,promotionList);
        }
        return price;
    }
}
