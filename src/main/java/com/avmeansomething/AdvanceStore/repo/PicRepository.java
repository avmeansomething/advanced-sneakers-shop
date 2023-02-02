package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Pics;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface PicRepository extends CrudRepository<Pics, Long> {
    List<Pics> findBysneakerid(int id);

}
