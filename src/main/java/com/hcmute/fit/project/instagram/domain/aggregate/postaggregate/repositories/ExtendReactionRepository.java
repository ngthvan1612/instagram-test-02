package com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.postaggregate.entities.Reaction;

import java.util.List;
import java.util.Map;

public interface ExtendReactionRepository {
    List<Reaction> searchReaction(Map<String, String> queries);
}