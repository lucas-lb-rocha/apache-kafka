package com.lucas.json_consumer.listener;

import com.lucas.json_consumer.model.Payment;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static java.lang.Thread.sleep;

@Slf4j
@Component
public class JsonListener {

    // Apenas simulando uma validacao
    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "create-group", containerFactory = "JsonContainerFactory")
    public void antiFraud(@Payload Payment payment){
        log.info("Validating Fraud for payment {}", payment.toString());
        sleep(2000);
        log.info("Validated!");
        sleep(3000);
    }

    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "pdf-group", containerFactory = "JsonContainerFactory")
    public void pdfGenerator(@Payload Payment payment){
        log.info("Creating PDF for payment {}", payment.toString());
        sleep(2000);
        log.info("PDF created!");
        sleep(3000);
    }

    @SneakyThrows
    @KafkaListener(topics = "payment-topic", groupId = "email-group", containerFactory = "JsonContainerFactory")
    public void sendEmail(@Payload Payment payment){
        log.info("Sending email for payment {}", payment.toString());
        sleep(2000);
        log.info("Email sent!");
        sleep(3000);
    }
}
