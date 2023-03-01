package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetStoryResponse extends SuccessfulResponse {
  public GetStoryResponse(StoryResponse storyResponse) {
    super();
    this.setData(storyResponse);
  }
}
