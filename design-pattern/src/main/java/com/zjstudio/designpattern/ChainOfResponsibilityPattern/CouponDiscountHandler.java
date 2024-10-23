package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import java.util.List;

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