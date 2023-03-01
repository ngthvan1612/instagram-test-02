package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.services.interfaces;

import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce.CreateAnnounceRequest;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce.GetAnnounceResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce.ListAnnounceResponse;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce.UpdateAnnounceRequest;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;

import java.util.Map;

public interface AnnounceService {
  SuccessfulResponse createAnnounce(CreateAnnounceRequest request);
  GetAnnounceResponse getAnnounceById(Integer id);
  ListAnnounceResponse searchAnnounces(Map<String, String> queries);
  SuccessfulResponse updateAnnounce(UpdateAnnounceRequest request);
  SuccessfulResponse deleteAnnounce(Integer id);


}
