package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.dto.story;

import lombok.Data;

@Data
public class CreateStoryRequest {
  
  
  private String content;
  
  private Integer userId;
  
}