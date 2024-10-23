package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import java.util.List;

/**
 * 抽象折扣处理类
 */
public abstract class DiscountHandler {

    protected DiscountHandler nextHandler;

    public void setNextHandler(DiscountHandler nextHandler){
        this.nextHandler = nextHandler;
    }

    public abstract double applyDiscount(double price, int membershipLevel, List<Double> couponList, List<Double> promotionList);
}
