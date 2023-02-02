package com.avmeansomething.AdvanceStore;

import com.avmeansomething.AdvanceStore.models.Accessories;
import com.avmeansomething.AdvanceStore.repo.AccsRepository;
import com.avmeansomething.AdvanceStore.repo.SneakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.avmeansomething.AdvanceStore.models.Sneakers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccsService {
    @Autowired
    private AccsRepository repo;

    public Page<Accessories> findAllWithFilter(int pageNumber, String sortField, String keyword){
        Sort sort = Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNumber,6, sort);
        return repo.findFilter(pageable, keyword);
    }
}
