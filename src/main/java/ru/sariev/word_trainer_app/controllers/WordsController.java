package ru.sariev.word_trainer_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.sariev.word_trainer_app.models.Word;
import ru.sariev.word_trainer_app.services.Selection;
import ru.sariev.word_trainer_app.services.WordsService;

import javax.validation.Valid;

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
    public String createNewWord(@ModelAttribute("word") @Valid Word word, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "wordtrainer/newWordPage";
        }
        wordsService.save(word);
        return "redirect:allwords";
    }

    @GetMapping("/{id}/edit")
    public String editWord(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", wordsService.findOne(id));
        return "wordtrainer/editPage";
    }

    @PatchMapping("/{id}")
    public String updateWord(@ModelAttribute("word") @Valid Word word, BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "wordtrainer/editPage";
        }
        wordsService.update(id, word);
        return "redirect:allwords";
    }

    @DeleteMapping("/{id}")
    public String deleteWord(@PathVariable("id") int id) {
        wordsService.delete(id);
        return "redirect:allwords";
    }

    @PostMapping("/trainer")
    public String startTraining(@ModelAttribute("selection") Selection selectionFromView, Model model) {
        model.addAttribute("word", wordsService.getNextWord(selectionFromView));
        return "wordtrainer/hideTranslationPage";
    }

    @GetMapping("/{id}/showTranslation")
    public String showTranslation(@PathVariable("id") int id, Model model) {
        model.addAttribute("word", wordsService.findOne(id));
        return "wordtrainer/trainerPage";
    }

    @GetMapping("/next")
    public String nextWord(Model model) {
        model.addAttribute("word", wordsService.getNextWord());
        return "wordtrainer/hideTranslationPage";
    }

    @GetMapping("/{id}/editWordOnTraining")
    public String editWordOnTraining(Model model, @PathVariable("id") int id) {
        model.addAttribute("word", wordsService.findOne(id));
        return "wordtrainer/editTrainPage";
    }

    @PatchMapping("/{id}/updateWordOnTraining")
    public String updateWordOnTraining(@ModelAttribute("word") @Valid Word word, BindingResult bindingResult,
                                       @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "wordtrainer/editTrainPage";
        }
        wordsService.update(id, word);
        return "wordtrainer/trainerPage";
    }
}
