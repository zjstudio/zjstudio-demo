package com.zjstudio.designpattern.ObserverPattern;

import org.springframework.stereotype.Component;

@Component
@ObserverAnnotation
public class ConcreteBObserver implements Observer{
    @Override
    public void update() {
        System.out.println("Observer B received update...");
    }
}
