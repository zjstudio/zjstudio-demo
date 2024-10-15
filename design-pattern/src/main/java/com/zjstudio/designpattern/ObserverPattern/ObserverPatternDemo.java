package com.zjstudio.designpattern.ObserverPattern;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

@Component
public class ObserverPatternDemo {

    @Resource
    private Observable observable;

    @PostConstruct
    public void updateAllObservers(){
        observable.notifyObservers();
    }
}
