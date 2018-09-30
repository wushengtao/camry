package com.lunzi.camry.mq.base;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;
import java.util.Map;

/**
 * Created by lunzi on 2018/9/19 上午9:13
 */
@Slf4j
public abstract class AbstractMQPushConsumer<T> extends AbstractMQConsumer<T> {
    @Getter
    @Setter
    private DefaultMQPushConsumer consumer;
    public AbstractMQPushConsumer(){}

    public abstract boolean process(T message,Map<String,Object> extMap);

    /**
     * 原生处理消息
     * @param list
     * @param consumeConcurrentlyContext
     * @return
     */
    public ConsumeConcurrentlyStatus dealMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext){
        for(MessageExt messageExt:list){
            log.debug("receive msgId: {}, tags : {}" , messageExt.getMsgId(), messageExt.getTags());
            T t=parseMessage(messageExt);
            Map<String,Object> ext=parseExtParam(messageExt);
            if(t!=null&&process(t,ext)){
                log.warn("consume fail , ask for re-consume , msgId: {}", messageExt.getMsgId());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

    public ConsumeOrderlyStatus dealMessage(List<MessageExt> list, ConsumeOrderlyContext consumeOrderlyContext) {
        for(MessageExt messageExt : list) {
            log.info("receive msgId: {}, tags : {}" , messageExt.getMsgId(), messageExt.getTags());
            T t = parseMessage(messageExt);
            Map<String, Object> ext = parseExtParam(messageExt);
            if( null != t && !process(t, ext)) {
                log.warn("consume fail , ask for re-consume , msgId: {}", messageExt.getMsgId());
                return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT;
            }
        }
        return  ConsumeOrderlyStatus.SUCCESS;
    }


}
