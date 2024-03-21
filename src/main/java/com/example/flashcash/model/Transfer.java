package com.example.flashcash.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double amount_before_fee;
    private Double amount_after_fee;

    private Date datetime;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user_from;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user_to;
}