package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.entities.Announce;

import java.util.List;
import java.util.Map;

public interface ExtendAnnounceRepository {
    List<Announce> searchAnnounce(Map<String, String> queries);
}