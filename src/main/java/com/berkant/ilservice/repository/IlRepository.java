package com.berkant.ilservice.repository;

import com.berkant.ilservice.model.Il;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface IlRepository extends MongoRepository<Il,String> {
    Optional<Il> findByName(String name);
    List<Il> findAllByName(String name);

}
