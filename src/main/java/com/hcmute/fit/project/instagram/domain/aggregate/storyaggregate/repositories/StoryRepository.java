package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.entities.Story;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StoryRepository extends JpaRepository<Story, Integer>, ExtendStoryRepository {
  @Override
  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Story u WHERE u.id = :integer AND u.deletedAt is null")
  boolean existsById(@Param("integer") Integer integer);

  @Override
  @Query("SELECT u FROM Story u WHERE u.id = :integer AND u.deletedAt is null")
  Optional<Story> findById(@Param("integer") Integer integer);

  @Override
  @Query("SELECT u FROM Story u WHERE u.deletedAt is null")
  List<Story> findAll();
  
  
}