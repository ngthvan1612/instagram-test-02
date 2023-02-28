package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.UserGroupMessage;

import java.util.List;
import java.util.Map;

public interface ExtendUserGroupMessageRepository {
    List<UserGroupMessage> searchUserGroupMessage(Map<String, String> queries);
}