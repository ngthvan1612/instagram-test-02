package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.CreatePostRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.GetPostResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.ListPostResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.dto.post.UpdatePostRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface PostService {
    SuccessfulResponse createPost(CreatePostRequest request);

    GetPostResponse getPostById(Integer id);

    ListPostResponse searchPosts(Map<String, String> queries);

    SuccessfulResponse updatePost(UpdatePostRequest request);

    SuccessfulResponse deletePost(Integer id);


}
