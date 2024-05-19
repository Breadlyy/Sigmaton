package ru.sigmaton.moneyhelper.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.List;

public class StringListDeserializer implements Deserializer<List<String>> {

    @Override
    public List<String> deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(data, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            throw new SerializationException("Error deserializing list", e);
        }
    }
}
