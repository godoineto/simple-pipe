package com.godoineto.simplepipe.repository;

import com.godoineto.simplepipe.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}
