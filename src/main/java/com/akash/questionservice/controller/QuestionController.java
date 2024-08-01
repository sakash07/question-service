package com.akash.questionservice.controller;

import com.akash.questionservice.model.Question;
import com.akash.questionservice.model.QuestionWrapper;
import com.akash.questionservice.model.Response;
import com.akash.questionservice.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {

    @Autowired
    QuestionService questionService;

    @Autowired
    Environment environment;

    @PostMapping("create")
    public ResponseEntity<String> CreateQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);

    }

    @GetMapping("getall")
    public ResponseEntity<List<Question>> getQuestions(){
        return questionService.getAllQuestions();

    }

    @GetMapping("get/{id}")
    public ResponseEntity<Question> getOneQuestion(@PathVariable Integer id){
        return questionService.getOneQuestion(id);
    }

    @GetMapping("getCategory/{category}")
    public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
        return questionService.getQuestionByCategory(category);
    }

    @GetMapping("generate")
    public ResponseEntity<List<Integer>> getQuestionsforQuiz
                (@RequestParam String categoryName, @RequestParam Integer numQuestions){
        return questionService.getQuestionsForQuiz(categoryName, numQuestions);
    }

    @PostMapping("getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsFromId(@RequestBody List<Integer> questionIds){
        System.out.println(environment.getProperty("local.server.port"));
        return questionService.getQuestionsFromId(questionIds);
    }

    @PostMapping("getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
        return questionService.getScore(responses);

    }

}
