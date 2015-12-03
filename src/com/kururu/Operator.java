package com.kururu;

import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.util.Enumeration;
import java.util.Scanner;
import java.sql.*;


/**
 * Created by kururu on 2015/11/18.
 */
class Operator extends User{

    public static int menuNum;

    static String originalSourceFilePath = "filesNeedToBeUploaded/";
    static String originalDestinationFilePath = "Data/";

    //uploadFiles Func
    static String uploadFilePath,uploadFileDestinationPath;
    static String uploadFileName,uploadTargetFileName;
    static String uploadID,uploadDescription;
    static Timestamp uploadTimestamp;
    //downloadDFiles Func
    //static String downloadFilePath,downloadFileDestinationPath;
    //static String downloadFileName,downloadTargetFileName;
    static Doc downLoadDoc;
    static String downLoadID;

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
                try{
                    if (uploadFile()) {
                        System.out.println("uploadFile successfully!");
                    }else{
                        System.out.println("uploadFile failed!");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    break OperatorPanel;
                }
                break OperatorPanel;
            }
            //DownlaodFile func
            case 2:{
                try{
                    if (downlaodFile()) {
                        System.out.println("downlaodFile successfully!");
                    }else{
                        System.out.println("downlaodFile failed!");
                    }
                }catch (Exception e) {
                    e.printStackTrace();
                    break OperatorPanel;
                }
                break OperatorPanel;
            }
            //Show func
            case 3:{
                try {
                    showFileList();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break OperatorPanel;
            }

        }

    }

    public boolean uploadFile() throws IOException,SQLException,IllegalStateException{

        System.out.println("func:uploadFile");
        System.out.println("Please input upload Filename");
        uploadFileName = jaclinForOpe.next();
        uploadFilePath = originalSourceFilePath + uploadFileName;

        System.out.println("Please input destination Filename");
        uploadTargetFileName = jaclinForOpe.next();
        uploadFileDestinationPath = originalDestinationFilePath + uploadTargetFileName;

        System.out.println("Please input ID");
        uploadID = jaclinForOpe.next();
        System.out.println("Please input description");
        uploadDescription = jaclinForOpe.next();

        uploadTimestamp = new Timestamp(System.currentTimeMillis());

        File sourceFile = new File(uploadFilePath);

        if(!sourceFile.exists()){
            System.out.println("destinationFile file doesn't exist!");
            if(sourceFile.createNewFile()){
                System.out.println("destinationFile file has been built!");
            }
        }
        /*if(!sourceFile.exists()){
            System.out.println("source file doesn't exist!");
            return false;
        }else if(!sourceFile.isFile()){
            System.out.println("source file is not a file!");
            return false;
        }*/

        File destiantionFile = new File(uploadFileDestinationPath);

        if(!destiantionFile.exists()){
            System.out.println("destinationFile file doesn't exist!");
            if(destiantionFile.createNewFile()){
                System.out.println("destinationFile file has been built!");
            }
        }
        // 复制文件
        //int byteread = 0; // 读取的字节数
        //InputStream inStream= null;
        // OutputStream outStream = null;
        FileInputStream inStream= null;
        FileOutputStream outStream = null;
        FileChannel inChannel = null;
        FileChannel outChannel = null;
        try {

            inStream = new FileInputStream(sourceFile);
            outStream = new FileOutputStream(destiantionFile);

            inChannel  = inStream.getChannel();
            outChannel  = outStream.getChannel();
            inChannel .transferTo(0, inChannel.size(), outChannel);

            /*inStream = new FileInputStream(sourceFile);
            outStream = new FileOutputStream(destiantionFile);
            byte[] buffer = new byte[1024];

            while ((byteread = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, byteread);
            }*/

            if(DataProcessing.insertDoc(uploadID,this.getName(),uploadTimestamp,uploadDescription,uploadFilePath)){
                System.out.println("insert successfully!");
            }
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (SQLException e){
            return false;
        } catch(IllegalStateException e){
            return false;
        } finally{
            /*try {
                if (outStream != null)
                    outStream.close();
                if (inStream != null)
                    inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }*/
            if (inStream != null) inStream.close();
            if (inChannel != null) inChannel.close();
            if (outStream != null) outStream.close();
            if (outChannel != null) outChannel.close();
        }
        //File test = new File("Data/test.txt");
        //DocIO.inputDataToFileForHashtable(test);
        //return true;
    }



    public boolean downlaodFile() throws Exception {
        System.out.println("func:downloadFile");
        System.out.println("Please input ID");
        downLoadID = jaclinForOpe.next();
        try {
            downLoadDoc = DataProcessing.searchDoc(downLoadID);
            if (downLoadDoc == null) {
                System.out.println("file doesn't exist!");
            } else {
                String downloadSourcePath = downLoadDoc.getFilename();
                String downloadDestinationPath = "filesNeedToBeUploaded/download.txt";

                File sourceFile = new File(downloadDestinationPath);
                if (!sourceFile.exists()) {
                    System.out.println("source file doesn't exist!");
                    return false;
                } else if (!sourceFile.isFile()) {
                    System.out.println("source file is not a file!");
                    return false;
                }

                File destinationFile = new File(downloadSourcePath);
                if (!destinationFile.exists()) {
                    System.out.println("destinationFile file doesn't exist!");
                    if (destinationFile.createNewFile()) {
                        System.out.println("destinationFile file has been built!");
                    }
                }
                // 复制文件
                int byteread = 0; // 读取的字节数
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = new FileInputStream(sourceFile);
                    out = new FileOutputStream(destinationFile);
                    byte[] buffer = new byte[1024];

                    while ((byteread = in.read(buffer)) != -1) {
                        out.write(buffer, 0, byteread);
                    }
                    return true;
                }catch(Exception e){
                    return false;
                }finally{
                    try {
                        if (out != null)
                            out.close();
                        if (in != null)
                            in.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void showFileList() throws IllegalStateException,SQLException{
        System.out.println("showFileList");
        Doc doc;
        System.out.println("ID\tCreater\tTimestamp\t\t\tDescription\t\t\tFilename");

        Enumeration<Doc> a = DataProcessing.getAllDocs();
        while(a.hasMoreElements()) {    //测试Enumeration枚举对象中是否还含有元素，如果返回true，则表示还含有至少一个的元素

            doc = a.nextElement();  //如果Bnumeration枚举对象还含有元素，该方法得到对象中的下一个元素

            System.out.println(doc.getID() + "\t" + doc.getCreator() + "\t" + doc.getTimestamp() + "\t\t" +
                    doc.getDescription() + "\t\t\t" + doc.getFilename());
        }

    }

}
