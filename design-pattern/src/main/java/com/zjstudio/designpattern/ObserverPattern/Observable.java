package com.zjstudio.designpattern.ObserverPattern;

import java.util.List;

/**
 * 被观察者接口
 */
public interface Observable {
    List<Observer> getObservers();
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}
