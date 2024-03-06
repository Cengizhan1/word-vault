package com.cengizhanyavuz.wordvault.service;

import com.cengizhanyavuz.wordvault.dto.WordDto;
import com.cengizhanyavuz.wordvault.dto.request.WordCreateRequest;
import com.cengizhanyavuz.wordvault.dto.request.WordUpdateRequest;
import com.cengizhanyavuz.wordvault.exception.WordNotFoundException;
import com.cengizhanyavuz.wordvault.model.Word;
import com.cengizhanyavuz.wordvault.repository.WordRepository;
import com.cengizhanyavuz.wordvault.service.auth.AuthenticationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordService {

    private final WordRepository wordRepository;
    private final AuthenticationService authenticationService;


    public WordService(WordRepository wordRepository, AuthenticationService authenticationService) {
        this.wordRepository = wordRepository;
        this.authenticationService = authenticationService;
    }

    public List<WordDto> getAllWords() {
        return wordRepository.findAll()
                .stream()
                .map(WordDto::convert)
                .toList();
    }

    public WordDto createWord(WordCreateRequest request) {
        Word word = new Word();
        word.setUser(authenticationService.getCurrentUser());
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

    private Word getWordById(Long id) {
        return wordRepository.findById(id).orElseThrow(
                () -> new WordNotFoundException("Word not found with id: " + id)
        );
    }
}
