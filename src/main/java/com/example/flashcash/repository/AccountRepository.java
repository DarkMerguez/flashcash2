package com.example.flashcash.repository;

import com.example.flashcash.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<UserAccount, Long> {
}
