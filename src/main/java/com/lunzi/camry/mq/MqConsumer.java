package com.lunzi.camry.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Created by lunzi on 2018/8/11 下午7:51
 */
public class MqConsumer {
    private static String consumerGroup = "consumer01";
    private static String namesrvAdd = "localhost:9876";

    public static void main(String arg[]) {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);
        consumer.setNamesrvAddr(namesrvAdd);
        try {
            consumer.subscribe("test", "order");
            consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
            //多线程不保证顺序消费
//            consumer.registerMessageListener((MessageListenerConcurrently) (list, context) -> {
//                for(MessageExt messageExt:list){
//                    System.out.println("messageExt:"+messageExt);
//                    String messageBody = null;
//                    try {
//                        messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
//                    } catch (UnsupportedEncodingException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);//输出消息内容
//                }
//                try{
//                    Thread.sleep(2000);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            });
            //保证顺序消费
            consumer.registerMessageListener((MessageListenerOrderly) (list, context) -> {
                context.setAutoCommit(true);
                for (MessageExt messageExt : list) {
                    System.out.println("messageExt:" + messageExt);
                    String messageBody = null;
                    try {
                        messageBody = new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    System.out.println("消费响应：msgId : " + messageExt.getMsgId() + ",  msgBody : " + messageBody);//输出消息内容
                }
                return ConsumeOrderlyStatus.SUCCESS;
            });
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
