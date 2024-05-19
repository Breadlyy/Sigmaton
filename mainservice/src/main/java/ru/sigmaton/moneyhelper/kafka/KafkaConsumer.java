package ru.sigmaton.moneyhelper.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.sigmaton.moneyhelper.model.Account;
import ru.sigmaton.moneyhelper.model.Transaction;
import ru.sigmaton.moneyhelper.services.AccountDetailsService;
import ru.sigmaton.moneyhelper.services.TransactionService;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaConsumer {

    private final AccountDetailsService accountDetailsService;

    @KafkaListener(topics = "transaction_topic", groupId = "transactions_group")
    public void consume(List<String> transaction)
    {
        System.out.println("Got the new transaction: " + transaction.get(2));
        accountDetailsService.createNewTransaction(transaction);
    }
}
