package com.kururu;

/**
 * Created by kururu on 2015/11/18.
 */
class Browser extends User{

    public Browser(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        System.out.println("Welcome my Browser!");
        System.out.println("The Browser's functions are in the list:\n"+
                "1 downloadFile;\n"+"2 showFileList;\n");
    }
    /*public boolean downloadFile(){

    }*/

    /*public void showFileList(){

    }*/

}
