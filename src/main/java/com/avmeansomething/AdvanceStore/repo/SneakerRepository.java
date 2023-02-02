package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Sneakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


import java.util.List;

public interface SneakerRepository extends PagingAndSortingRepository<Sneakers, Long>, CrudRepository<Sneakers, Long> {
    List<Sneakers> findById(int id);
    List<Sneakers> findByisnew(int id);
    List<Sneakers> findByissale(int id);
    List<Sneakers> findByistock(int id);

    Page<Sneakers> findByBrandAndSex(Pageable pageable, String brand, String sex);

    Page<Sneakers> findByBrand(String brand, Pageable pageable);

    Page<Sneakers> findByBrandNot(String brand, Pageable pageable);

    Page<Sneakers> findBySex(Pageable pageable, String sex);

    @Query("SELECT s from Sneakers s WHERE " +
            "CONCAT(s.brand, s.sex, s.type, s.color, s.material, s.idbrand)" +
            " LIKE %?1%")
    Page<Sneakers> findFilter(Pageable pageable, String keyword);

    @Query("SELECT count(s.id) from Sneakers s WHERE s.brand_name = ?1")
    int countBrand(String brand);

    @Query("SELECT count(s.id) from Sneakers s")
    int countAll();

    @Query("SELECT count(s.id) from Sneakers s WHERE s.type = ?1")
    int countType(String type);

    @Query("SELECT sum(s.issale) FROM Sneakers s inner join Cart c on s.id = c.idsneaker")
    int getDiscount();

    @Query("SELECT count(c.id) FROM Cart c WHERE c.idsneaker <> 0")
    int discountPresent();

}
