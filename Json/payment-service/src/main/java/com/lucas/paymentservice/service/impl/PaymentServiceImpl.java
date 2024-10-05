package com.lucas.paymentservice.service.impl;

import com.lucas.paymentservice.model.Payment;
import com.lucas.paymentservice.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@RequiredArgsConstructor
@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService {

    private final KafkaTemplate<String, Serializable> kafkaTemplate;

    @SneakyThrows
    @Override
    public void sendPayment(Payment payment) {
        log.info("Payment {} received.", payment.toString());
        Thread.sleep(1000);
        log.info("Sending payment... ");
        kafkaTemplate.send("payment-topic", payment);
    }
}
