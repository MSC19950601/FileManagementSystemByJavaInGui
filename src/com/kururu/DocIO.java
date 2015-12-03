package com.kururu;

import java.io.*;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kururu on 2015/12/2.
 */
public class DocIO {

    //private String fileName;

    public static void inputDataToFile(String stream,File file){

        FileWriter FirstoutputPipe = null;

        try{
            FirstoutputPipe = new FileWriter(file);
        }catch(IOException e){
            e.printStackTrace();
        }

        BufferedWriter BufferOfFirstoutputPipe = new BufferedWriter(FirstoutputPipe);

        try {
            BufferOfFirstoutputPipe.write(stream);
            BufferOfFirstoutputPipe.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(BufferOfFirstoutputPipe != null){
                    BufferOfFirstoutputPipe.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void inputDataToFileForHashtable(File file){

        FileWriter FirstoutputPipe = null;

        try{
            FirstoutputPipe = new FileWriter(file);
        }catch(IOException e){
            e.printStackTrace();
        }

        BufferedWriter BufferOfFirstoutputPipe = new BufferedWriter(FirstoutputPipe);

        try {
            /*if(!hashtabletream.isEmpty()){
                Enumeration<String> keysOfUser = hashtabletream.keys();
                while(keysOfUser.hasMoreElements()){
                    if()
                }
            }*/
            if(!DataProcessing.users.isEmpty()){
                Enumeration<String> keysOfUser = DataProcessing.users.keys();
                while(keysOfUser.hasMoreElements()){
                    User oneUserOutput = DataProcessing.users.get(keysOfUser.nextElement());
                    //System.out.println(oneUserOutput.getName());
                    BufferOfFirstoutputPipe.write("<name>" + oneUserOutput.getName() + "</name>" + "\r\n");
                    //System.out.println(oneUserOutput.getPassword());
                    BufferOfFirstoutputPipe.write("<password>" + oneUserOutput.getPassword() + "</password>" + "\r\n");
                    //System.out.println(oneUserOutput.getRole());
                    BufferOfFirstoutputPipe.write("<role>" + oneUserOutput.getRole() + "</role>" + "\r\n");
                }
            }
            BufferOfFirstoutputPipe.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if(BufferOfFirstoutputPipe != null){
                    BufferOfFirstoutputPipe.close();
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /*public static Hashtable<String, User> outputDataFromFileForHashtable(File file){
        Hashtable<String, User> outputUsers = new Hashtable<String, User>();

        FileReader FirstinputPipe = null;
        try{
            FirstinputPipe = new FileReader(file);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }

        BufferedReader BufferOfFirstoutputPipe = new BufferedReader(file);
        String eachline = null;
        String [] nameArray = new String[3];
        String [] passwordArray = new String[3];
        String [] roleArray = new String[3];

        try{
            while((eachline = BufferOfFirstoutputPipe.readLine()) != null){
                Pattern pattern = Pattern.compile("name:", Pattern.MULTILINE | Pattern.DOTALL);
                Matcher matcher;

            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if(BufferOfFirstoutputPipe != null){
                try{
                    BufferOfFirstoutputPipe.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        return outputUsers;
    }*/
}
