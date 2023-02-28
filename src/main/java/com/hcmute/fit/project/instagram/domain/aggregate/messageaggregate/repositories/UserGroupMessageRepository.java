package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.UserGroupMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserGroupMessageRepository extends JpaRepository<UserGroupMessage, Integer>, ExtendUserGroupMessageRepository {
    @Override
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN TRUE ELSE FALSE END FROM UserGroupMessage u WHERE u.id = :integer AND u.deletedAt is null")
    boolean existsById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM UserGroupMessage u WHERE u.id = :integer AND u.deletedAt is null")
    Optional<UserGroupMessage> findById(@Param("integer") Integer integer);

    @Override
    @Query("SELECT u FROM UserGroupMessage u WHERE u.deletedAt is null")
    List<UserGroupMessage> findAll();


}