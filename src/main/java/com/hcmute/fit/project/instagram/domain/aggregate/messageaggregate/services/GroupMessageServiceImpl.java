package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.*;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.entities.User;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.repositories.UserRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.useraggregate.services.UserServiceImpl;
import com.hcmute.fit.project.instagram.domain.base.StorageRepository;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import com.hcmute.fit.project.instagram.domain.exception.ServiceExceptionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
  @Autowired
  private GroupMessageRepository groupMessageRepository;
  
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public GroupMessageServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createGroupMessage(CreateGroupMessageRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalAdmin = this.userRepository.findById(request.getAdminId());
    User admin = null;
    
    if (optionalAdmin.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i ng?????i d??ng n??o v???i adminId = " + request.getAdminId());
    }
    else {
      admin = optionalAdmin.get();
    }
    
    
    GroupMessage groupMessage = new GroupMessage();
    
    groupMessage.setDisplayName(request.getDisplayName());
    groupMessage.setAdmin(admin);

    //Save to database
    this.groupMessageRepository.save(groupMessage);

    //Return
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(groupMessageDTO);
    response.addMessage("T???o Nh??m th??nh c??ng");

    LOG.info("Created groupMessage with id = " + groupMessage.getId());
    return response;
  }

  @Override
  public GetGroupMessageResponse getGroupMessageById(Integer id) {
    if (!this.groupMessageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Nh??m n??o v???i id l?? " + id);
    }

    GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    GetGroupMessageResponse response = new GetGroupMessageResponse(groupMessageDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListGroupMessageResponse searchGroupMessages(Map<String, String> queries) {
    List<GroupMessageResponse> listGroupMessageResponses = this.groupMessageRepository.searchGroupMessage(queries)
          .stream().map(groupMessage -> new GroupMessageResponse(groupMessage)).toList();
    
    ListGroupMessageResponse response = new ListGroupMessageResponse(listGroupMessageResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updateGroupMessage(UpdateGroupMessageRequest request) {
    //Check record exists
    if (!this.groupMessageRepository.existsById(request.getGroupMessageId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Nh??m n??o v???i id l?? " + request.getGroupMessageId());
    }

    //Read data from request
    GroupMessage groupMessage = this.groupMessageRepository.findById(request.getGroupMessageId()).get();
    
    Optional<User> optionalAdmin = this.userRepository.findById(request.getAdminId());
    User admin = null;
    
    if (optionalAdmin.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Nh??m n??o v???i adminId = " + request.getAdminId());
    }
    else {
      admin = optionalAdmin.get();
    }
    
    
    
    groupMessage.setDisplayName(request.getDisplayName());
    groupMessage.setAdmin(admin);

    //Validate unique
    

    //Update last changed time
    groupMessage.setLastUpdatedAt(new Date());

    //Store
    this.groupMessageRepository.save(groupMessage);

    //Return
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(groupMessageDTO);
    response.addMessage("C???p nh???t Nh??m th??nh c??ng");

    LOG.info("Updated groupMessage with id = " + groupMessage.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteGroupMessage(Integer id) {
    if (!this.groupMessageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Nh??m n??o v???i id l?? " + id);
    }

    GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
    groupMessage.setDeletedAt(new Date());
    
    this.groupMessageRepository.save(groupMessage);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a Nh??m th??nh c??ng");

    LOG.info("Deleted groupMessage with id = " + groupMessage.getId());
    return response;
  }
  
}
  