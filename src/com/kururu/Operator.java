package com.kururu;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by kururu on 2015/11/18.
 */
class Operator extends User{

    static int menuNum;

    Scanner jaclinForOpe = new Scanner(System.in);

    public Operator(String nameInput, String passwordInput, String roleInput){
        super(nameInput,passwordInput,roleInput);
    }

    public void showMenu(){
        System.out.println("Welcome my Operator!");
        System.out.println("The Operator's functions are in the list:\n"+
                "1 uploadFile;\n"+"2 downloadFile;\n"+"3 showFileList;\n"+"0 exit.");
        System.out.println("Please choose your functions!");
        menuNum = jaclinForOpe.nextInt();
        OperatorPanel:
        switch(menuNum){
            //Exit func
            case 0:{
                System.exit(0);
            }
            //UplaodFile func
            case 1:{
                if (uploadFile()) {
                    System.out.println("uploadFile successfully!");
                }else{
                    System.out.println("uploadFile failed!");
                }
                break OperatorPanel;
            }
            //DownlaodFile func
            case 2:{
                if (downlaodFile()) {
                    System.out.println("downlaodFile successfully!");
                }else{
                    System.out.println("downlaodFile failed!");
                }
                break OperatorPanel;
            }
            //Show func
            case 3:{
                showFileList();
                break OperatorPanel;
            }

        }

    }

    public boolean uploadFile() /*throws FileNotFoundException*/{
        System.out.println("uploadFile");
        return true;
    }

    public boolean downlaodFile() /*throws FileNotFoundException*/{
        System.out.println("downloadFile");
        return true;
    }

    public void showFileList(){
        System.out.println("showFileList");
    }

}
