package ru.sariev.word_trainer_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sariev.word_trainer_app.models.Word;
import ru.sariev.word_trainer_app.repository.WordsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class WordsService {
    private int i = 0;

    private WordsRepository wordsRepository;

    @Autowired
    public WordsService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    public List<Word> getAllWords() {
        return wordsRepository.findAll();
    }

    public Word findOne(int id) {
        Optional<Word> foundWord = wordsRepository.findById(id);
        return foundWord.orElse(null);
    }

    @Transactional
    public void save(Word word) {
        wordsRepository.save(word);
    }

    @Transactional
    public void update(int id, Word updatedWord) {
        updatedWord.setId(id);
        wordsRepository.save(updatedWord);
    }

    @Transactional
    public void delete(int id) {
        wordsRepository.deleteById(id);
    }

    public Word getRandomWord() {
        List<Word> list = getAllWords();

        if (i == list.size()) {
            i = 0;
        }

        return list.get(i++);
    }
}
