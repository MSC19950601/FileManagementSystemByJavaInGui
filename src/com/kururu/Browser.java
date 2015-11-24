package com.kururu;

import java.util.Scanner;

/**
 * Created by kururu on 2015/11/18.
 */
class Browser extends User{

    static int menuNum;

    Scanner jaclinForBro = new Scanner(System.in);

    public Browser(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        System.out.println("Welcome my Browser!");
        System.out.println("The Browser's functions are in the list:\n"+
                "1 downloadFile;\n"+"2 showFileList;\n"+"0 exit.");
        System.out.println("Please choose your functions!");
        menuNum = jaclinForBro.nextInt();
        BrowserPanel:
        switch(menuNum){
            //Exit func
            case 0:{
                System.exit(0);
            }
            //DownloadFile func
            case 1:{
                if (downloadFile()) {
                    System.out.println("downloadFile successfully!");
                }else{
                    System.out.println("downloadFile failed!");
                }
                break BrowserPanel;
            }
            //Show func
            case 2:{
                showFileList();
            }
        }

    }
    public boolean downloadFile(){
        System.out.println("downloadFile");
        return true;
    }

    public void showFileList(){
        System.out.println("showFileList");
    }

}
