package com.avmeansomething.AdvanceStore;
import com.avmeansomething.AdvanceStore.models.Accessories;
import com.avmeansomething.AdvanceStore.repo.AccsRepository;
import com.avmeansomething.AdvanceStore.repo.CartRepository;
import com.avmeansomething.AdvanceStore.repo.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private SneakerRepository sneakerRepo;

    @Autowired
    private AccsRepository accsRepo;

    public List<Sneakers> GetSneakersFromCart(){
        List<Sneakers> sneakersList = new ArrayList<Sneakers>();//все кроссовки
        List<Integer> sneakersid = cartRepo.GetAllSneakersFromCart();//получаем все id кроссовок с корзины

        for (int i = 0; i < sneakersid.size(); i++ )
        {
            List<Sneakers> getSneaker = sneakerRepo.findById(sneakersid.get(i));//ищем Sneaker по id и добавляем в лист
            sneakersList.add(getSneaker.get(0));//добавляем найденный кроссовок в общий лист для вывода
        }
        return sneakersList;
    }

    public List<Accessories> GetAccessoriesFromCart(){
        List<Accessories> accessoriesList = new ArrayList<Accessories>();
        List<Integer> accessoryid = cartRepo.GetAllAccsFromCart();

        for (int i = 0; i < accessoryid.size(); i++ )
        {
            List<Accessories> getAccs = accsRepo.findById(accessoryid.get(i));//ищем Sneaker по id и добавляем в лист
            accessoriesList.add(getAccs.get(0));//добавляем найденный кроссовок в общий лист для вывода
        }
        return accessoriesList;
    }

    public int GetAllSneakersCost(List<Sneakers> sneakers){
        int cost = 0;
        for (int i = 0; i < sneakers.size(); i++){
            cost += sneakers.get(i).getCost();
        }
        return cost;
    }
    public int GetAllAccessoriesCost(List<Accessories> accessories){
        int cost = 0;
        for (int i = 0; i < accessories.size(); i++){
            cost += accessories.get(i).getCost();
        }
        return cost;
    }

}
