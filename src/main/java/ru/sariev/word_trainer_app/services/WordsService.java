package ru.sariev.word_trainer_app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.sariev.word_trainer_app.models.Word;
import ru.sariev.word_trainer_app.repository.WordsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class WordsService {
    private int index; // Индекс в списке слов
    private Selection selection;
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

    public Word selectionAll() {
        List<Word> words = getAllWords();
        resetIndex(words);
        return words.get(index++);
    }

    public Word selectionLearned() {
        List<Word> words = getAllWords().stream().filter(word -> word.isStatus()).collect(Collectors.toList());
        resetIndex(words);
        return words.get(index++);
    }

    public Word selectionUnLearned() {
        List<Word> words = getAllWords().stream().filter(word -> !word.isStatus()).collect(Collectors.toList());
        resetIndex(words);
        return words.get(index++);
    }

    public Word getNextWord() {
        return getWord(selection);
    }

    public Word getNextWord(Selection selectionFromView) {
        index = 0;
        Word nextWord = null;
        setSelection(selectionFromView);

        return getWord(selectionFromView);
    }

    private void resetIndex(List<Word> words) {
        if (index == words.size()) {
            index = 0;
        }
    }

    private void setSelection(Selection selectionFromView) {
        switch (selectionFromView) {
            case ALL:
                selection = Selection.ALL;
                break;

            case LEARNED:
                selection = Selection.LEARNED;
                break;

            case UNLEARNED:
                selection = Selection.UNLEARNED;
                break;

            default:
                selection = Selection.ALL;
        }
    }

    private Word getWord(Selection selection) {
        Word nextWord = null;

        switch (selection) {
            case ALL:
                nextWord = selectionAll();
                break;

            case LEARNED:
                nextWord = selectionLearned();
                break;

            case UNLEARNED:
                nextWord = selectionUnLearned();
                break;

            default:
                nextWord = selectionAll();
        }
        return nextWord;
    }
}
