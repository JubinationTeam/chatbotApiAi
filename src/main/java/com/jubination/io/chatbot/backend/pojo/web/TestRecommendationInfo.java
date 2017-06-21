/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.backend.pojo.web;

import java.util.ArrayList;

/**
 *
 * @author omkar
 */
public class TestRecommendationInfo {
    ArrayList<String> suggestedTests = new ArrayList();
    int cost, discountedCost;

    public ArrayList<String> getSuggestedTests() {
        return suggestedTests;
    }

    public void setSuggestedTests(ArrayList<String> suggestedTests) {
        this.suggestedTests = suggestedTests;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getDiscountedCost() {
        return discountedCost;
    }

    public void setDiscountedCost(int discountedCost) {
        this.discountedCost = discountedCost;
    }

    public TestRecommendationInfo() {
    }
    
    
}
