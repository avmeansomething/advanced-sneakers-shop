package com.avmeansomething.AdvanceStore.controllers;

import com.avmeansomething.AdvanceStore.EmailService;
import com.avmeansomething.AdvanceStore.models.Accessories;
import com.avmeansomething.AdvanceStore.models.Cart;
import com.avmeansomething.AdvanceStore.models.Orders;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import com.avmeansomething.AdvanceStore.repo.AccsRepository;
import com.avmeansomething.AdvanceStore.repo.CartRepository;
import com.avmeansomething.AdvanceStore.repo.OrdersRepo;
import com.avmeansomething.AdvanceStore.repo.SneakerRepository;
import com.avmeansomething.AdvanceStore.CartService;
import jakarta.persistence.criteria.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class CartController {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private SneakerRepository sneakerRepo;

    @Autowired
    private AccsRepository accsRepo;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrdersRepo ordersRepo;

    @Autowired
    private EmailService mailTo;

    @PostMapping("/cart/addsneakers")
    public String CartSneakersAdd(@RequestParam int idsneaker, Model model){
        Cart cart = new Cart(idsneaker, 0);
        cartRepo.save(cart);
        return "redirect:/cart";
    }

    @PostMapping("/neworder")
    public String GetOrder(Model model, @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String phone,
                           @RequestParam String email,
                           @RequestParam String adress,
                           @RequestParam String zipcode,
                           @RequestParam String comment){
        Orders order = new Orders(name, surname, phone, email, adress, zipcode, comment);
        ordersRepo.save(order);
    return "redirect:/takeorder";
    }

    @PostMapping("/cart/addaccessory")
    public String CartAccsAdd(@RequestParam int idaccessory, Model model){
        Cart cart = new Cart(0, idaccessory);
        cartRepo.save(cart);
        return "redirect:/cart";
    }
    @GetMapping("/cart/deleteaccs/{id}")
    public String CartDeleteAccs(@PathVariable("id") int deleteId, Model model){
        Optional<Cart> deleteRecord = cartRepo.findById(cartRepo.findIdAccs(deleteId));
        cartRepo.delete(deleteRecord.get());
        return "redirect:/cart";
    }
    @GetMapping("/cart/deletesneaker/{id}")
    public String CartDeleteSneaker(@PathVariable("id") int deleteId, Model model){
        Optional<Cart> deleteRecord = cartRepo.findById(cartRepo.findIdSneaker(deleteId));
        cartRepo.delete(deleteRecord.get());
        return "redirect:/cart";
    }

    @GetMapping("/cart")
    public String CartView(Model model) {

        List<Sneakers> sneakersList = cartService.GetSneakersFromCart();
        List<Accessories> accessoriesList = cartService.GetAccessoriesFromCart();

        int ifPresent = sneakerRepo.discountPresent();
        if(ifPresent > 0){
            model.addAttribute("discount", sneakerRepo.getDiscount());
        }

        model.addAttribute("sneakers", sneakersList);
        model.addAttribute("accessories", accessoriesList);
        model.addAttribute("countSneakers", sneakersList.size());
        model.addAttribute("countAccs", accessoriesList.size());
        model.addAttribute("costsneakers", cartService.GetAllSneakersCost(sneakersList));
        model.addAttribute("accscost", cartService.GetAllAccessoriesCost(accessoriesList));
        model.addAttribute("count", cartRepo.countCartItems());
        return "cart";
    }

    @GetMapping("/checkout")
    public String CheckoutView(Model model){
        List<Sneakers> sneakersList = cartService.GetSneakersFromCart();
        List<Accessories> accessoriesList = cartService.GetAccessoriesFromCart();
        model.addAttribute("sneakers", sneakersList);
        model.addAttribute("accessories", accessoriesList);
        model.addAttribute("countSneakers", sneakersList.size());
        model.addAttribute("countAccs", accessoriesList.size());
        model.addAttribute("costsneakers", cartService.GetAllSneakersCost(sneakersList));
        model.addAttribute("accscost", cartService.GetAllAccessoriesCost(accessoriesList));
        model.addAttribute("count", cartRepo.countCartItems());
        return "checkout";
    }

    @GetMapping("/takeorder")
    public String ConfirmationView(Model model){
        List<Sneakers> sneakersList = cartService.GetSneakersFromCart();
        List<Accessories> accessoriesList = cartService.GetAccessoriesFromCart();
        int Scost = cartService.GetAllSneakersCost(sneakersList);
        int Acost = cartService.GetAllAccessoriesCost(accessoriesList);
        List<Orders> orders = ordersRepo.findFirstByOrderByIdDesc();
        model.addAttribute("sneakers", sneakersList);
        model.addAttribute("accessories", accessoriesList);
        model.addAttribute("countSneakers", sneakersList.size());
        model.addAttribute("countAccs", accessoriesList.size());
        model.addAttribute("costsneakers", cartService.GetAllSneakersCost(sneakersList));
        model.addAttribute("accscost", cartService.GetAllAccessoriesCost(accessoriesList));
        model.addAttribute("count", cartRepo.countCartItems());
        model.addAttribute("discount", sneakerRepo.getDiscount());
        mailTo.sendSimpleMessage( "Advance Sneaker Store", "Привет " + orders.get(0).getName() +
                ", спасибо за заказ! Постараемся доставить его тебе молниеносно! " +
                "Твой заказ вышел на сумму: " + Scost + " Br" +
                " за кроссовки и " + Acost + " Br" +
                " за заказанные аксессуары. Уверяем, что тебе все точно понравится) " +
                " Заказ вышлем по адресу - " + orders.get(0).getAdress() + " " +
                "До встречи! Заходи к нам почаще!", "ronn1e23@yandex.ru");
        return "confirmation";
    }
    @GetMapping("/return")
    public String ReturnToMain(Model model){
        cartRepo.deleteAll();
        return "redirect:/sneakers/1?sortField=id&keyword=0";
    }
}
