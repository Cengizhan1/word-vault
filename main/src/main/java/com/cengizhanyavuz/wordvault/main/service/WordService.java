package com.cengizhanyavuz.wordvault.main.service;

import com.cengizhanyavuz.wordvault.main.dto.WordDto;
import com.cengizhanyavuz.wordvault.main.dto.WordCreateRequest;
import com.cengizhanyavuz.wordvault.main.exception.InsufficientWordsException;
import com.cengizhanyavuz.wordvault.main.exception.WordExistsException;
import com.cengizhanyavuz.wordvault.main.exception.WordNotFoundException;
import com.cengizhanyavuz.wordvault.main.exception.WorldAlreadyApprovedException;
import com.cengizhanyavuz.wordvault.main.model.Word;
import com.cengizhanyavuz.wordvault.main.repository.WordRepository;
import com.cengizhanyavuz.wordvault.user.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import static com.cengizhanyavuz.wordvault.main.config.PointConstants.*;

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
        return wordRepository.findAllByIsApprovedTrue()
                .stream()
                .map(WordDto::convert)
                .toList();
    }

    private void checkWordExists(String tr) {
        if (wordRepository.existsByTr(tr)) {
            throw new WordExistsException("Word already exists");
        }
    }

    public WordDto createWord(WordCreateRequest request) {
        checkWordExists(request.tr());
        Word word = new Word();
        word.setTr(request.tr());
        word.setEn(request.en());
        word.setIt(request.it());
        word.setAlm(request.alm());
        return WordDto.convert(wordRepository.save(word));
    }

    public WordDto findWordById(Long id) {
        return WordDto.convert(getWordById(id));
    }

//    @Scheduled(fixedRate = 24 * 60 * 30)
//    public void updateEloByLastAnsweredDate() {
//        wordRepository.increaseElo(WORD_POINTS_TO_INCREASED, LocalDateTime.now().minusDays(
//                MAX_DAY_COUNT_FOR_UPDATE_WORD));
//    }

    public List<Word> getWords() throws InsufficientWordsException {
        List<Word> words = wordRepository.findRandomWords(TEST_WORD_COUNT, userService.getUser().elo());
        if (words.size() != TEST_WORD_COUNT) {
            throw new InsufficientWordsException();
        }
        return words;
    }

    public WordDto getRandomIsNotApprovedWord() {
        return WordDto.convert(wordRepository.findRandomWordByIsApprovedFalse().orElseThrow(
                () -> new WordNotFoundException("Word not found by isApproved = false")
        ));
    }
    public WordDto setWordAsApproved(Long id) {
        Word word = getWordById(id);
        if (word.getIsApproved()) {
            throw new WorldAlreadyApprovedException("Word already approved");
        }
        word.setIsApproved(true);
        return WordDto.convert(wordRepository.save(word));
    }


    private Word getWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException("Word not found with id: " + id)
        );
    }

    @Async
    public void saveAll(List<Word> words) {
        wordRepository.saveAll(words);
    }
}
