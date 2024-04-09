package com.example.sbb2.Question;


import com.example.sbb2.Answer.AnswerForm;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import java.util.List;

@RequestMapping("/question")
@RequiredArgsConstructor
@Controller
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") Integer page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "Question/question_list";
    }

    @GetMapping(value = "/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id, AnswerForm answerForm) {
        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "Question/question_detail";
    }

    @GetMapping("/create")
    public String questionCreate(QuestionForm form) {
        return "Question/question_form";
    }

    @PostMapping("/create")
    public String questionCreate(@Valid QuestionForm form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "Question/question_form";
        }
        this.questionService.create(form.getSubject(), form.getContent());
        return "redirect:/question/list";
    }
}
