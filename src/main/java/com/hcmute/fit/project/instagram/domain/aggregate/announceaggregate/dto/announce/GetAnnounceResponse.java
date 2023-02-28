package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce;

import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

public class GetAnnounceResponse extends SuccessfulResponse {
  public GetAnnounceResponse(AnnounceResponse announceResponse) {
    super();
    this.setData(announceResponse);
  }
}
