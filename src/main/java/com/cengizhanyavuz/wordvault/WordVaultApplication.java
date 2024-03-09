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
public class WordVaultApplication implements CommandLineRunner {

	private final UserRepository userRepository;
	private final WordRepository wordRepository;

	public WordVaultApplication(UserRepository userRepository, WordRepository wordRepository) {
		this.userRepository = userRepository;
		this.wordRepository = wordRepository;
	}


	public static void main(String[] args) {
		SpringApplication.run(WordVaultApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setName("Cengizhan");
		user.setSurname("Yavuz");
		user.setPassword("123");
		user.setElo(100);
		user.setAge(24);
		user.setGender(Gender.MALE);
		user.setUsername("cengizhanyavuz");
		userRepository.save(user);

		List<Word> words = new ArrayList<>();
		for (int i =0; i < 10; i++) {
			Word word = new Word();
			word.setUser(user);
			word.setTr("test"+i);
			word.setEn("test"+i);
			word.setIt("test"+i);
			word.setAlm("test"+i);
			words.add(word);
		}
		wordRepository.saveAll(words);

	}
}
