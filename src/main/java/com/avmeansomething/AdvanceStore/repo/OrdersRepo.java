package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Orders;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends CrudRepository<Orders, Long> {
    List<Orders> findFirstByOrderByIdDesc();
}
