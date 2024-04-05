package com.cengizhanyavuz.wordvault.kafka.to.elastic.repo;


import com.cengizhanyavuz.wordvault.kafka.to.elastic.model.Word;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface WordElasticRepository extends ElasticsearchRepository<Word,Integer> {
}