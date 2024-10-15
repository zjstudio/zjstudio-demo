package com.zjstudio.designpattern.ObserverPattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/observer/pattern/")
public class ObserverPatternController {

    @Autowired
    private Observable observable;

    @GetMapping("/list")
    public List<String> getObserverList(){
        return observable.getObservers().stream().map(item->item.getClass().getName()).collect(Collectors.toList());
    }

    @GetMapping("/remove")
    public List<String> removeObserver(@RequestParam String observerClassName){
        Iterator<Observer> iterator = observable.getObservers().iterator();
        while (iterator.hasNext()) {
            Observer observer = iterator.next();
            if(observer.getClass().getName().equals(observerClassName)){
                iterator.remove();
            }
        }
        return this.getObserverList();
    }
}
