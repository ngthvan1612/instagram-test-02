package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.CreateStoryRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.GetStoryResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.ListStoryResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story.UpdateStoryRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface StoryService {
  SuccessfulResponse createStory(CreateStoryRequest request);
  GetStoryResponse getStoryById(Integer id);
  ListStoryResponse searchStorys(Map<String, String> queries);
  SuccessfulResponse updateStory(UpdateStoryRequest request);
  SuccessfulResponse deleteStory(Integer id);


}
