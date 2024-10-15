package com.zjstudio.designpattern.ObserverPattern;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体的被观察对象
 */
@Component
public class ConcreteObservable implements Observable{
    private List<Observer> observers = new ArrayList<>();

    @Override
    public List<Observer> getObservers() {
        return observers;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(Observer observer:observers){
            observer.update();
        }
    }
}
