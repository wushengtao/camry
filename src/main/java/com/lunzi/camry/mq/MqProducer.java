package com.lunzi.camry.mq;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.List;

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
            for(int i=1;i<=1;i++){
                SendResult sendResult=defaultMQProducer.send(new Message("test", "order", "key" + i, ("订单" + i).getBytes()), new MessageQueueSelector() {
                    @Override
                    public MessageQueue select(List<MessageQueue> list, Message message, Object arg) {
                        Integer id=(Integer)arg;
                        int size=list.size();
                        int index=id%size;
                        return list.get(index);
                    }
                },1001);
                System.out.println("sendResult:"+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            defaultMQProducer.shutdown();
        }
    }



}
