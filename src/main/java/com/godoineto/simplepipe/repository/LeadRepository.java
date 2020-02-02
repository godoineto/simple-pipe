package com.godoineto.simplepipe.repository;

import com.godoineto.simplepipe.model.Lead;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeadRepository extends MongoRepository<Lead, String> {

    Optional<Lead> findByEmail(String email);
}
