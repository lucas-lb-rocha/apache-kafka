package com.lucas.str_consumer.listeners;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StrConsumerListener {

    /* @KafkaListener é o jeito padrão de fazer a chamada, criamos uma interface de
    * nome StrConsumerCustomListener para criar o groupId, topic padrão como str-topic
    * e containerFactory como strContainerFactory.
     */
    //@KafkaListener(groupId = "group-1", topics = "str-topic", containerFactory = "strContainerFactory")
    @StrConsumerCustomListener(groupId = "group-1")
    public void create(String message){
        log.info("Create::Receive message {}", message);

    }

    // KafkaListener com um partition specifc no topicPartition
    //@KafkaListener(groupId = "group-1", topicPartitions = {@TopicPartition(topic = "str-topic", partitions = {"0"})}, topics = "str-topic", containerFactory = "strContainerFactory")
    @StrConsumerCustomListener(groupId = "group-1")
    public void createSpecificPartition(String message){
        log.info("CreateSpecificPartition::Receive message {}", message);
    }

    // Criando consumer para testar exceptions
    @SneakyThrows // para dizer que o metodo pode lançar uma exception
    @StrConsumerCustomListener(groupId = "group-1")
    public void testException(String message){
        log.info("testException::Receive message {}", message);
        throw new IllegalArgumentException("Argument invalid...");
    }


    @KafkaListener(groupId = "group-2", topics = "str-topic", containerFactory = "validMessageContainerFactory")
    public void testValidMessageContainerFactory(String message){
        log.info("TestValidMessageContainerFactory::Receive message {}", message);
    }
}
