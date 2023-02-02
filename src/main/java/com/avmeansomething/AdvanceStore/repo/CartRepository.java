package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Sneakers;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.avmeansomething.AdvanceStore.models.Cart;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, Long> {

    @Query("SELECT c.idsneaker from Cart c WHERE c.idsneaker <> 0")
    List<Integer> GetAllSneakersFromCart();

    @Query("SELECT c.idaccessory from Cart c WHERE c.idaccessory <> 0")
    List<Integer> GetAllAccsFromCart();

    @Query("SELECT count(c.id) from Cart c")
    int countCartItems();

    @Query("SELECT c.id from Cart c where c.idsneaker = ?1")
    long findIdSneaker(int id);
    @Query("SELECT c.id from Cart c where c.idaccessory = ?1")
    long findIdAccs(int id);

}
