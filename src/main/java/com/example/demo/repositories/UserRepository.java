package com.example.demo.repositories;

import com.example.demo.models.User;
import com.example.demo.projections.users.UserProjection;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    List<User> findAll();
    @Query("{$or: [{'email': ?O}, {'username': ?1}]}")
    User findOneByEmailOrUsername(String email, String username);
    User findOneByEmail(String email);

    User findOneById(String id);
    User findOneByUsername(String username);
    List<UserProjection> findAllBy();
}
