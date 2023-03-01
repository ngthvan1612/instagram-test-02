package com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.storyaggregate.entities.Story;

import java.util.List;
import java.util.Map;

public interface ExtendStoryRepository {
    List<Story> searchStory(Map<String, String> queries);
}