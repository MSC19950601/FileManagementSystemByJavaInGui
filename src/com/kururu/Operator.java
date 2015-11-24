package com.kururu;

/**
 * Created by kururu on 2015/11/18.
 */
class Operator extends User{

    public Operator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        System.out.println("Welcome my Operator!");
        System.out.println("The Operator's functions are in the list:\n"+
                "1 uploadFile;\n"+"2 downloadFile;\n"+"3 showFileList;\n");
    }

    /*public boolean uploadFile(){

    }*/

    /*public boolean downlaodFile(){

    }*/

    /*public void showFileList(){

    }*/

}
