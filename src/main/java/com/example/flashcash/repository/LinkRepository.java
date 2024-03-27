package com.example.flashcash.repository;

import com.example.flashcash.model.Link;
import com.example.flashcash.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
}
