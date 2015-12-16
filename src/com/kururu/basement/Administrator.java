package com.kururu.basement;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * Created by kururu on 2015/11/18.
 */
public class Administrator extends User{

    static int menuNum;

    private static String changePassword;
    private static String changeRole;
    private static String delName;
    private static String addName;
    private static String addPassword;
    private static String addRole;

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
                try {
                    if (changeUserInfo()) {
                        System.out.println("Change successfully!");
                    } else {
                        System.out.println("Change failed!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break AdministratorPanel;
                }
                break AdministratorPanel;
            }
            //Delete func
            case 2:{
                try {
                    if (delUser()) {
                        System.out.println("Delete successfully!");
                    } else {
                        System.out.println("Delete failed!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break AdministratorPanel;
                }
                break AdministratorPanel;
            }
            //Add func
            case 3:{
                try {
                    if (addUser()) {
                        System.out.println("Add successfully!");
                    } else {
                        System.out.println("Add failed!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break AdministratorPanel;
                }
                break AdministratorPanel;
            }
            //showlist func
            case 4:{
                try{
                    listUser();
                }catch (Exception e){
                    e.printStackTrace();
                    break AdministratorPanel;
                }
                break AdministratorPanel;
            }
        }
    }

    public boolean changeUserInfo() throws SQLException {
        System.out.println("Please input changed name:");
        String changeName = jaclinForAdmini.next();
        System.out.println("Please input changed password:");
        changePassword = jaclinForAdmini.next();
        System.out.println("Please input changed role:");
        changeRole = jaclinForAdmini.next();
        return DataProcessing.updateUser(changeName, changePassword, changeRole);
    }

    public boolean delUser() throws SQLException {
        System.out.println("Please input deleted name:");
        delName = jaclinForAdmini.next();
        return DataProcessing.deleteUser(delName);
    }

    public boolean addUser() throws SQLException {
        System.out.println("Please input added name:");
        addName = jaclinForAdmini.next();
        System.out.println("Please input added password:");
        addPassword = jaclinForAdmini.next();
        System.out.println("Please input added role:");
        addRole = jaclinForAdmini.next();
        return DataProcessing.insertUser(addName, addPassword, addRole);
    }

    public void listUser() throws SQLException {
        DataProcessing.getAllUser();
    }

}
