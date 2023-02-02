package com.avmeansomething.AdvanceStore.controllers;

import com.avmeansomething.AdvanceStore.AccsService;
import com.avmeansomething.AdvanceStore.models.Feedback;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import com.avmeansomething.AdvanceStore.repo.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FeedbackController {
    @Autowired
    private FeedbackRepo feedbackRepo;

    @PostMapping("/feedback/add")
    public String SneakersAdd(@RequestParam String name, @RequestParam String email, @RequestParam String topic, @RequestParam String text,
                              Model model){
        Feedback feedback = new Feedback(name, email, topic, text );
        feedbackRepo.save(feedback);
        return "index";
    }
}
