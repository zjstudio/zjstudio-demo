package com.zjstudio.designpattern.StatePattern;


import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderStateAnnotation {
    OrderState orderState();
}
