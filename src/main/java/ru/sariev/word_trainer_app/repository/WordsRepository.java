package ru.sariev.word_trainer_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sariev.word_trainer_app.models.Word;

@Repository
public interface WordsRepository extends JpaRepository<Word, Integer> {
}
