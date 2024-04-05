package com.cengizhanyavuz.wordvault.kafka.to.elastic.service;


import com.cengizhanyavuz.wordvault.kafka.to.elastic.repo.WordElasticRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService {


    private final WordElasticRepository wordRepository;

    public WordService(WordElasticRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

}
