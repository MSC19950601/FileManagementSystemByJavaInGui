package com.kururu.netServer;

import com.kururu.basement.User;

import java.io.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;
import java.util.Vector;

/**
 *
 */
public class Client extends Socket {

    private static final String SERVER_IP = "127.0.0.1";
    private static final int FINAL_PORT = 8899;
    private static final int SERVER_PORT_FOR_UPLOAD = 2016;
    private static final int SERVER_PORT_FOR_DOWNLOAD = 2014;
    private static final int SERVER_PORT_FOR_LOGIN = 7809;
    private static final int SERVER_PORT_FOR_GETUSERTABLE = 1578;
    private static final int SERVER_PORT_FOR_GETDOCTABLE = 2001;
    private static final String clientPath = "F:\\myJavaCodeInIntelliIdea\\FileManagementSystem\\clientFile\\";
    private static Socket
            client,
            transClientForUpload,
            transClientForDownload,
            transClientForLogin,
            transClientForGetUserTable,
            transClientForGetDocTable,
            transClientForAddUser,
            transClientForDeleteUser,
            transClientForChangeUserInfo,
            transClientForQuit;

    private static Socket keySocket;
    private static FileInputStream fis;
    private static DataOutputStream dos;

    private static void transClickForUpload(String click) throws IOException {
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

    private static void transClickForDownload(String click, String fileName) throws IOException {
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

    private static void transClickAndCommand(String click, String command) throws IOException {
        try{
            keySocket = new Socket(SERVER_IP, FINAL_PORT);
            keySocket .setSoTimeout(600000);
            PrintWriter printWriter = new
                    PrintWriter(keySocket.getOutputStream(), true);
            printWriter.println(click);
            printWriter.flush();
            printWriter.println(command);
            printWriter.flush();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void upload(String fileName, String click) {
        try {
            try {
                transClickForUpload(click);
                client = new Socket(SERVER_IP, SERVER_PORT_FOR_UPLOAD);
                client.setSoTimeout(600000);
                //�����˴����ļ�
                File file = new File(clientPath + fileName);
                fis = new FileInputStream(file);
                dos = new DataOutputStream(client.getOutputStream());
                //�ļ����ͳ���
                dos.writeUTF(file.getName());
                dos.flush();
                dos.writeLong(file.length());
                dos.flush();
                //�����ļ�
                byte[] sendBytes = new byte[1024];
                int length = 0;
                while ((length = fis.read(sendBytes, 0, sendBytes.length)) > 0) {
                    dos.write(sendBytes, 0, length);
                    dos.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fis != null)
                    System.out.println("fis");
                fis.close();
                if (dos != null)
                    System.out.println("dos");
                dos.close();
                if(client != null)
                    System.out.println("cli");
                client.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void download(String fileName, String click) {
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
                        ex.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
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
                Socket s = new Socket(SERVER_IP,SERVER_PORT_FOR_LOGIN);
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
                Socket s = new Socket(SERVER_IP,SERVER_PORT_FOR_GETUSERTABLE);
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
                Socket s = new Socket(SERVER_IP,SERVER_PORT_FOR_GETDOCTABLE);
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
                } else{
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

    public static void quit() throws IOException {
        try {
            transClientForQuit = new Socket(SERVER_IP, FINAL_PORT);
            transClientForQuit.setSoTimeout(60000);
            PrintWriter printWriter = new
                    PrintWriter(transClientForQuit.getOutputStream(), true);
            printWriter.println("quit");
            printWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transClientForQuit.close();
        }
    }
}

