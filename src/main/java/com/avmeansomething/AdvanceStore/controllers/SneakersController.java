package com.avmeansomething.AdvanceStore.controllers;

import com.avmeansomething.AdvanceStore.SneakerService;
import com.avmeansomething.AdvanceStore.models.Pics;
import com.avmeansomething.AdvanceStore.models.Review;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import com.avmeansomething.AdvanceStore.repo.CartRepository;
import com.avmeansomething.AdvanceStore.repo.PicRepository;
import com.avmeansomething.AdvanceStore.repo.ReviewRepository;
import com.avmeansomething.AdvanceStore.repo.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Page;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Controller
public class SneakersController {

    @Autowired
    private SneakerRepository sneakerRepository;
    @Autowired
    private PicRepository picsRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private SneakerService sneakerService;

    @Autowired
    private CartRepository cartRepo;

    @GetMapping("/")
    public String SneakersNew(Model model){
        Iterable<Sneakers> sneakers = sneakerRepository.findByisnew(1);
        Iterable<Sneakers> sneakersNew = sneakerRepository.findByistock(0);
        Iterable<Sneakers> sneakersSale = sneakerRepository.findByissale(1);
        model.addAttribute("sneakers",sneakers);
        model.addAttribute("sneakersnew",sneakersNew);
        model.addAttribute("sneakerssale",sneakersSale);
        model.addAttribute("count", cartRepo.countCartItems());
        return "index";
    }

    @GetMapping("/sneakers/{pageNumber}")
    public String SneakersMain(Model model,
                               @PathVariable("pageNumber") int currentPage,
                               @Param("sortField") String sortField,
                               @Param("keyword") String keyword)
    {
        Page<Sneakers> page = sneakerService.findAllWithFilter(currentPage - 1, sortField, keyword);
        List<Sneakers> sneakers = page.getContent();
        int totalPages = page.getTotalPages();
        long totalItems = page.getTotalElements();
        model.addAttribute("sneakers",sneakers);
        model.addAttribute("totalItems",totalItems);
        model.addAttribute("totalPages",totalPages);
        model.addAttribute("currentPage",currentPage);
        model.addAttribute("sortField",sortField);
        model.addAttribute("keyword", keyword);
        model.addAttribute("cnike", sneakerRepository.countBrand("NIKE"));
        model.addAttribute("cadidas", sneakerRepository.countBrand("ADIDAS"));
        model.addAttribute("cunder", sneakerRepository.countBrand("UNDER"));
        model.addAttribute("creebok", sneakerRepository.countBrand("REEBOK"));
        model.addAttribute("cpuma", sneakerRepository.countBrand("PUMA"));
        model.addAttribute("cair", sneakerRepository.countBrand("AIR"));
        model.addAttribute("cbalance", sneakerRepository.countBrand("BALANCE"));
        model.addAttribute("call", sneakerRepository.countAll());
        model.addAttribute("cwinter", sneakerRepository.countType("Зимние"));
        model.addAttribute("callwear", sneakerRepository.countType("Повседневные"));
        model.addAttribute("crunners", sneakerRepository.countType("Беговые"));
        model.addAttribute("csummer", sneakerRepository.countType("Летние"));
        model.addAttribute("count", cartRepo.countCartItems());
        return "sneakers";
    }
    @PostMapping("/sneakers/addnew")
    public String SneakersAdd(@RequestParam String brand, @RequestParam String brand_name, @RequestParam String color, @RequestParam int cost, @RequestParam String description,
                              @RequestParam int isnew, @RequestParam int issale, @RequestParam int istock, @RequestParam String material, @RequestParam String pic,
                              @RequestParam String sex, @RequestParam String sizes, @RequestParam String type, Model model){
        Sneakers sneaker = new Sneakers(brand, brand_name, description, sizes, color, sex, type, material, pic, cost, isnew, issale, istock);
        sneakerRepository.save(sneaker);
        return "redirect:/sneakers/1?sortField=id&keyword=0";
    }

    @GetMapping("/sneakers-add")
    public String AddSneaker(Model model){
        return "sneakers-add";
    }

    @GetMapping("/sneakers/single-sneaker/{id}")
    public String SingleSneaker(@PathVariable(value = "id") int id, Model model, @Param("sortField") String sortField,
                                @Param("keyword") String keyword){
        List<Sneakers> post = sneakerRepository.findById(id);
        List<Pics> pics = picsRepository.findBysneakerid(id);
        List<Review> revs = reviewRepository.findByidsneaker(id);
        model.addAttribute("post",post);
        model.addAttribute("pics",pics);
        model.addAttribute("reviews",revs);
        model.addAttribute("id", id);
        model.addAttribute("sortField",sortField);
        model.addAttribute("keyword",keyword);
        model.addAttribute("revAmount",reviewRepository.countAll(id));
        model.addAttribute("avgMark", reviewRepository.avgMark(id));
        model.addAttribute("fives",reviewRepository.countFive(id));
        model.addAttribute("fours",reviewRepository.countFour(id));
        model.addAttribute("three",reviewRepository.countThree(id));
        model.addAttribute("two",reviewRepository.countTwo(id));
        model.addAttribute("one",reviewRepository.countOne(id));
        model.addAttribute("count", cartRepo.countCartItems());
        return "single-product";
    }

    @PostMapping("/review/add")
    public String AddReview(@RequestParam String name, @RequestParam String message, @RequestParam int mark, @RequestParam String email, @RequestParam int sneakerid, Model model){
        Review review = new Review(message, name, email, mark, sneakerid);
        reviewRepository.save(review);
        return "redirect:/sneakers/single-sneaker/" + sneakerid;
    }
    @PostMapping("/sneaker-pic/add")
    public String AddPicture(@RequestParam String url, int sneakerid, Model model){
        Pics pic = new Pics(url, sneakerid);
        picsRepository.save(pic);
        return "sneakers-add";
    }


}
