package com.cengizhanyavuz.wordvault;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.Gender;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.repository.UserRepository;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class WordVaultApplication {
	public static void main(String[] args) {
		SpringApplication.run(WordVaultApplication.class, args);
	}
}
