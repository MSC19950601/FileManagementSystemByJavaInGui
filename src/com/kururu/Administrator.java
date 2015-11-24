package com.kururu;

/**
 * Created by kururu on 2015/11/18.
 */
class Administrator extends User{

    public Administrator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        System.out.println("Welcome my Administrator!");
        System.out.println("The Administrator's functions are in the list:\n"+
        "1 changeUserInfo;\n"+"2 delUser;\n"+"3 addUser;\n"+"4 listUser;\n");

        
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
