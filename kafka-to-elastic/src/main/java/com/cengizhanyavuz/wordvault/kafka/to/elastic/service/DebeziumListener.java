package com.cengizhanyavuz.wordvault.kafka.to.elastic.service;


import com.cengizhanyavuz.wordvault.kafka.to.elastic.dto.WordListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebeziumListener {

    @KafkaListener(topics = "words-kafka.word-vault-db.words", containerFactory = "kafkaListenerDebezium", groupId = "word-vault-consumer-group")
    public void debeziumListener(@Payload(required = false) WordListener word) {
        try {
            log.info("Message received before: " + word.getPayload().getBefore());
            log.info("Message received after: " + word.getPayload().getAfter());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}