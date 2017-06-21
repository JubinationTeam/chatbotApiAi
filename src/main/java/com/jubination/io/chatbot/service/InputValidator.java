/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jubination.io.chatbot.service;

import org.springframework.stereotype.Service;

/**
 *
 * @author omkar
 */
@Service
public class InputValidator {
    public boolean givenNameValidation(String givenName) {
        return true;
    }
    
    public boolean zipCodeValidation(int zipCode) {
        if(zipCode < 1000000 && zipCode > 0) {
            return true;
        } else {
            return false;
        }
    }
}
