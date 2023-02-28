package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.entities.Follower;

import java.util.List;
import java.util.Map;

public interface ExtendFollowerRepository {
    List<Follower> searchFollower(Map<String, String> queries);
}