package com.example.flashcash.repository;

import com.example.flashcash.model.Transfer;
import com.example.flashcash.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransferRepository extends CrudRepository<Transfer, Long> {
    public List<Transfer> findTransfersByFromId(Integer id);
}