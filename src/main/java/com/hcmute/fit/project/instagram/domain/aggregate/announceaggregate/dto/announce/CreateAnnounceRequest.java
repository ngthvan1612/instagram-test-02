package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce;

import lombok.Data;

@Data
public class CreateAnnounceRequest {


    private String content;


    private Boolean seen;

    private Integer userId;

}