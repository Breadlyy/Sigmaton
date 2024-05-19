package ru.sigmaton.notification;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class KafkaProducer {
    private KafkaTemplate<String, List<String>> kafkaTemplate;
    private String topic = "transaction_topic";
    public KafkaProducer(KafkaTemplate<String, List<String>> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void sendMessage(TransactionDTO transactionDTO) throws InterruptedException {
        List<String> transaction = List.of(transactionDTO.getName(),
                transactionDTO.getTimestamp().toString(),
                transactionDTO.getAmount().toString(),
                transactionDTO.getCategoryName(),
                transactionDTO.getAccountId());
        kafkaTemplate.send(topic, transaction);

    }
}
