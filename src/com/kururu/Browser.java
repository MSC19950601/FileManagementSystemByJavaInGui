package com.kururu;

/**
 * Created by kururu on 2015/11/18.
 */
class Browser extends User{

    public Browser(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        super.getter();
    }
    /*public boolean downloadFile(){

    }*/

    /*public void showFileList(){

    }*/

}
