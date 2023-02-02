package com.avmeansomething.AdvanceStore.repo;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.avmeansomething.AdvanceStore.models.Accessories;

import java.util.List;

public interface AccsRepository extends PagingAndSortingRepository<Accessories, Long>, CrudRepository<Accessories, Long> {


    List<Accessories> findById(int id);

    @Query("SELECT a from Accessories a WHERE " +
            "CONCAT(a.type, a.name, a.description, a.color, a.brand, a.ident)" +
            " LIKE %?1%")
    Page<Accessories> findFilter(Pageable pageable, String keyword);

    @Query("SELECT count(s.id) from Accessories s WHERE s.brand LIKE %?1%")
    int countBrand(String brand);

    @Query("SELECT count(s.id) from Accessories s WHERE s.type LIKE %?1%")
    int countType(String type);
}
