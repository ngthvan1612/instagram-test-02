package com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;

import java.util.List;
import java.util.Map;

public interface ExtendUserRepository {
    List<User> searchUser(Map<String, String> queries);
}