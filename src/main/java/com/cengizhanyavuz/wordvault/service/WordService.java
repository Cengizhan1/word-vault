package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.WordDto;
import com.cengizhanyavuz.wordvault.dto.request.WordCreateRequest;
import com.cengizhanyavuz.wordvault.dto.request.WordUpdateRequest;
import com.cengizhanyavuz.wordvault.exception.WordNotFoundException;
import com.cengizhanyavuz.wordvault.model.BaseService;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import com.cengizhanyavuz.wordvault.service.auth.AuthenticationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WordService extends BaseService {

    private final WordRepository wordRepository;


    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<WordDto> getAllWords() {
        return wordRepository.findAllByUser(getCurrentUser())
                .stream()
                .map(WordDto::convert)
                .toList();
    }

    public WordDto createWord(WordCreateRequest request) {
        Word word = new Word();
        word.setUser(getCurrentUser());
        word.setTr(request.tr());
        word.setEn(request.en());
        word.setIt(request.it());
        word.setAlm(request.alm());
        return WordDto.convert(wordRepository.save(word));
    }

    public WordDto findWordById(Long id) {
        return WordDto.convert(getWordById(id));
    }

    public WordDto updateWordTranslation(WordUpdateRequest request) {
        Word word = getWordById(request.id());
        word.setTr(request.tr());
        word.setEn(request.en());
        word.setIt(request.it());
        word.setAlm(request.alm());
        return WordDto.convert(wordRepository.save(word));
    }

    public void updateProficiencyLevelOfWord(Long id, Boolean result) {
        if (result) {
            updateProficiencyLevelOfWord(id, WORD_POINTS_TO_DECREASED);
        } else {
            updateProficiencyLevelOfWord(id, WORD_POINTS_TO_INCREASED);
        }
    }

    @Scheduled(fixedRate = 24 * 60*30)
    public void updateProficiencyLevelByLastAnsweredDate() {
        wordRepository.increasePointsForOldWords(LocalDateTime.now().minusDays(
                MAX_DAY_COUNT_FOR_UPDATE_WORD));
    }

    public List<Word> getWordByUserElo() {
        Integer userElo = getCurrentUser().getElo(); // TODO
        return wordRepository.findRandomWords(TEST_WORD_COUNT);
    }

    // Private methods

    private void updateProficiencyLevelOfWord(Long id ,int point) {
        Word word = getWordById(id);
        word.setProficiencyLevel(word.getProficiencyLevel() + point);
        wordRepository.save(word);
    }

    private Word getWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException("Word not found with id: " + id)
        );
    }
}
