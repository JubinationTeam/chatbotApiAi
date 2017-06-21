/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.model.pojo;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 * @author MumbaiZone
 */

@Document(collection = "chatlet_tag")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChatletTag {
     @Id
    String id;
    String userId;
    String tag;
    String answer; //
    String answerType; //text
    String tagType;
    String chatletId;
    String sessionId;
    String webId;
    String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

 
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

   

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTagType() {
        return tagType;
    }

    public void setTagType(String tagType) {
        this.tagType = tagType;
    }

    

    public String getChatletId() {
        return chatletId;
    }

    public void setChatletId(String chatletId) {
        this.chatletId = chatletId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String answerType) {
        this.answerType = answerType;
    }

    public String getWebId() {
        return webId;
    }

    public void setWebId(String webId) {
        this.webId = webId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ChatletTag{" + "id=" + id + ", userId=" + userId + ", tag=" + tag + ", answer=" + answer + ", answerType=" + answerType + ", tagType=" + tagType + ", chatletId=" + chatletId + ", sessionId=" + sessionId + ", webId=" + webId + ", name=" + name + '}';
    }
    
}
