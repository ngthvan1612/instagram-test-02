package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Integer>, ExtendPostRepository {
  @Override
  @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM Post u WHERE u.id = :integer AND u.deletedAt is null")
  boolean existsById(@Param("integer") Integer integer);

  @Override
  @Query("SELECT u FROM Post u WHERE u.id = :integer AND u.deletedAt is null")
  Optional<Post> findById(@Param("integer") Integer integer);

  @Override
  @Query("SELECT u FROM Post u WHERE u.deletedAt is null")
  List<Post> findAll();
  
  
}