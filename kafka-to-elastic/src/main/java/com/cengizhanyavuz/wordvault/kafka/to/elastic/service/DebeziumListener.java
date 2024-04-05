package com.cengizhanyavuz.wordvault.kafka.to.elastic.service;


import com.cengizhanyavuz.wordvault.kafka.to.elastic.dto.WordListener;
import com.cengizhanyavuz.wordvault.kafka.to.elastic.model.Word;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DebeziumListener {

    private final ElasticWordService elasticWordService;

    public DebeziumListener(ElasticWordService elasticWordService) {
        this.elasticWordService = elasticWordService;
    }

    @KafkaListener(topics = "words-kafka.word-vault-db.words", containerFactory = "kafkaListenerDebezium", groupId = "word-vault-consumer-group")
    public void debeziumListener(@Payload(required = false) WordListener wordListener) {
        try {
            Word word = wordListener.getPayload().getAfter();
            log.info("Message received : " + word);
            elasticWordService.saveWord(word);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}