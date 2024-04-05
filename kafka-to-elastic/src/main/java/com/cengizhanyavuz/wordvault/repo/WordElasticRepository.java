package com.cengizhanyavuz.wordvault.repo;


import com.cengizhanyavuz.wordvault.model.Word;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WordElasticRepository extends ElasticsearchRepository<Word,Integer> {
}