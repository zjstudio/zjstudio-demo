package com.zjstudio.designpattern.ChainOfResponsibilityPattern;

import java.util.ArrayList;
import java.util.List;

public class ChainOfResponsibilityPatternDemo {
    public static void main(String[] args) {
        DiscountHandler membershipHandler = new MembershipDiscountHandler();
        DiscountHandler couponHandler = new CouponDiscountHandler();
        DiscountHandler promotionHandler = new PromotionDiscountHandler();

        membershipHandler.setNextHandler(couponHandler);
        couponHandler.setNextHandler(promotionHandler);

        List<Double> couponList = new ArrayList<>();
        couponList.add(0.9d);
        List<Double> promotionList = new ArrayList<>();
        promotionList.add(0.9d);

        double discountPrice = membershipHandler.applyDiscount(100, 1, couponList, promotionList);
        System.out.println("最终价格：" + discountPrice);
    }
}
