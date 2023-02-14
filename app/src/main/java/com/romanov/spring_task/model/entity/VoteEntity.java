package com.romanov.spring_task.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "votes")
@Data
public class VoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
}
