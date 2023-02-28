package com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.dto.announce.*;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.entities.Announce;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.repositories.AnnounceRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.announceaggregate.services.interfaces.AnnounceService;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.fit.project.instagram.domain.base.StorageRepository;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import com.hcmute.fit.project.instagram.domain.exception.ServiceExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AnnounceServiceImpl implements AnnounceService {
  
  @Autowired
  private AnnounceRepository announceRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public AnnounceServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createAnnounce(CreateAnnounceRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại người dùng nào với userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    Announce announce = new Announce();
    
    announce.setContent(request.getContent());
    announce.setSeen(request.getSeen());
    announce.setUser(user);

    //Save to database
    this.announceRepository.save(announce);

    //Return
    AnnounceResponse announceDTO = new AnnounceResponse(announce);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(announceDTO);
    response.addMessage("Tạo Thông báo thành công");

    return response;
  }

  @Override
  public GetAnnounceResponse getAnnounceById(Integer id) {
    if (!this.announceRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Thông báo nào với id là " + id);
    }

    Announce announce = this.announceRepository.findById(id).get();
    AnnounceResponse announceDTO = new AnnounceResponse(announce);
    GetAnnounceResponse response = new GetAnnounceResponse(announceDTO);

    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public ListAnnounceResponse searchAnnounces(Map<String, String> queries) {
    List<AnnounceResponse> listAnnounceResponses = this.announceRepository.searchAnnounce(queries)
          .stream().map(announce -> new AnnounceResponse(announce)).toList();
    
    ListAnnounceResponse response = new ListAnnounceResponse(listAnnounceResponses);
    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public SuccessfulResponse updateAnnounce(UpdateAnnounceRequest request) {
    //Check record exists
    if (!this.announceRepository.existsById(request.getAnnounceId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Thông báo nào với id là " + request.getAnnounceId());
    }

    //Read data from request
    Announce announce = this.announceRepository.findById(request.getAnnounceId()).get();
    
    Optional<User> optionalUser = this.userRepository.findById(request.getUserId());
    User user = null;
    
    if (optionalUser.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Thông báo nào với userId = " + request.getUserId());
    }
    else {
      user = optionalUser.get();
    }
    
    
    
    announce.setContent(request.getContent());
    announce.setSeen(request.getSeen());
    announce.setUser(user);

    //Validate unique
    

    //Update last changed time
    announce.setLastUpdatedAt(new Date());

    //Store
    this.announceRepository.save(announce);

    //Return
    AnnounceResponse announceDTO = new AnnounceResponse(announce);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(announceDTO);
    response.addMessage("Cập nhật Thông báo thành công");

    return response;
  }
  

  @Override
  public SuccessfulResponse deleteAnnounce(Integer id) {
    if (!this.announceRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Thông báo nào với id là " + id);
    }

    Announce announce = this.announceRepository.findById(id).get();
    announce.setDeletedAt(new Date());
    
    this.announceRepository.save(announce);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("Xóa Thông báo thành công");

    return response;
  }
  
}
  