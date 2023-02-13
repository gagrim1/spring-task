package com.romanov.spring_task.model.entity;

import com.romanov.spring_task.model.enumurated.Content;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "quotes")
@Data
public class QuoteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    private Content content;
    @Column(name = "update_date")
    private LocalDate updateDate;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "vote_id")
    private VoteEntity vote;
}
