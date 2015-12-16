package com.kururu.database;

import com.kururu.basement.*;

import java.lang.*;

import java.sql.*;


/**
 * Created by kururu on 2015/12/16.
 */
public class MysqlDatabaseForUser {

    final public static String connectionAddress= "jdbc:mysql://127.0.0.1:3306/baseforsystemcharger";
    final public static String connectionName = "root";
    final public static String connectionPassword = "mo123456";

    public static User resUser;
    public static String resName,resPassword,resRole;

    public static User searchUser(String name, String password) throws Exception{

        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForSearch = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForSearch.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForSearch = conForSearch.createStatement();
                String SearchSqlStr = "SELECT * FROM user WHERE USER_NAME ='" + name + "'AND USER_PASSWORD = '" + password + "'";
                ResultSet res = staForSearch.executeQuery(SearchSqlStr);

                while(res.next()) {
                    resName = res.getString("USER_NAME");
                    //System.out.println(resName);
                    resPassword = res.getString("USER_PASSWORD");
                    //System.out.println(resPassword);
                    resRole = res.getString("USER_ROLE");
                    //System.out.println(resRole);
                    if(resRole.equals("operator")){
                        System.out.println("operator");
                        resUser = new Operator(resName,resPassword,resRole);
                    }else if(resRole.equals("browser")){
                        System.out.println("browser");
                        resUser = new Browser(resName,resPassword,resRole);
                    }else if(resRole.equals("administrator")){
                        System.out.println("administrator");
                        resUser = new Administrator(resName,resPassword,resRole);
                    }
                }
                res.close();
                staForSearch.close();
                conForSearch.close();
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(resUser == null)
                return null;
            else
                return resUser;
        }
    }

    public static boolean updateUser(String name, String password, String role) throws Exception{
        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForUpdate = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForUpdate.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForUpdate = conForUpdate.createStatement();
                String insertSqlStr = "UPDATE user SET USER_PASSWORD = '" + password + "', USER_ROLE = '" + role + "'WHERE USER_NAME = '" + name + "'";
                int count = staForUpdate.executeUpdate(insertSqlStr);
                System.out.println("update " + count + " line,"+"name is "+name);
                staForUpdate.close();
                conForUpdate.close();
                return true;
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return false;
        }
    }

    public static boolean insertUser(String name, String password, String role) throws Exception{
        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForInsert = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForInsert.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForInsert = conForInsert.createStatement();
                String insertSqlStr = "INSERT INTO user (USER_NAME, USER_PASSWORD, USER_ROLE) VALUES " + "('" + name + "', '" + password+ "', '" + role + "')";
                int count = staForInsert.executeUpdate(insertSqlStr);
                System.out.println("insert" + count + "line,"+"name is "+name);
                staForInsert.close();
                conForInsert.close();
                return true;
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return false;
        }
    }


    public static boolean deleteUser(String delName) throws Exception{
        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForDel = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForDel.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForsel = conForDel.createStatement();
                String delSqlStr = "DELETE FROM user WHERE USER_NAME = " + "'" + delName + "'";
                int count = staForsel.executeUpdate(delSqlStr);
                System.out.println("delete" + count + "line,"+"name is"+delName);
                staForsel.close();
                conForDel.close();
                return true;
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return false;
        }
    }
}
