package com.cengizhanyavuz.wordvault.kafka.to.elastic.service;

import com.cengizhanyavuz.wordvault.kafka.to.elastic.model.Word;
import com.cengizhanyavuz.wordvault.kafka.to.elastic.repo.WordElasticRepository;
import org.springframework.stereotype.Service;

@Service
public class ElasticWordService {

    private final WordElasticRepository repository;

    public ElasticWordService(WordElasticRepository repository) {
        this.repository = repository;
    }

    public void saveWord(Word word) {
        repository.save(word);
    }

    public Iterable<Word> getWords(){
        return repository.findAll();
    }
}
