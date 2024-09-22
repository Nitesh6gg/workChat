package com.workchat.repository;

import com.workchat.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    @Query("{ 'id': { $ne: ?0 } }")
    List<User> findAllUsersExcept(String userId);

}
