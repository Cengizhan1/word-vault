package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.WordDto;
import com.cengizhanyavuz.wordvault.dto.request.WordCreateRequest;
import com.cengizhanyavuz.wordvault.dto.request.WordUpdateRequest;
import com.cengizhanyavuz.wordvault.exception.WordExistsException;
import com.cengizhanyavuz.wordvault.exception.WordNotFoundException;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.model.user.User;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static com.cengizhanyavuz.wordvault.constants.PointConstants.*;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final UserService userService;


    public WordService(WordRepository wordRepository, UserService userService) {
        this.wordRepository = wordRepository;
        this.userService = userService;
    }

    public List<WordDto> getAllWords() {
        return wordRepository.findAllByUser(userService.getCurrentUser())
                .stream()
                .map(WordDto::convert)
                .toList();
    }
    private void checkWordExists(User user, String tr) {
        if (wordRepository.existsByUserAndTr(user, tr)) {
            throw new WordExistsException("Word already exists by this user");
        }
    }

    public WordDto createWord(WordCreateRequest request) {
        User user = userService.getCurrentUser();
        checkWordExists(user, request.tr());
        Word word = new Word();
        word.setUser(user);
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

    @Scheduled(fixedRate = 24 * 60*30)
    public void updateProficiencyLevelByLastAnsweredDate() {
//        wordRepository.increasePointsForOldWords(WORD_POINTS_TO_INCREASED ,LocalDateTime.now().minusDays(
//                MAX_DAY_COUNT_FOR_UPDATE_WORD));
    }

    public List<Word> getWordByUserElo() {
        Integer userElo = userService.getCurrentUser().getElo(); // TODO
        return wordRepository.findRandomWords(TEST_WORD_COUNT);
    }

    // Private methods

    @Async
    protected void updateProficiencyLevelOfWord(Word word ,int point) {
        word.setProficiencyLevel(word.getProficiencyLevel() + point);
        wordRepository.save(word);
    }

    private Word getWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException("Word not found with id: " + id)
        );
    }
}
