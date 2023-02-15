package com.romanov.spring_task.repository;

import com.romanov.spring_task.model.entity.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<QuoteEntity, Long> {
    List<QuoteEntity> findAllByVoteId(Long voteId);
    Optional<QuoteEntity> findByUserIdAndVoteId(Long userId, Long voteId);
}
