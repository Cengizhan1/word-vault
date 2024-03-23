package com.cengizhanyavuz.wordvault.config;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
@Component
public class DummyData {

    private final WordRepository wordRepository;

    public DummyData(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public void dummyWords() {
        String[] turkishWords = {"Merhaba", "Dünya", "Kitap", "Bilgisayar", "Öğrenmek", "Deneme", "Yazılım", "Geliştirici", "Araba", "Proje"};
        String[] englishTranslations = {"Hello", "World", "Book", "Computer", "Learn", "Trial", "Software", "Developer", "Car", "Project"};

        List<Word> words = new ArrayList<>();
        for (int i = 0; i < turkishWords.length; i++) {
            Word word = new Word();
            word.setTr(turkishWords[i]);
            word.setEn(englishTranslations[i]);
            words.add(word);
        }
        wordRepository.saveAll(words);
    }

    @Bean
    public CommandLineRunner commandLineRunnerMethod() {
        return args -> {
            if (wordRepository.count() == 0) {
                System.out.println("Dummy data is being created");
                log.info("Dummy data is being created");
                dummyWords();
            }
        };
    }
}
