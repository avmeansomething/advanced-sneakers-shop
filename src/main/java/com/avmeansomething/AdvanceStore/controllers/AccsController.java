package com.avmeansomething.AdvanceStore.controllers;

import com.avmeansomething.AdvanceStore.AccsService;
import com.avmeansomething.AdvanceStore.SneakerService;
import com.avmeansomething.AdvanceStore.models.Accessories;
import com.avmeansomething.AdvanceStore.models.Pics;
import com.avmeansomething.AdvanceStore.models.Review;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import com.avmeansomething.AdvanceStore.repo.AccsRepository;
import com.avmeansomething.AdvanceStore.repo.CartRepository;
import com.avmeansomething.AdvanceStore.repo.ReviewRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AccsController {


    @Autowired
    private AccsService accsService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private AccsRepository accsRepository;
    @Autowired
    private CartRepository cartRepo;

    @GetMapping("/accessories/{pageNumber}")
    public String AccsMain(Model model,
                               @PathVariable("pageNumber") int currentPage,
                               @Param("sortField") String sortField,
                               @Param("keyword") String keyword)
    {
        Page<Accessories> page = accsService.findAllWithFilter(currentPage - 1, sortField, keyword);
        List<Accessories> accessories = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("accs",accessories);
        model.addAttribute("cjordan", accsRepository.countBrand("JORDAN"));
        model.addAttribute("cadidas", accsRepository.countBrand("ADIDAS"));
        model.addAttribute("cnike", accsRepository.countBrand("NIKE"));
        model.addAttribute("cpuma", accsRepository.countBrand("PUMA"));
        model.addAttribute("socks", accsRepository.countType("Носки"));
        model.addAttribute("cap", accsRepository.countType("Кепка"));
        model.addAttribute("bag", accsRepository.countType("Рюкзак"));
        model.addAttribute("aid", accsRepository.countType("Средство"));
        model.addAttribute("all", accsRepository.countType(""));
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("sortField",sortField);
        model.addAttribute("keyword", keyword);
        model.addAttribute("count", cartRepo.countCartItems());

        return "accessories";
    }
    @GetMapping("/accessories/single-accessory/{id}")
    public String SingleAccs(@PathVariable(value = "id") int id, Model model){
        List<Accessories> post = accsRepository.findById(id);
        List<Review> revs = reviewRepository.findByidsneaker(id);
        model.addAttribute("post",post);
        model.addAttribute("reviews",revs);
        model.addAttribute("id", id);
        model.addAttribute("revAmount",reviewRepository.countAll(id));
        model.addAttribute("avgMark", reviewRepository.avgMark(id));
        model.addAttribute("fives",reviewRepository.countFive(id));
        model.addAttribute("fours",reviewRepository.countFour(id));
        model.addAttribute("three",reviewRepository.countThree(id));
        model.addAttribute("two",reviewRepository.countTwo(id));
        model.addAttribute("one",reviewRepository.countOne(id));
        model.addAttribute("count", cartRepo.countCartItems());

        return "single-accessory";
    }
    @PostMapping("/accessories/addnew")
    public String AccsAdd(@RequestParam String brand, @RequestParam String color, @RequestParam int cost, @RequestParam String description,
                          @RequestParam int istock, @RequestParam String name, @RequestParam String pic,
                              @RequestParam String pictwo, @RequestParam String type, Model model){
        Accessories accs = new Accessories(type, brand, name, pic, color, description, pictwo, cost, istock);
        accsRepository.save(accs);
        return "redirect:/accessories/1?sortField=id&keyword=0";
    }
    @GetMapping("/accessory-add")
    public String AccsAddView(Model model){
        return "accessory-add";
    }
}
