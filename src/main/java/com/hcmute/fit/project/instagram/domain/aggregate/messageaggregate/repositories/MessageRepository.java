package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Integer>, ExtendMessageRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Message u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Message u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<Message> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM Message u WHERE u.deletedAt is null")
    List<Message> findAll();


}