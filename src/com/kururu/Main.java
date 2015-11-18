package com.kururu;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        DataProcessing test = new DataProcessing();
        /*User testUser1 = new Browser("msc1","258","Browser");
        User testUser2 = new Operator("msc2","369","Operator");
        User testUser3 = new Administrator("msc3","147","Administrator");

        testUser1.showMenu();
        testUser2.showMenu();
        testUser3.showMenu();*/

        User testUserSed = test.search("jack","123");
        testUserSed.showMenu();

        test.getAllUser();

        if(test.update("jack", "123","operator")){
            System.out.println("update successfully!");
        }else{
            System.out.println("update failed!");
        }

        if(test.insert("sxy","159","Browser")){
            System.out.println("insert successfully!");
        }else{
            System.out.println("insert failed!");
        }

        if(test.delete("jack")){
            System.out.println("delete successfully!");
        }else{
            System.out.println("delete failed!");
        }
    }
}
