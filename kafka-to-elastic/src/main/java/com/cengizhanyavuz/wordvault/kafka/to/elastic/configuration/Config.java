package com.cengizhanyavuz.wordvault.kafka.to.elastic.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableElasticsearchRepositories(basePackages = "com.cengizhanyavuz.wordvault.kafka.to.elastic.repo")
@ComponentScan("com.cengizhanyavuz.wordvault.kafka.to.elastic")
public class Config extends ElasticsearchConfiguration {
    private final ElasticConfigData elasticConfigData;

    public Config(ElasticConfigData configData) {
        this.elasticConfigData = configData;
    }

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(elasticConfigData.getConnectionUrl())
                .withConnectTimeout(elasticConfigData.getConnectTimeoutMs())
                .withSocketTimeout(elasticConfigData.getSocketTimeoutMs())
                .build();
    }
}
