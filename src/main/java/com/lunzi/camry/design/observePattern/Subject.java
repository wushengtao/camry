package com.lunzi.camry.design.observePattern;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lunzi on 2019/4/26 10:57 PM
 */
public abstract class Subject {
    public List<Obser> list=new LinkedList<>();
    public void attach(Obser obser){
        list.add(obser);
    }
    public void update(){
        list.forEach(obser -> obser.update());
    }
}
