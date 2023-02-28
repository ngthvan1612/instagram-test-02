package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Post;

import java.util.List;
import java.util.Map;

public interface ExtendPostRepository {
    List<Post> searchPost(Map<String, String> queries);
}