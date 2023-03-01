package com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.CreateFollowerRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.GetFollowerResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.ListFollowerResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.followeraggregate.dto.follower.UpdateFollowerRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface FollowerService {
  SuccessfulResponse createFollower(CreateFollowerRequest request);
  GetFollowerResponse getFollowerById(Integer id);
  ListFollowerResponse searchFollowers(Map<String, String> queries);
  SuccessfulResponse updateFollower(UpdateFollowerRequest request);
  SuccessfulResponse deleteFollower(Integer id);


}
