package com.tute.wjl.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonSerializer{
    private static final ObjectMapper objMapper = new ObjectMapper();

    /**
     * bean、array、List、Map --> json
     *
     * @param obj
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> byte[] serializer(T obj) throws Exception {

        byte[] bytes;
        try {
            bytes = objMapper.writeValueAsBytes(obj);

        } catch (JsonProcessingException e) {
            throw new Exception(e);
        }
        return bytes;
    }

    /**
     * string --> bean、Map、List(array)
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> Object deSerializer(byte[] bytes, Class<T> clazz) {
        T obj = null;
        try {
            obj = objMapper.readValue(bytes, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
