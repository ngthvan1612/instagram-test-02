package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.GroupMessage;

import java.util.List;
import java.util.Map;

public interface ExtendGroupMessageRepository {
    List<GroupMessage> searchGroupMessage(Map<String, String> queries);
}