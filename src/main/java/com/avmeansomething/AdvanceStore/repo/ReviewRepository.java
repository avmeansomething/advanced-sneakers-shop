package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Pics;
import com.avmeansomething.AdvanceStore.models.Review;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Long> {

    List<Review> findByidsneaker(int id);

    @Query("SELECT AVG(r.mark) from Review r WHERE idsneaker = ?1")
    String avgMark(int id);
    @Query("SELECT count(r.id) from Review r WHERE mark = 5 and r.idsneaker = ?1")
    int countFive(int id);
    @Query("SELECT count(r.mark) from Review r WHERE mark = 4 and r.idsneaker = ?1")
    int countFour(int id);
    @Query("SELECT count(r.id) from Review r WHERE mark = 3 and r.idsneaker = ?1")
    int countThree(int id);
    @Query("SELECT count(r.id) from Review r WHERE mark = 2 and r.idsneaker = ?1")
    int countTwo(int id);
    @Query("SELECT count(r.id) from Review r WHERE mark = 1 and r.idsneaker = ?1")
    int countOne(int id);
    @Query("SELECT count(r.id) from Review r WHERE r.idsneaker = ?1")
    int countAll(int id);



}
