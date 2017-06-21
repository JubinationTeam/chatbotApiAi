/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.model.pojo;

import com.jubination.io.chatbot.model.dao.CascadeSave;
import java.util.ArrayList;
import java.util.List;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author MumbaiZone
 */
@Document(collection = "message_set")
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageSet {
  @Id
    String id;
  @DBRef
//          @CascadeSave
    List<ChatletMessage> messages = new ArrayList<>();

    public List<ChatletMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatletMessage> messages) {
        this.messages = messages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @Override
    public String toString(){
        StringBuilder value = new StringBuilder("");
        for(ChatletMessage message:getMessages()){
            value.append(message);
        }
        return messages.toString();
    }
    
}
