package com.lunzi.camry.design.listen.observer;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by lunzi on 2018/7/26 上午9:15
 */
public class WeChatPlat implements Observerable {
    List<Observers> list=new LinkedList<>();
    @Override
    public void addObserver(Observers observers) {
        list.add(observers);
    }

    @Override
    public void removeObserver(Observers observers) {
        list.remove(observers);
    }

    @Override
    public void notify(List<Observers> list) {
        for(Observers observers:list){
            observers.update();
        }
    }
    public void pulishNew(){
        System.out.println("公众号更新了");
        notify(list);
    }
    public static void main(String agrs[]){
        WeChatPlat weChatPlat=new WeChatPlat();
        WeChatUser user1=new WeChatUser(weChatPlat);
        WeChatUser user2=new WeChatUser(weChatPlat);
        weChatPlat.addObserver(user1);
        weChatPlat.addObserver(user2);
        weChatPlat.pulishNew();

    }
}
