package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.model.GlobalWord;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.GlobalWordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.TEST_WORD_COUNT;

@Service
public class GlobalWordService {
    private final GlobalWordRepository globalWordRepository;

    public GlobalWordService(GlobalWordRepository globalWordRepository) {
        this.globalWordRepository = globalWordRepository;
    }

    public List<GlobalWord> getGlobalWords() {
        return globalWordRepository.findRandomWords(TEST_WORD_COUNT);
    }

    public void saveAll(List<GlobalWord> words) {
        globalWordRepository.saveAll(words);
    }
}
