package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.UserTagFriendPost;

import java.util.List;
import java.util.Map;

public interface ExtendUserTagFriendPostRepository {
    List<UserTagFriendPost> searchUserTagFriendPost(Map<String, String> queries);
}