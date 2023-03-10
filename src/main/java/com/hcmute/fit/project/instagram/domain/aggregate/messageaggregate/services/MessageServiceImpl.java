package com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services;

import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.dto.message.*;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.GroupMessage;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.entities.Message;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.GroupMessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.repositories.MessageRepository;
import com.hcmute.fit.project.instagram.domain.aggregate.messageaggregate.services.interfaces.MessageService;
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
public class MessageServiceImpl implements MessageService {
  private final static Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);
  
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
        .addMessage("Kh??ng t???n t???i ng?????i d??ng n??o v???i senderId = " + request.getSenderId());
    }
    else {
      sender = optionalSender.get();
    }
    
    
    Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
    User receiver = null;
    
    if (optionalReceiver.isPresent())
      receiver = optionalReceiver.get();
    
    
    Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
    GroupMessage group = null;
    
    if (optionalGroup.isPresent())
      group = optionalGroup.get();
    
    
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
    response.addMessage("T???o Tin nh???n th??nh c??ng");

    LOG.info("Created message with id = " + message.getId());
    return response;
  }

  @Override
  public GetMessageResponse getMessageById(Integer id) {
    if (!this.messageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tin nh???n n??o v???i id l?? " + id);
    }

    Message message = this.messageRepository.findById(id).get();
    MessageResponse messageDTO = new MessageResponse(message);
    GetMessageResponse response = new GetMessageResponse(messageDTO);

    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public ListMessageResponse searchMessages(Map<String, String> queries) {
    List<MessageResponse> listMessageResponses = this.messageRepository.searchMessage(queries)
          .stream().map(message -> new MessageResponse(message)).toList();
    
    ListMessageResponse response = new ListMessageResponse(listMessageResponses);
    response.addMessage("L???y d??? li???u th??nh c??ng");

    return response;
  }

  @Override
  public SuccessfulResponse updateMessage(UpdateMessageRequest request) {
    //Check record exists
    if (!this.messageRepository.existsById(request.getMessageId())) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tin nh???n n??o v???i id l?? " + request.getMessageId());
    }

    //Read data from request
    Message message = this.messageRepository.findById(request.getMessageId()).get();
    
    Optional<User> optionalSender = this.userRepository.findById(request.getSenderId());
    User sender = null;
    
    if (optionalSender.isEmpty()) { 
      throw ServiceExceptionFactory.badRequest()
        .addMessage("Kh??ng t???n t???i Tin nh???n n??o v???i senderId = " + request.getSenderId());
    }
    else {
      sender = optionalSender.get();
    }
    
    
    Optional<User> optionalReceiver = this.userRepository.findById(request.getReceiverId());
    User receiver = null;
    
    if (optionalReceiver.isPresent()) {
      receiver = optionalReceiver.get();
    }
    
    
    Optional<GroupMessage> optionalGroup = this.groupMessageRepository.findById(request.getGroupId());
    GroupMessage group = null;
    
    if (optionalGroup.isPresent()) {
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
    response.addMessage("C???p nh???t Tin nh???n th??nh c??ng");

    LOG.info("Updated message with id = " + message.getId());
    return response;
  }
  

  @Override
  public SuccessfulResponse deleteMessage(Integer id) {
    if (!this.messageRepository.existsById(id)) {
      throw ServiceExceptionFactory.notFound()
        .addMessage("Kh??ng t??m th???y Tin nh???n n??o v???i id l?? " + id);
    }

    Message message = this.messageRepository.findById(id).get();
    message.setDeletedAt(new Date());
    
    this.messageRepository.save(message);

    SuccessfulResponse response = new SuccessfulResponse();
    response.addMessage("X??a Tin nh???n th??nh c??ng");

    LOG.info("Deleted message with id = " + message.getId());
    return response;
  }
  
}
  