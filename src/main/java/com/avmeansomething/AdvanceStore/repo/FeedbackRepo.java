package com.avmeansomething.AdvanceStore.repo;

import com.avmeansomething.AdvanceStore.models.Feedback;
import org.springframework.data.repository.CrudRepository;

public interface FeedbackRepo extends CrudRepository<Feedback, Long> {

}
