package com.kururu;

import java.util.Enumeration;
import java.util.Scanner;

/**
 * Created by kururu on 2015/11/18.
 */
class Administrator extends User{

    static int menuNum;

    static String
            changeName,changePassword,changeRole,
            delName,
            addName,addPassword,addRole;

    Scanner jaclinForAdmini = new Scanner(System.in);

    public Administrator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);

    }

    public void showMenu(){

        System.out.println("Welcome my Administrator!");
        System.out.println("The Administrator's functions are in the list:\n"+
        "1 changeUserInfo;\n"+"2 delUser;\n"+"3 addUser;\n"+"4 listUser;\n"+"0 exit.");
        System.out.println("Please choose your functions!");
        menuNum = jaclinForAdmini.nextInt();
        AdministratorPanel:
        switch (menuNum){
            //Exit func
            case 0: {
                System.exit(0);
            }
            //change func
            case 1: {
                if (changeUserInfo()) {
                    System.out.println("Change successfully!");
                }else{
                    System.out.println("Change failed!");
                }
                break AdministratorPanel;
            }
            //Delete func
            case 2:{
                if (delUser()) {
                    System.out.println("Delete successfully!");
                }else{
                    System.out.println("Delete failed!");
                }
                break AdministratorPanel;
            }
            //Add func
            case 3:{
                if (addUser()) {
                    System.out.println("Add successfully!");
                }else{
                    System.out.println("Add failed!");
                }
                break AdministratorPanel;
            }
            //showlist func
            case 4:{
                listUser();
                break AdministratorPanel;
            }
        }
    }

    public boolean changeUserInfo(){
        System.out.println("Please input changed name:");
        changeName = jaclinForAdmini.next();
        System.out.println("Please input changed password:");
        changePassword = jaclinForAdmini.next();
        System.out.println("Please input changed role:");
        changeRole = jaclinForAdmini.next();
        return DataProcessing.update(changeName,changePassword,changeRole);
    }

    public boolean delUser(){
        System.out.println("Please input deleted name:");
        delName = jaclinForAdmini.next();
        return DataProcessing.delete(delName);
    }

    public boolean addUser(){
        System.out.println("Please input added name:");
        addName = jaclinForAdmini.next();
        System.out.println("Please input added password:");
        addPassword = jaclinForAdmini.next();
        System.out.println("Please input added role:");
        addRole = jaclinForAdmini.next();
        return DataProcessing.insert(addName,addPassword,addRole);

    }

    public void listUser(){
        DataProcessing.getAllUser();
    }

}
