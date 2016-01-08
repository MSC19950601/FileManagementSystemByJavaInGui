package com.kururu.basement;

import java.io.Serializable;

/**
 * Created by kururu on 2015/11/18.
 */
public class Operator extends User implements Serializable{

    public Operator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

}
