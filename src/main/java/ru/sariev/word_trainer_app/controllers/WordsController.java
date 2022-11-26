package ru.sariev.word_trainer_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.sariev.word_trainer_app.models.Word;
import ru.sariev.word_trainer_app.services.WordsService;

@Controller
@RequestMapping("train")
public class WordsController {

    private WordsService wordsService;

    @Autowired
    public WordsController(WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping("/main")
    public String selectAction() {
        return "wordtrainer/mainPage";
    }

    @GetMapping("/allwords")
    public String showAllWords(Model model) {
        model.addAttribute("words", wordsService.getAllWords());
        return "wordtrainer/allWordsPage";
    }

    @GetMapping("/new")
    public String addNewWord(@ModelAttribute("word") Word word) {
        return "wordtrainer/newWordPage";
    }

    @PostMapping("/allwords")
    public String createWord(@ModelAttribute("word") Word word) {
        wordsService.save(word);
        return "redirect:allwords";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", wordsService.findOne(id));
        return "wordtrainer/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("word") Word word, @PathVariable("id") int id) {
        wordsService.update(id, word);
        return "redirect:allwords";
    }

    @DeleteMapping("/{id}")
    public String deleteWord(@PathVariable("id") int id) {
        wordsService.delete(id);
        return "redirect:allwords";
    }

    @GetMapping("/trainer")
    public String startTraining(Model model) {
        model.addAttribute("word", wordsService.getRandomWord());
        return "wordtrainer/trainerPage";
    }

    @GetMapping("/next")
    public String nextWord(Model model) {
        model.addAttribute("word", wordsService.getRandomWord());
        return "redirect:trainer";
    }

    @GetMapping("/{id}/editTrain")
    public String editTrain(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", wordsService.findOne(id));
        return "wordtrainer/editTrain";
    }

    @PatchMapping("/{id}/update")
    public String updateTrain(@ModelAttribute("word") Word word, @PathVariable("id") int id) {
        wordsService.update(id, word);
        return "wordtrainer/trainerPage";
    }
}
