package ru.sariev.word_trainer_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.sariev.word_trainer_app.services.WordsService;

@Controller
@RequestMapping("words")
public class WordsController {

    private WordsService wordsService;

    @Autowired
    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping("/main")
    public String selectAction() {
        return "words/mainPage";
    }

    @GetMapping("/allwords")
    public String showAllWords(Model model) {
        model.addAttribute("words", wordsService.getAllWords());
        return "words/allWordsPage";
    }

    @GetMapping("/trainer")
    public String startTraining() {
        return "words/trainerPage";
    }
}
