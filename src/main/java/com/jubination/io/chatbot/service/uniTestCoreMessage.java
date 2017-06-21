/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.service;

import ai.api.AIDataService;
import com.jubination.io.chatbot.model.pojo.Chatlet;
import com.jubination.io.chatbot.model.pojo.ChatletMessage;
import com.jubination.io.chatbot.model.pojo.ChatletTag;
import com.jubination.io.chatbot.model.pojo.MessageSet;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author omkar
 */
public class uniTestCoreMessage {
    @Autowired
    PostProcessingService postProcessor;
    @Autowired
    ProductDecider productDecider;
    @Autowired
    InputValidator inputValidator;
    HashMap<String, AIDataService> sessionIdMap = new HashMap<>();
    final private static String apiKey="e51544345653434b8958816fb65a1ab9";
    
    public Chatlet getNextChatlet(ChatletTag chatletTag) {
        Chatlet chatlet = new Chatlet();
        chatlet.setAnswerType("text");
        
        if(chatletTag.getChatletId().equals("customproduct")) {
            
            MessageSet messageSet = new MessageSet();
            messageSet.getMessages().add(new ChatletMessage("text","Alright, it was nice talking to you."));
            
            chatlet.getBotMessages().add(messageSet);
            chatlet.setId("apiai");
        } else {
            chatletTag.setAnswer("get the personal details");
            chatlet = apiAiIn(chatletTag, chatlet);
            
        }
        
        return null;
    }

    private Chatlet apiAiIn(ChatletTag chatletTag, Chatlet chatlet) {
        
        
        return null;
    }
}
