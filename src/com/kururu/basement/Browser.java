package com.kururu.basement;

import java.util.Scanner;

/**
 * Created by kururu on 2015/11/18.
 */
public class Browser extends User{
    //add on 20151202
    public String docNum;
    public Doc docTest;

    public static int menuNum;

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
                try{
                    showFileList();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
    public boolean downloadFile() /*throws FileNotFoundException*/ {
        System.out.println("downloadFile");
        return true;
    }

    public void showFileList() throws Exception{
        System.out.println("Func:showFileList");
        System.out.println("Please input your docNum");
        docNum = jaclinForBro.next();
        docTest = DataProcessing.searchDoc(docNum);
    }

}
