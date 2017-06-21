/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jubination.io.chatbot.backend.pojo.web.TestRecommendationInfo;
import java.util.ArrayList;
import java.util.Map;
import javax.json.Json;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

/**
 *
 * @author omkar
 */
@Service
public class ProductDecider {
    private String populateJSON(ArrayList<String> suggestedTests, int cost, int discountedCost) {
        
        // UNUSED METHOD. Creates valid JSON
        
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{\"result\":{\"tests\":[");
        
        for(String tests: suggestedTests) {
            stringBuilder.append("\""+tests+"\"");
            stringBuilder.append(",");
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length()-1);
        
        stringBuilder.append("]}");
        stringBuilder.append(",");
        
        stringBuilder.append("\"cost\":\""+cost+"\"");
        stringBuilder.append(",");
        
        stringBuilder.append("\"discountedCost\":\""+discountedCost+"\"");
        
        
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    
    public TestRecommendationInfo returnDecision(String age, String gender, String smoker) {
        ArrayList<String> suggestedTests = new ArrayList();
        int cost = -1;
        int discountedCost = -1;
        
        
        
        if(Integer.parseInt(age) < 40) {
            if(gender.equals("woman")) {
                if(smoker.equals("non-smoker")) {
                    suggestedTests.add("Lipid");
                    suggestedTests.add("HbA1c");
                    suggestedTests.add("CBC");
                    suggestedTests.add("TSH-LH-FSH-PRL");
                    
                    cost = 1570;
                    discountedCost = 1210;
                
                } else {
                    //smoker woman below 40
                    suggestedTests.add("Lipid");
                    suggestedTests.add("HbA1c");
                    suggestedTests.add("CBC");
                    suggestedTests.add("TSH-LH-FSH-PRL");
                    suggestedTests.add("Homocysteine");
                    
                    cost = 2280;
                    discountedCost = 1755;
                    
                    
                }
            } else {
                if(smoker.equals("non-smoker")) {
                    suggestedTests.add("Lipid");
                    suggestedTests.add("HbA1c");
                    suggestedTests.add("CBC");
                    suggestedTests.add("Vitamin D");
                    suggestedTests.add("SGPT");
                    
                    cost = 1480;
                    discountedCost = 1140;
                
                
                } else {
                    suggestedTests.add("Lipid");
                    suggestedTests.add("HbA1c");
                    suggestedTests.add("CBC");
                    suggestedTests.add("Vitamin D");
                    suggestedTests.add("SGPT");
                    
                    cost = 2190;
                    discountedCost = 1685;
                }
            }
        
        
        
        
        
        } else {
            //age not below 40
            if(smoker.equals("non-smoker")) {
                suggestedTests.add("Lipid");
                suggestedTests.add("Hba1c");
                suggestedTests.add("TSH");
                suggestedTests.add("Vitamin D");
                suggestedTests.add("Serum creatinine");
                suggestedTests.add("SGPT");
                suggestedTests.add("Hs-crp");
                
                cost = 1640;
                discountedCost = 1260;
                
            
            } else {
                suggestedTests.add("Lipid");
                suggestedTests.add("Hba1c");
                suggestedTests.add("TSH");
                suggestedTests.add("Vitamin D");
                suggestedTests.add("Serum creatinine");
                suggestedTests.add("SGPT");
                suggestedTests.add("Hs-crp");
                suggestedTests.add("Homocysteine");
                
                cost = 2350;
                discountedCost = 1805;
            }
        }
        TestRecommendationInfo testRecommendationInfo = new TestRecommendationInfo();
        testRecommendationInfo.setSuggestedTests(suggestedTests);
        testRecommendationInfo.setCost(cost);
        testRecommendationInfo.setDiscountedCost(discountedCost);
        //String result = populateJSON(suggestedTests, cost, discountedCost);
        
        return testRecommendationInfo;
    }
    
    public TestRecommendationInfo getTestChoice(Map<String, JsonElement> input) {
        String smoker, gender, age = "";
        
        
        //System.out.println("hello from productdecider "+input);
        JsonElement jsonElement = input.get("age");
        System.out.println("type "+jsonElement.getClass().getName());
        
        JsonObject temp2 = jsonElement.getAsJsonObject();
        System.out.println("here "+temp2.get("amount"));
        
        age = temp2.get("amount").toString();
        gender = input.get("gender").getAsString();
        smoker = input.get("smoker").getAsString();
        
        System.out.println("\nin productdecider "+input.keySet()+" \n");
        return returnDecision(age, gender, smoker);
    }
}
