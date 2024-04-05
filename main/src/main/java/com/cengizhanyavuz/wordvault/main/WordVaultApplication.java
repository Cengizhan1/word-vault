package com.cengizhanyavuz.wordvault.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.cengizhanyavuz.wordvault")
public class WordVaultApplication {
	public static void main(String[] args) {
		SpringApplication.run(WordVaultApplication.class, args);
	}
}
