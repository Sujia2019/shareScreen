package com.tute.wjl.serialize;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import org.objenesis.Objenesis;
import org.objenesis.ObjenesisStd;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProtostuffSerializer {
    private static final Objenesis OBJENESIS=new ObjenesisStd(true);
    private static Map<Class<?>, Schema<?>> cachedSchema= new ConcurrentHashMap<>();
    /**
     **序列化方法
     */
    public <T> byte[] serializer(T t) {
        Class<T> clazz= (Class<T>) t.getClass();
        LinkedBuffer buffer=LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        Schema<T> schema=getSchema(clazz);
        return ProtostuffIOUtil.toByteArray(t,schema,buffer);

    }
    /**
     **反序列化方法
     */
    public <T> T deSerializer(byte[] data,Class<T> clazz) {
        T message=OBJENESIS.newInstance(clazz);
        Schema<T> schema=getSchema(clazz);
        ProtostuffIOUtil.mergeFrom(data,message,schema);
        return message;

    }

    @SuppressWarnings("unchecked")
    private static <T> Schema<T> getSchema(Class<T> clazz){
        return (Schema<T>) cachedSchema.computeIfAbsent(clazz,RuntimeSchema::createFrom);
    }
}
