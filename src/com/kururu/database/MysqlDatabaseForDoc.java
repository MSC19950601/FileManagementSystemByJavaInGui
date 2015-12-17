package com.kururu.database;

import com.kururu.basement.Administrator;
import com.kururu.basement.Browser;
import com.kururu.basement.Operator;
import com.kururu.basement.*;

import java.sql.*;

/**
 * Created by kururu on 2015/12/16.
 */
public class MysqlDatabaseForDoc {
    final public static String connectionAddress= "jdbc:mysql://127.0.0.1:3306/baseforsystemcharger";
    final public static String connectionName = "root";
    final public static String connectionPassword = "mo123456";

    public static Doc resDoc;
    public static String resID,resCreator,resDescription,resFilename;
    public static Timestamp resTime;

    public static boolean insertUserDoc(String ID, String creator, Timestamp timestamp, String description, String filename) throws Exception{
        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForInsert = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForInsert.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForInsert = conForInsert.createStatement();
                String insertSqlStr = "INSERT INTO doc (DOC_ID, DOC_CREATOR, DOC_TIMESTAMP, DOC_DESCRIPTION, DOC_FILENAME) VALUES "
                        + "('" + ID + "', '" + creator + "', '" + timestamp + "', '" + description + "', '" + filename + "')";
                int count = staForInsert.executeUpdate(insertSqlStr);
                System.out.println("insert" + count + "line,"+"ID is "+ ID);
                staForInsert.close();
                conForInsert.close();
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return true;
        }
    }

    public static Doc searchDoc(String ID) throws Exception{

        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForSearch = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForSearch.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForSearch = conForSearch.createStatement();
                String SearchSqlStr = "SELECT * FROM doc WHERE DOC_ID ='" + ID + "'";
                ResultSet res = staForSearch.executeQuery(SearchSqlStr);

                while(res.next()) {
                    resID = res.getString("DOC_ID");
                    resCreator = res.getString("DOC_CREATOR");
                    resTime = res.getTimestamp("DOC_TIMESTAMP");
                    resDescription = res.getString("DOC_DESCRIPTION");
                    resFilename = res.getString("DOC_FILENAME");
                    resDoc = new Doc(resID,resCreator,resTime,resDescription,resFilename);
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
            if(resDoc == null)
                return null;
            else
                return resDoc;
        }
    }

    public static boolean deleteDoc(String delID) throws Exception{
        try{
            System.out.println("connecting to database now, loading...");
            Class.forName("com.mysql.jdbc.Driver");
            Connection conForDel = DriverManager.getConnection(
                    connectionAddress, connectionName, connectionPassword);
            if(!conForDel.isClosed()){
                System.out.println("Connecting successfully!");
                Statement staForsel = conForDel.createStatement();
                String delSqlStr = "DELETE FROM doc WHERE DOC_ID = " + "'" + delID + "'";
                int count = staForsel.executeUpdate(delSqlStr);
                System.out.println("delete" + count + "line,"+"ID is"+delID);
                staForsel.close();
                conForDel.close();
            }else{
                System.out.println("Sorry, Connection is closed!");
            }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return true;
        }
    }
}
