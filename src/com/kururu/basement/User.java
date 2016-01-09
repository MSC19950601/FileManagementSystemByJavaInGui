package com.kururu.basement;
import java.io.Serializable;
/**
 * Created by kururu on 2015/11/18.
 */
public class User implements Serializable {

    private String name;
    private String password;
    private String role;

    public String getName(){
        return this.name;
    }
    public String getPassword(){
        return this.password;
    }
    public String getRole(){
        return this.role;
    }

    public void setName(String nameInput){
        this.name = nameInput;
    }
    public void setPassword(String passwordInput) {
        this.password = passwordInput;
    }
    public void setRole(String roleInput){
        this.role = roleInput;
    }

    public boolean setter(String nameInput, String passwordInput, String roleInput){
        if(!((nameInput == null || nameInput.isEmpty())
                && (passwordInput == null || passwordInput.isEmpty())
                && (roleInput == null || roleInput.isEmpty()))){
            setName(nameInput);
            setPassword(passwordInput);
            setRole(roleInput);
            return true;
        }else{
            return false;
        }
    }



    public User(String nameInput, String passwordInput, String roleInput){
        if(setter(nameInput,passwordInput,roleInput)){
        }else{
            System.out.println("User init failed!");
        }
    }

}
