//package com.syriana.sso.oidc.api.config.oauth2.jackson;
//
//import com.alibaba.fastjson.util.IOUtils;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.google.common.base.Preconditions;
//import com.syriana.sso.oidc.api.config.redis.MyGenericJackson2JsonRedisSerializer;
//import org.codehaus.jackson.map.JsonDeserializer;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.springframework.data.redis.serializer.SerializationException;
//import org.springframework.security.oauth2.provider.OAuth2Authentication;
//import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStoreSerializationStrategy;
//
//import java.nio.charset.Charset;
//
//public class JacksonRedisTokenStoreSerializationStrategy<T extends OAuth2Authentication> implements RedisTokenStoreSerializationStrategy {
//
//    protected static ObjectMapper mapper = new ObjectMapper();
//
//    public void init(T t){
//        JsonDeserializer<OAuth2Authentication> oAuth2AuthenticationJackson2Deserializer = new OAuth2AuthenticationJackson2Deserializer(t.getClass());
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(t.getClass(), oAuth2AuthenticationJackson2Deserializer);
//        mapper.registerModule(module);
//    }
//
//    @Override
//    public <T> T deserialize(byte[] bytes, Class<T> clazz) {
//        Preconditions.checkArgument(clazz != null,
//                "clazz can't be null");
//        if (bytes == null || bytes.length == 0) {
//            return null;
//        }
//
//        try {
//            return mapper.readValue(new String(bytes, IOUtils.UTF8), clazz);
//        } catch (Exception ex) {
//            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
//        }
//    }
//
//    @Override
//    public String deserializeString(byte[] bytes) {
//        if (bytes == null || bytes.length == 0) {
//            return null;
//        }
//        return new String(bytes, IOUtils.UTF8);
//    }
//
//    @Override
//    public byte[] serialize(Object object) {
//        if (object == null) {
//            return new byte[0];
//        }
//
//        try {
//            return mapper.writeValueAsBytes(object);
//        } catch (Exception ex) {
//            throw new SerializationException("Could not serialize: " + ex.getMessage(), ex);
//        }
//    }
//
//    @Override
//    public byte[] serialize(String data) {
//        if (data == null || data.length() == 0) {
//            return new byte[0];
//        }
//
//        return data.getBytes(Charset.forName("utf-8"));
//    }
//}
