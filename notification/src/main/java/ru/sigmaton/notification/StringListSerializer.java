package ru.sigmaton.notification;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.List;

public class StringListSerializer implements Serializer<List<String>> {

    @Override
    public byte[] serialize(String topic, List<String> data) {
        if (data == null) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing list", e);
        }
    }
}
