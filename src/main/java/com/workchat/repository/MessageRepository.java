package com.workchat.repository;

import com.workchat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MessageRepository extends MongoRepository<Message,String> {

    List<Message> findBySenderIdAndRecipientId(String senderId, String recipientId);

    @Query("{ $or: [ { $and: [ { 'senderId': ?0 }, { 'recipientId': ?1 } ] }, { $and: [ { 'senderId': ?1 }, { 'recipientId': ?0 } ] } ] }")
    List<Message> findChatHistory(String senderId, String recipientId);

}
