package com.example.flashcash.model;

import jakarta.persistence.*;

@Entity
public class Link {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user1;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user2;
}