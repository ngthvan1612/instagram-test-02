package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.Message;

import java.util.List;
import java.util.Map;

public interface ExtendMessageRepository {
    List<Message> searchMessage(Map<String, String> queries);
}