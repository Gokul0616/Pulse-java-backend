
package com.pulse.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pulse.demo.Modals.MessageModal;

@Repository
public interface MessageRepository extends JpaRepository<MessageModal, Long> {

    void getMessageBySenderId(String id);

    public MessageModal save(MessageModal message); 

    void deleteMessageByMessageId(String messageId);
}
 