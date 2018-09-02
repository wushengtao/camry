package com.lunzi.camry.design.listen.observer;

/**
 * 微信平台用户相当于观察者，时刻关注者公众好的更新
 * Created by lunzi on 2018/7/26 上午9:18
 */
public class WeChatUser implements Observers{
    private WeChatPlat weChatPlat;
    public WeChatUser(WeChatPlat weChatPlat){
        this.weChatPlat=weChatPlat;
    }
    @Override
    public void update() {
        System.out.println("收到了新的公众号更新");
    }
}
