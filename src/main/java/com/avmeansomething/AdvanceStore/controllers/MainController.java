package com.avmeansomething.AdvanceStore.controllers;

import com.avmeansomething.AdvanceStore.repo.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    private CartRepository cartRepo;

    @GetMapping("/single-product")
    public String CurrentModelView(Model model) {
        return "single-product";
    }

    @GetMapping("/confirmation")
    public String ConfirmationView(Model model) {
        return "confirmation";
    }

    @GetMapping("/contact")
    public String ContactView(Model model) {
        model.addAttribute("count", cartRepo.countCartItems());
        return "contact";
    }


}