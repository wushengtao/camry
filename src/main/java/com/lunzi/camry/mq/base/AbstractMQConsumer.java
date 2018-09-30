package com.lunzi.camry.mq.base;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.util.Assert;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by lunzi on 2018/9/18 下午7:41
 */
@Slf4j
public abstract class AbstractMQConsumer<T> {
    protected static Gson gson = new Gson();
    /**
     *  反序列化解析
     */
    protected T parseMessage(MessageExt message){
        if(message==null||message.getBody()==null){
            return null;
        }
        final Type type=this.getMessageType();
        if(type instanceof Class){
            try{
                T data=gson.fromJson(new String(message.getBody()),type);//转成对应的class
                return data;
            }catch (JsonSyntaxException e){
                log.error("parse message json fail : {}", e.getMessage());
            }
        }else {
            log.warn("Parse msg error. {}", message);
        }
        return null;
    }
    protected Map<String,Object> parseExtParam(MessageExt message){
        Map<String,Object> extMap=new HashMap<>();
        // parse message property
        extMap.put(MessageExtConst.PROPERTY_TOPIC, message.getTopic());
        extMap.putAll(message.getProperties());

        // parse messageExt property
        extMap.put(MessageExtConst.PROPERTY_EXT_BORN_HOST, message.getBornHost());
        extMap.put(MessageExtConst.PROPERTY_EXT_BORN_TIMESTAMP, message.getBornTimestamp());
        extMap.put(MessageExtConst.PROPERTY_EXT_COMMIT_LOG_OFFSET, message.getCommitLogOffset());
        extMap.put(MessageExtConst.PROPERTY_EXT_MSG_ID, message.getMsgId());
        extMap.put(MessageExtConst.PROPERTY_EXT_PREPARED_TRANSACTION_OFFSET, message.getPreparedTransactionOffset());
        extMap.put(MessageExtConst.PROPERTY_EXT_QUEUE_ID, message.getQueueId());
        extMap.put(MessageExtConst.PROPERTY_EXT_QUEUE_OFFSET, message.getQueueOffset());
        extMap.put(MessageExtConst.PROPERTY_EXT_RECONSUME_TIMES, message.getReconsumeTimes());
        extMap.put(MessageExtConst.PROPERTY_EXT_STORE_HOST, message.getStoreHost());
        extMap.put(MessageExtConst.PROPERTY_EXT_STORE_SIZE, message.getStoreSize());
        extMap.put(MessageExtConst.PROPERTY_EXT_STORE_TIMESTAMP, message.getStoreTimestamp());
        extMap.put(MessageExtConst.PROPERTY_EXT_SYS_FLAG, message.getSysFlag());
        extMap.put(MessageExtConst.PROPERTY_EXT_BODY_CRC, message.getBodyCRC());

        return extMap;
    }
    /**
     * 解析消息的类型
     * @return
     */
    protected Type getMessageType() {
        Type superType=this.getClass().getGenericSuperclass();
        if(superType instanceof ParameterizedType){
            ParameterizedType parameterizedType=(ParameterizedType)superType;
            Type[] actualTypeArguments=parameterizedType.getActualTypeArguments();
            Assert.isTrue(actualTypeArguments.length==1,"Number of type arguments must be 1");
            return actualTypeArguments[0];
        }else {
            return Object.class;
        }
    }

}
