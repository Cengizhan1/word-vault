package com.cengizhanyavuz.wordvault.config;

import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.repository.UserRepository;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
public class DummyData {

    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    public DummyData(WordRepository wordRepository, UserRepository userRepository) {
        this.wordRepository = wordRepository;
        this.userRepository = userRepository;
    }

    public void dummyWords(User user) {
        String[] turkishWords = {"Merhaba", "Dünya", "Kitap", "Bilgisayar", "Öğrenmek", "Deneme", "Yazılım", "Geliştirici", "Araba", "Proje"};
        String[] englishTranslations = {"Hello", "World", "Book", "Computer", "Learn", "Trial", "Software", "Developer", "Car", "Project"};

        List<Word> words = new ArrayList<>();
        for (int i = 0; i < turkishWords.length; i++) {
            Word word = new Word();
            word.setTr(turkishWords[i]);
            word.setEn(englishTranslations[i]);
            word.setUser(user);
            words.add(word);
        }
        wordRepository.saveAll(words);
    }


    public void run() {
        if (wordRepository.count() == 0) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = userRepository.findAll().get(0);
            dummyWords(user);
        }
    }
}
