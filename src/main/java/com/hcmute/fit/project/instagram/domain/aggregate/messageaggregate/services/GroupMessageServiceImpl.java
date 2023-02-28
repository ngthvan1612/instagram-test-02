package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.groupmessage.*;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces.GroupMessageService;
import com.hcmute.fit.project.instagram.domain.base.StorageRepository;
import com.hcmute.fit.project.instagram.domain.base.SuccessfulResponse;
import com.hcmute.fit.project.instagram.domain.exception.ServiceExceptionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class GroupMessageServiceImpl implements GroupMessageService {
  
  @Autowired
  private GroupMessageRepository groupMessageRepository;
  
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
    
    GroupMessage groupMessage = new GroupMessage();
    
    groupMessage.setDisplayName(request.getDisplayName());

    //Save to database
    this.groupMessageRepository.save(groupMessage);

    //Return
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(groupMessageDTO);
    response.addMessage("Tạo Nhóm thành công");

    return response;
  }

  @Override
  public GetGroupMessageResponse getGroupMessageById(Integer id) {
    if (!this.groupMessageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Nhóm nào với id là " + id);
    }

    GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    GetGroupMessageResponse response = new GetGroupMessageResponse(groupMessageDTO);

    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public ListGroupMessageResponse searchGroupMessages(Map<String, String> queries) {
    List<GroupMessageResponse> listGroupMessageResponses = this.groupMessageRepository.searchGroupMessage(queries)
          .stream().map(groupMessage -> new GroupMessageResponse(groupMessage)).toList();
    
    ListGroupMessageResponse response = new ListGroupMessageResponse(listGroupMessageResponses);
    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public SuccessfulResponse updateGroupMessage(UpdateGroupMessageRequest request) {
    //Check record exists
    if (!this.groupMessageRepository.existsById(request.getGroupMessageId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Nhóm nào với id là " + request.getGroupMessageId());
    }

    //Read data from request
    GroupMessage groupMessage = this.groupMessageRepository.findById(request.getGroupMessageId()).get();
    
    
    groupMessage.setDisplayName(request.getDisplayName());

    //Validate unique
    

    //Update last changed time
    groupMessage.setLastUpdatedAt(new Date());

    //Store
    this.groupMessageRepository.save(groupMessage);

    //Return
    GroupMessageResponse groupMessageDTO = new GroupMessageResponse(groupMessage);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(groupMessageDTO);
    response.addMessage("Cập nhật Nhóm thành công");

    return response;
  }
  

  @Override
  public SuccessfulResponse deleteGroupMessage(Integer id) {
    if (!this.groupMessageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Nhóm nào với id là " + id);
    }

    GroupMessage groupMessage = this.groupMessageRepository.findById(id).get();
    groupMessage.setDeletedAt(new Date());
    
    this.groupMessageRepository.save(groupMessage);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("Xóa Nhóm thành công");

    return response;
  }
  
}
  