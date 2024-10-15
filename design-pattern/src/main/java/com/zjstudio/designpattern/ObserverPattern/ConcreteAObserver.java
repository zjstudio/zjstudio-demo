package com.zjstudio.designpattern.ObserverPattern;

import org.springframework.stereotype.Component;

/**
 * 具体的观察者
 */
@Component
@ObserverAnnotation
public class ConcreteAObserver implements Observer{
    @Override
    public void update() {
        System.out.println("Observer A received update...");
    }
}
