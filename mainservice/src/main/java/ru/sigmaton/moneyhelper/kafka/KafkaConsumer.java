package ru.sigmaton.moneyhelper.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.services.TransactionService;

import java.util.List;

@Slf4j
@Service
public class KafkaConsumer {

    private final TransactionService transactionService;

    public KafkaConsumer(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @KafkaListener(topics = "transaction_topic", groupId = "transactions_group")
    public void consume(List<String> transaction)
    {
        System.out.println("Got the new transaction: " + transaction.get(2));
    }
}
