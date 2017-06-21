/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.model.pojo;

import com.jubination.io.chatbot.model.dao.CascadeSave;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedHashMap;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author MumbaiZone
 */

@Document(collection = "chatlet")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Chatlet {
    @Id
    private String id;
     @DBRef
//      @CascadeSave
    private List<MessageSet> botMessages = new ArrayList<>();
    private LinkedHashMap<String,String> options = new LinkedHashMap<>();
    
    private String answerType;
    
    private Boolean refreshSession;
   
      

    public Chatlet() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

   
    public List<MessageSet> getBotMessages() {
        return botMessages;
    }

    public void setBotMessages(List<MessageSet> botMessages) {
        this.botMessages = botMessages;
    }



    public LinkedHashMap<String, String> getOptions() {
        return options;
    }

    public void setOptions(LinkedHashMap<String, String> options) {
        this.options = options;
    }

   

    public Boolean getRefreshSession() {
        return refreshSession;
    }

    public void setRefreshSession(Boolean refreshSession) {
        this.refreshSession = refreshSession;
    }

   

    
    
}
