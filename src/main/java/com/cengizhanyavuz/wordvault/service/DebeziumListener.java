package com.cengizhanyavuz.wordvault.service;


import com.cengizhanyavuz.wordvault.dto.WordDto;
import com.cengizhanyavuz.wordvault.dto.request.kafka.PropertyListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebeziumListener {

    @KafkaListener(topics = "words-kafka.word-vault-db.words", containerFactory = "kafkaListenerDebezium", groupId = "kafkaListenerDebezium")
    public void debeziumListener(@Payload(required = false) WordDto message) {
        try {
            log.info("Message received: " + message.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}