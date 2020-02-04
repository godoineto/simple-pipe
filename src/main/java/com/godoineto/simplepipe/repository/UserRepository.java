package com.godoineto.simplepipe.repository;

import com.godoineto.simplepipe.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
