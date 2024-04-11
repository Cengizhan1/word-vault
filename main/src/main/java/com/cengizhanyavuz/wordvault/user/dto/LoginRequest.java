package com.cengizhanyavuz.wordvault.user.dto;

public record LoginRequest(
        String username,
        String password
){
}