package com.mysite.sbb.user.profile;


import com.mysite.sbb.comment.Comment;
import com.mysite.sbb.comment.CommentService;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.security.Principal;

@RequestMapping("/profile")
@RequiredArgsConstructor
@Controller
public class profileController {

        private final QuestionService questionService;
        private final UserService userService;
        private final CommentService commentService;

        @PreAuthorize("isAuthenticated()")
        @GetMapping("/list_article")
        public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Principal principal) {

            Page<Question> user_paging = this.questionService.getListUser(page, this.userService.getUser(principal.getName()));
            model.addAttribute("user_paging", user_paging);
            return "profile";
        }


        @PreAuthorize("isAuthenticated()")
        @GetMapping("/list_comment")
        public String list_comment(Model model, @RequestParam(value = "page", defaultValue = "0") int page, Principal principal) {
            Page<Comment> user_paging = this.commentService.getListUser(page, this.userService.getUser(principal.getName()));
            model.addAttribute("user_paging", user_paging);
            return "profile";
        }
}
