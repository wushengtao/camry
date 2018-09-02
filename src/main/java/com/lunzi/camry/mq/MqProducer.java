package com.lunzi.camry.mq;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * Created by lunzi on 2018/8/11 下午7:27
 */
public class MqProducer {
    private static String producerGroup="producer01";
    private static String namesrvAdd="localhost:9876";

    public static void main(String[] args) {
        DefaultMQProducer defaultMQProducer=new DefaultMQProducer(producerGroup);
        defaultMQProducer.setNamesrvAddr(namesrvAdd);
        try {
            defaultMQProducer.start();
            Message message=new Message("test","push","send Message0812".getBytes(RemotingHelper.DEFAULT_CHARSET));
            for(int i=0;i<5;i++){
                SendResult sendResult=defaultMQProducer.send(message);
                System.out.println("sendResult:msgId:"+sendResult.getMsgId()+"sendStatus:"+sendResult.getSendStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            defaultMQProducer.shutdown();
        }
    }



}
