package com.kururu;
import java.lang.*;
import java.util.Scanner;

public class Main {

    static String loginName;
    static String loginPassword;

    //static String RegesterName;
    //static String RegesterPassword;
    //static String RegesterRole;

    static User loginUser;

    public static void main(String[] args) {

        //SystemFrame test = new SystemFrame();
        LoginFrame test2 = new LoginFrame();
        //LoginJFrameTest test3 = new LoginJFrameTest();
        //test3.showMe();
        Scanner jaclin = new Scanner(System.in);

        System.out.println("Welcome back to system!");
        mainPanel:
        while(true){
            System.out.println("1 LOGIN");
            //System.out.println("2 REGISTER");
            System.out.println("2 QUIT");

            int mainChoice = jaclin.nextInt();
            switch (mainChoice){
                case 1:{
                    System.out.println("Please input your name:");
                    loginName = jaclin.next();
                    System.out.println("Please input your password:");
                    loginPassword = jaclin.next();
                    loginUser = DataProcessing.search(loginName, loginPassword);
                    int w = 0;
                    while(true) {
                        try {
                            if(w > 5){
                                System.out.println("fucking bitch!");
                                break mainPanel;
                            }
                            System.out.println("Welcome!" + "\n" + loginUser.getName() + "\n" + "Your role is " + loginUser.getRole());
                            break;
                        } catch (NullPointerException e) {
                            w++;
                            System.out.println("name or password wrong!\nPlease input again!");
                            System.out.println("Please input your name:");
                            loginName = jaclin.next();
                            System.out.println("Please input your password:");
                            loginPassword = jaclin.next();
                            loginUser = DataProcessing.search(loginName, loginPassword);
                        }
                    }
                    loginUser.showMenu();
                    break mainPanel;
                }

                /*case 2:{
                    System.out.println("Please input your name:");
                    RegesterName = jaclin.next();
                    System.out.println("Please input your password:");
                    RegesterPassword = jaclin.next();
                    RegesterRole = "Browser";

                    if(DataProcessing.insert(RegesterName,RegesterPassword,RegesterRole)){
                        System.out.println("register successfully!");
                    }else{
                        System.out.println("register failed!");
                    }
                    break mainPanel;
                }*/

                case 2:{
                    System.exit(0);
                }
            }
        }

        //User testUser1 = new Browser("msc1","258","Browser");
        //testUser1.showMenu();
        /*System.out.println("Please input your name:");
        loginName = jaclin.nextLine();
        System.out.println("Please input your password:");
        loginPassword = jaclin.nextLine();

        loginUser = DataProcessing.search(loginName,loginPassword);
        loginUser.getter();
        loginUser.showMenu();*/


        /*User testUser1 = new Browser("msc1","258","Browser");
        User testUser2 = new Operator("msc2","369","Operator");
        User testUser3 = new Administrator("msc3","147","Administrator");

        testUser1.showMenu();
        testUser2.showMenu();
        testUser3.showMenu();*/
        /*DataProcessing test = new DataProcessing();
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
        }*/
    }
}
