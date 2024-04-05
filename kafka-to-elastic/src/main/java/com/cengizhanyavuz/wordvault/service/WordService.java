package com.cengizhanyavuz.wordvault.service;


import com.cengizhanyavuz.wordvault.repo.WordElasticRepository;
import org.springframework.stereotype.Service;

@Service
public class WordService {


    private final WordElasticRepository wordRepository;

    public WordService(WordElasticRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

}
