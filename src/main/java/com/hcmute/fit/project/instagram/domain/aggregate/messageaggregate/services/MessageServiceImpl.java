package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces.MessageService;
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
public class MessageServiceImpl implements MessageService {
  
  @Autowired
  private MessageRepository messageRepository;
  
  @Autowired
  private GroupMessageRepository groupMessageRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private StorageRepository storageRepository;

  public MessageServiceImpl() {

  }

  //TODO: Validate with annotation
  //TODO: check fk before create & update
  //TODO: update unique column for delete
  //TODO: swagger
  //TODO: authorize
  //TODO: hash password
  //TODO: loggggggggg

  @Override
  public SuccessfulResponse createMessage(CreateMessageRequest request) {
    //Validate
    

    //Check null
    
    Optional<User> optionalSender = this.userRepository.findById(request.getSenderId());
    User sender = null;
    
    if (optionalSender.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại người dùng nào với senderId = " + request.getSenderId());
    }
    else {
      sender = optionalSender.get();
    }
    
    
    Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
    User receiver = null;
    
    if (optionalReceiver.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại người dùng nào với receiverId = " + request.getReceiverId());
    }
    else {
      receiver = optionalReceiver.get();
    }
    
    
    Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
    GroupMessage group = null;
    
    if (optionalGroup.isEmpty()) {
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Nhóm nào với groupId = " + request.getGroupId());
    }
    else {
      group = optionalGroup.get();
    }
    
    
    Message message = new Message();
    
    message.setContent(request.getContent());
    message.setSender(sender);
    message.setReceiver(receiver);
    message.setGroup(group);

    //Save to database
    this.messageRepository.save(message);

    //Return
    MessageResponse messageDTO = new MessageResponse(message);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(messageDTO);
    response.addMessage("Tạo Tin nhắn thành công");

    return response;
  }

  @Override
  public GetMessageResponse getMessageById(Integer id) {
    if (!this.messageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
    }

    Message message = this.messageRepository.findById(id).get();
    MessageResponse messageDTO = new MessageResponse(message);
    GetMessageResponse response = new GetMessageResponse(messageDTO);

    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public ListMessageResponse searchMessages(Map<String, String> queries) {
    List<MessageResponse> listMessageResponses = this.messageRepository.searchMessage(queries)
          .stream().map(message -> new MessageResponse(message)).toList();
    
    ListMessageResponse response = new ListMessageResponse(listMessageResponses);
    response.addMessage("Lấy dữ liệu thành công");

    return response;
  }

  @Override
  public SuccessfulResponse updateMessage(UpdateMessageRequest request) {
    //Check record exists
    if (!this.messageRepository.existsById(request.getMessageId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Tin nhắn nào với id là " + request.getMessageId());
    }

    //Read data from request
    Message message = this.messageRepository.findById(request.getMessageId()).get();
    
    Optional<User> optionalSender = this.userRepository.findById(request.getSenderId());
    User sender = null;
    
    if (optionalSender.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Tin nhắn nào với senderId = " + request.getSenderId());
    }
    else {
      sender = optionalSender.get();
    }
    
    
    Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
    User receiver = null;
    
    if (optionalReceiver.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Tin nhắn nào với receiverId = " + request.getReceiverId());
    }
    else {
      receiver = optionalReceiver.get();
    }
    
    
    Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
    GroupMessage group = null;
    
    if (optionalGroup.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Không tồn tại Tin nhắn nào với groupId = " + request.getGroupId());
    }
    else {
      group = optionalGroup.get();
    }
    
    
    
    message.setContent(request.getContent());
    message.setSender(sender);
    message.setReceiver(receiver);
    message.setGroup(group);

    //Validate unique
    

    //Update last changed time
    message.setLastUpdatedAt(new Date());

    //Store
    this.messageRepository.save(message);

    //Return
    MessageResponse messageDTO = new MessageResponse(message);
    SuccessfulResponse response = new SuccessfulResponse();

    response.setData(messageDTO);
    response.addMessage("Cập nhật Tin nhắn thành công");

    return response;
  }
  

  @Override
  public SuccessfulResponse deleteMessage(Integer id) {
    if (!this.messageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Không tìm thấy Tin nhắn nào với id là " + id);
    }

    Message message = this.messageRepository.findById(id).get();
    message.setDeletedAt(new Date());
    
    this.messageRepository.save(message);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("Xóa Tin nhắn thành công");

    return response;
  }
  
}
  