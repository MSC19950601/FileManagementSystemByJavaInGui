package com.kururu;

/**
 * Created by kururu on 2015/11/18.
 */
class Administrator extends User{

    public Administrator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        super.getter();
    }

    /*public boolean changeUserInfo(){

    }*/

    /*public boolean delUser(){

    }*/

    /*public boolean addUser(){

    }*/

    /*public void listUser(){

    }*/

}
