package ru.sariev.word_trainer_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sariev.word_trainer_app.models.Word;
import ru.sariev.word_trainer_app.repository.WordsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class WordsService {

    private WordsRepository wordsRepository;

    @Autowired
    public WordsService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    public List<Word> getAllWords() {
        return wordsRepository.findAll();
    }
}
