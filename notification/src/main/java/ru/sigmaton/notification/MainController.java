package ru.sigmaton.notification;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transaction")
public class MainController {
    private final KafkaProducer kafkaProducer;

    public MainController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/new")
    public void createTransaction (@RequestBody TransactionDTO transactionDTO) throws InterruptedException {
        kafkaProducer.sendMessage(transactionDTO);
    }
}
