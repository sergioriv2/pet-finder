package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    List<UserProjection> findAllBy();
}
