package com.lunzi.camry.design.listen.observer;

import java.util.List;

/**
 * 抽象的被观察的接口
 * Created by lunzi on 2018/7/26 上午9:12
 */
public interface Observerable {
    void addObserver(Observers observers);
    void removeObserver(Observers observers);
    void notify(List<Observers> list);

}
