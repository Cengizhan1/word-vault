package com.cengizhanyavuz.wordvault.dto.request.kafka;

import lombok.Data;

@Data
public class PropertyRequest {

    private String propertyKey;
    private String propertyValue;

}