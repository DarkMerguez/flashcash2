package com.example.flashcash.repository;

import com.example.flashcash.model.Link;
import com.example.flashcash.model.User;
import com.example.flashcash.model.UserAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Repository
public interface LinkRepository extends CrudRepository<Link, Long> {
    public List<Link> findLinksByUser1Email(String email);

}
