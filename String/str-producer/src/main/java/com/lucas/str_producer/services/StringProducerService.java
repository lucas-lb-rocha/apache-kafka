package com.lucas.str_producer.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
@Slf4j
public class StringProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(final String message) {
        CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send("str-topic", message);

        future.thenAccept(result -> {
            if(result != null){
                log.info("Message [{}] delivered in partition {} and offset {}.",
                        message,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            }
        }).exceptionally(ex -> {
            log.error("Unable to deliver message [{}]. {}", message, ex.getMessage());
            return null;
        });
    }
}
