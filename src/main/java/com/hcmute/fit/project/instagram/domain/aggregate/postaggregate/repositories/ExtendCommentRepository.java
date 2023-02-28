package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Comment;

import java.util.List;
import java.util.Map;

public interface ExtendCommentRepository {
    List<Comment> searchComment(Map<String, String> queries);
}