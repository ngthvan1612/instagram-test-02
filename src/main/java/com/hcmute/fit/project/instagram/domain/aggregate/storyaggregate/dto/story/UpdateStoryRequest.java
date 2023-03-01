package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class UpdateStoryRequest {
  @JsonIgnore
  private Integer storyId;
  
  private String content;
  
  private Integer userId;
}
