package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListStoryResponse extends SuccessfulResponse {
  public ListStoryResponse(List<StoryResponse> storyResponses) {
    super();
    this.setData(storyResponses);
  }
}
