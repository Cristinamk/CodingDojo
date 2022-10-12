package com.bookclub.mvc.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bookclub.mvc.models.User;

@Repository
public interface UserRepository extends CrudRepository<User,Long>{
	Optional<User> findByEmail(String email);
	boolean existsUserByEmail(String email);

}
