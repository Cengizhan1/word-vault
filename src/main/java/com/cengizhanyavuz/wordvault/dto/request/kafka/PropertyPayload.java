package com.cengizhanyavuz.wordvault.dto.request.kafka;

import lombok.Data;

@Data
public class PropertyPayload {

    private String op;
    private PropertyModel before;
    private PropertyModel after;
}