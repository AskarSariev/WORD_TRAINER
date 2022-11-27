package ru.sariev.word_trainer_app.services;

/**
 * Выборка слов из словаря в режиме Trainer:
 * ALL - учить все слова;
 * LEARNED - повторить выученные слова;
 * UNLEARNED - учить все невыученные слова
 */

public enum Selection {
    ALL, LEARNED, UNLEARNED
}
