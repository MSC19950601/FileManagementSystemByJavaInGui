package com.kururu.netServer;

import com.kururu.basement.User;

import java.io.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.EnumMap;
import java.util.Vector;

public class Client extends Socket {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int SERVER_PORT_FOR_UPLOAD = 2013;
    private static final int SERVER_PORT_FOR_DOWNLOAD = 2014;
    private static final int FINAL_PORT = 8899;
    private static final String clientPath = "F:\\myJavaCodeInIntelliIdea\\FileManagementSystem\\clientFile\\";

    private static Socket client;
    private Socket transClientForUpload;
    private Socket transClientForDownload;
    private static Socket transClientForLogin;
    private static Socket
            transClientForGetUserTable,
            transClientForGetDocTable,
            transClientForAddUser,
            transClientForDeleteUser,
            transClientForChangeUserInfo;
    private FileInputStream fis;
    private DataOutputStream dos;


    public Client(String click, String fileName) {
        if (click.equals("upload")) {
            upload(fileName, click);
        } else if (click.equals("downloadForOpe")) {
            download(fileName, click);
        }
    }

    private void transClickForUpload(String click) throws IOException {
        try {
            transClientForUpload = new Socket(SERVER_IP, FINAL_PORT);
            transClientForUpload.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForUpload.getOutputStream(), true);
            printWriter.println(click);
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transClientForUpload.close();
        }
    }

    private void transClickForDownload(String click, String fileName) throws IOException {
        try {
            transClientForDownload = new Socket(SERVER_IP, FINAL_PORT);
            transClientForDownload.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForDownload.getOutputStream(), true);
            printWriter.println(click);
            printWriter.flush();
            printWriter.println(fileName);
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transClientForDownload.close();
        }
    }

    private static void transClickForLogin(String command) throws IOException {
        try {
            transClientForLogin = new Socket(SERVER_IP, FINAL_PORT);
            transClientForLogin.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForLogin.getOutputStream(), true);
            printWriter.println("login");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transClientForLogin.close();
        }
    }

    private static void transClickForGetUserTable(String command) throws IOException{
        try{
            transClientForGetUserTable = new Socket(SERVER_IP, FINAL_PORT);
            transClientForGetUserTable.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForGetUserTable.getOutputStream(), true);
            printWriter.println("getUserTable");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void transClickForGetDocTable(String command) throws IOException{
        try{
            transClientForGetDocTable = new Socket(SERVER_IP, FINAL_PORT);
            transClientForGetDocTable.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForGetDocTable.getOutputStream(), true);
            printWriter.println("getDocTable");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private  static void transClickForAddUser(String command){
        try{
            transClientForAddUser = new Socket(SERVER_IP, FINAL_PORT);
            transClientForAddUser.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForAddUser.getOutputStream(), true);
            printWriter.println("addUser");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void transClickForDeleteUser(String command) {
        try{
            transClientForDeleteUser = new Socket(SERVER_IP, FINAL_PORT);
            transClientForDeleteUser .setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForDeleteUser.getOutputStream(), true);
            printWriter.println("deleteUser");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void transClickForChangeUserInfo(String command) {
        try{
            transClientForChangeUserInfo = new Socket(SERVER_IP, FINAL_PORT);
            transClientForChangeUserInfo .setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForChangeUserInfo.getOutputStream(), true);
            printWriter.println("changeUserInfo");
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void upload(String fileName, String click) {
        try {
            try {
                transClickForUpload(click);
                client = new Socket(SERVER_IP, SERVER_PORT_FOR_UPLOAD);
                client.setSoTimeout(60000);
                //向服务端传送文件
                File file = new File(clientPath + fileName);

                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());

                //文件名和长度
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();

                //传输文件
                byte[] sendBytes = new byte[1024];
                int length = 0;
                while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                    dos.write(sendBytes, 0, length);
                    dos.flush();
                }
                System.out.println("fuckfuck");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null)
                    System.out.println("fis");
                fis.close();
                if (dos != null)
                    System.out.println("dos");
                dos.close();
                System.out.println("cli");
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void download(String fileName, String click) {

        try {
            try {
                transClickForDownload(click, fileName);
                byte[] aByte = new byte[1];
                int bytesRead;

                Socket clientSocket = null;
                InputStream is = null;
                try {
                    clientSocket = new Socket(SERVER_IP, SERVER_PORT_FOR_DOWNLOAD);
                    is = clientSocket.getInputStream();
                } catch (IOException ex) {
                    // Do exception handling
                }

                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                if (is != null) {

                    FileOutputStream fos = null;
                    BufferedOutputStream bos = null;
                    try {

                        fos = new FileOutputStream(clientPath + fileName);
                        bos = new BufferedOutputStream(fos);
                        bytesRead = is.read(aByte, 0, aByte.length);

                        do {
                            baos.write(aByte);
                            bytesRead = is.read(aByte);
                        } while (bytesRead != -1);

                        bos.write(baos.toByteArray());
                        bos.flush();
                        bos.close();
                        clientSocket.close();
                    } catch (IOException ex) {
                        // Do exception handling
                    }
                }
            } catch (Exception e) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User loginSecure(String loginName, String loginPassword) {

        try {
            try {
                String command = "SELECT * FROM user WHERE USER_NAME ='" + loginName + "'AND USER_PASSWORD = '" + loginPassword + "'";
                transClickForLogin(command);
                Socket s = new Socket("127.0.0.1",7809);
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                User u=(User)ois.readObject();
                return u;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector[] getUserTable(){

        try {
            try {
                String command = "SELECT * FROM user";
                transClickForGetUserTable(command);
                Socket s = new Socket("127.0.0.1",1578);
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                Vector colHead = (Vector)ois.readObject();
                Vector rows= (Vector)ois.readObject();

                Vector[] res = new Vector[2];
                res[0] = colHead;
                res[1] = rows;
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Vector[] getDocTable(){

        try {
            try {
                String command = "SELECT * FROM doc";
                transClickForGetDocTable(command);
                Socket s = new Socket("127.0.0.1",2001);
                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());

                Vector colHead = (Vector)ois.readObject();
                Vector rows= (Vector)ois.readObject();


                Vector[] res = new Vector[2];
                res[0] = colHead;
                res[1] = rows;
                return res;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void newUserAdd(String addName, String addPassword, String addRole){
        try {
            try {
                String command = "INSERT INTO user (USER_NAME, USER_PASSWORD, USER_ROLE) VALUES " + "('" + addName + "', '" + addPassword + "', '" + addRole + "')";
                transClickForAddUser(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(String delName){
        try {
            try {
                String command = "DELETE FROM user WHERE USER_NAME = " + "'" + delName + "'";
                transClickForDeleteUser(command);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static void changeUserInfo(String name, String changeInfo, String changedCellsCol){
        try {
            try {
                if(changedCellsCol.equals("USER_NAME")) {
                    String command = "UPDATE user SET USER_NAME = '" + changeInfo + "'WHERE USER_NAME = '" + name + "'";
                    transClickForChangeUserInfo(command);
                }
                else{
                    String command = "UPDATE user SET USER_PASSWORD = '" + changeInfo + "'WHERE USER_NAME = '" + name + "'";
                    transClickForChangeUserInfo(command);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

