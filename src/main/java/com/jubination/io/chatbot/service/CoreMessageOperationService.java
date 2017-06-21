    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.service;

import com.jubination.io.chatbot.model.pojo.Chatlet;
import com.jubination.io.chatbot.model.pojo.ChatletTag;
import com.jubination.io.chatbot.model.pojo.MessageSet;
import com.jubination.io.chatbot.model.pojo.ChatletMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIServiceException;
import ai.api.model.AIRequest;
import ai.api.model.AIResponse;
import com.jubination.io.chatbot.backend.pojo.web.TestRecommendationInfo;
import java.util.HashMap;

/**
 *
 * @author MumbaiZone
 */
@Service
@Transactional
public class CoreMessageOperationService {
        @Autowired
        PostProcessingService postProcessor;
        @Autowired
        ProductDecider productDecider;
        @Autowired
        InputValidator inputValidator;
        HashMap<String, AIDataService> sessionIdMap = new HashMap<>();
        final private static String apiKey="e51544345653434b8958816fb65a1ab9";
        
        boolean firstTime = true;
    
         //Bussiness Logic
        
        public String returnUserReadableString(TestRecommendationInfo testRecommendationInfo) {
            StringBuilder suggestionDecision = new StringBuilder("We would like to suggest the following tests for you:");
            
            for(String val:testRecommendationInfo.getSuggestedTests()){
                suggestionDecision.append("<br/>"+val);
            }
            
            suggestionDecision.append("<br/><br/>Which amounts to Rs <strike>"+
                    testRecommendationInfo.getCost()+"</strike> <b>"+
                    testRecommendationInfo.getDiscountedCost()+"</b>. Press Yes to go ahead with the booking.");
            
            return suggestionDecision.toString();
        }
        
        
        
        public Chatlet getNextChatlet(ChatletTag chatletTag) throws AIServiceException{
            System.out.println("in id:::::"+chatletTag.getChatletId());
            Chatlet chatlet=new Chatlet();
            
            chatlet.setAnswerType("text");
            
            if(chatletTag.getChatletId().equals("4")) {
                
                if(chatletTag.getAnswer().equalsIgnoreCase("no")) {
                    MessageSet messageSet = new MessageSet();
                    messageSet.getMessages().add(new ChatletMessage("text", "Alright, it was nice talking to you."));
                    chatlet.getBotMessages().add(messageSet);
                    chatlet.setId("1");
                } else{
                    chatletTag.setAnswer("get the personal details");
                    chatlet = apiAiIn(chatletTag,chatlet);
                }
            } 
            else {
                
                
                //to do
                //validate -- chatletid
                System.out.println("chatlet ID is "+chatletTag.getChatletId().toString());
                String regex = "[0-9]+";
                if(chatletTag.getChatletId().equals("2")) {
                    if(chatletTag.getAnswer().length()==6 && chatletTag.getAnswer().matches(regex)) {
                        chatletTag.setAnswer("55555");
                        System.out.println("calling apiAiIn from 55555");
                        apiAiIn(chatletTag, chatlet);
                    } else {
                        //send to api.ai with the zipcode "55555"
                        MessageSet messageSet = new MessageSet();
                        messageSet.getMessages().add(new ChatletMessage("text","Please enter a valid zip code."));
                        chatlet.getBotMessages().add(messageSet);
                    }
                } else if(chatletTag.getChatletId().equals("3")) {
                    chatletTag.setAnswer("Alex");
                    System.out.println("calling apiAiIn from firstname");
                    apiAiIn(chatletTag, chatlet);
                } else {
                    System.out.println("calling apiAiIn from else");
                    System.out.println(chatletTag.getChatletId());
                    chatlet = apiAiIn(chatletTag,chatlet);
                }
                
                //--to do
            }
            System.out.println("out id:::::::"+chatlet.getId());
            return chatlet;
        }
        
        
       private Chatlet apiAiIn(ChatletTag chatletTag, Chatlet chatlet) throws AIServiceException{
           /*
           if the user has been asked for a name of a zipcode then you just accept the input, validate it and send 
           standard predetermined inputs to api.ai
           
           if the chatlet.getId equals apiai_firstname of apiai_zipcode then we pass control to the validator 
           InputValidator. He will return if the format is correct. If not then ask the user to write it correctly.
           
           */
           
            //-----------API AI---------------------
            String usersRequestToApiAi = chatletTag.getAnswer();
            
            if(sessionIdMap.get(chatletTag.getWebId()) == null){
                AIConfiguration configuration = new AIConfiguration(apiKey);
                sessionIdMap.put(chatletTag.getWebId(), new AIDataService(configuration));
                System.out.println("Added to the hashmap:::::::::::::"+sessionIdMap.toString());

            }
            AIDataService dataService = sessionIdMap.get(chatletTag.getWebId());

            System.out.println("Query (User asking api.ai): " + usersRequestToApiAi);

            AIRequest apiAiRequest = new AIRequest(usersRequestToApiAi);
            AIResponse apiAiResponse = dataService.request(apiAiRequest);

            if(!customApiAiOut(chatlet, apiAiResponse)){
                chatlet = apiAiOut(chatlet, apiAiResponse);
            }

//            if (apiAiResponse.getStatus().getCode() == 200) {
//                if(!customApiAiOut(chatlet, apiAiResponse)){
//                    chatlet = apiAiOut(chatlet, apiAiResponse);
//                }
//            } else {
//                chatlet = apiAiOutErr(chatlet, apiAiResponse);
//            }
            //-----------------------------------------
            return chatlet;
       }
       
       
       
       private Chatlet apiAiOut(Chatlet chatlet,AIResponse apiAiResponse){
            System.out.println("Response: "+apiAiResponse.getResult().getFulfillment().getSpeech());
            MessageSet messageSet = new MessageSet();
            
            String apiaiTextResponse = apiAiResponse.getResult().getFulfillment().getSpeech();
            if(apiaiTextResponse.contains("zip code")) {
                chatlet.setId("2");
            } else if(apiaiTextResponse.contains("first name")) {
                chatlet.setId("3");
            } else {
                chatlet.setId("1");
            }
            messageSet.getMessages().add(new ChatletMessage("text",apiaiTextResponse));
            chatlet.getBotMessages().add(messageSet);
            
            return chatlet;
       }
       
       private Chatlet apiAiOutErr(Chatlet chatlet,AIResponse apiAiResponse){
            System.err.println(apiAiResponse.getStatus().getErrorDetails());
            MessageSet messageSet = new MessageSet();
            messageSet.getMessages().add(new ChatletMessage("text",apiAiResponse.getStatus().getErrorDetails()));
            chatlet.getBotMessages().add(messageSet);

            chatlet.setId("404");
            return chatlet;
       }
       
       
       
       private Boolean customApiAiOut(Chatlet chatlet, AIResponse apiAiResponse){
            TestRecommendationInfo testRecommendationInfo = new TestRecommendationInfo(); //contains the suggested tests and their associated cost
            MessageSet messageSet = new MessageSet();
            String apiaiTextResponse = apiAiResponse.getResult().getFulfillment().getSpeech();
            
            
            System.out.println("response is "+apiAiResponse);
            if(apiaiTextResponse.
                    trim().
                    equals("Thanks a bunch. Let me suggest a customized package for you")) {
                System.out.println("elementary details received");

                //signifies end of session. which means that all the REQUIRED variables have been received. intent will be over now

                testRecommendationInfo = productDecider.getTestChoice(apiAiResponse.getResult().getContexts().get(0).getParameters());
                //System.out.println("FINAL RESULT IS "+testRecommendationInfo);

                //RESULT
                chatlet.setAnswerType("option");
                messageSet.getMessages().add(new ChatletMessage("text",returnUserReadableString(testRecommendationInfo)));
                chatlet.getBotMessages().add(messageSet);
                chatlet.getOptions().put("Yes", null);
                chatlet.getOptions().put("No", null);

                chatlet.setId("4");
                return true;
            }
            else if(apiaiTextResponse.
                    trim().
                    equals("OK, I have made a booking. Our agent will get in touch with you soon.")) {
                    chatlet.setAnswerType("option");
                    messageSet.getMessages().add(new ChatletMessage("text","OK, I have made a booking. Our agent will get in touch with you soon."));
                    chatlet.getBotMessages().add(messageSet);
                    chatlet.setId("200");
                    return true;
                }
            
            
            return false;
       }
}
