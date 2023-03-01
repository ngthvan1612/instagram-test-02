package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.List;

public class ListAnnounceResponse extends SuccessfulResponse {
  public ListAnnounceResponse(List<AnnounceResponse> announceResponses) {
    super();
    this.setData(announceResponses);
  }
}
