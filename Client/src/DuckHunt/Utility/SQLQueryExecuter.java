/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DuckHunt.Utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Sahaj Bamba
 *
 * SQL Query Executer
 *
 * This class is used to connect to the Data-Base and interact with it. Be sure to call start connection at the beginning of your application and then always call get instance whenever required.
 *
 * ex of arguments are  ("user", "pass", "jdbc:mysql://localhost:3306/dbname")
 *
 * SELECT and UPDATE functions perform DDL and DML Queries as supplied.
 *
 * Also be sure to close the connection at the end using close function
 */

public class SQLQueryExecuter {
    
    private static String user ;
    private static String pass ;
    private static String url;
    private Connection conn ;
    private Statement stm ;
    private ResultSet rs = null;

    private static SQLQueryExecuter sqlQueryExecuter = null;

    public static void startConnection(String user2 , String pass2 , String url2){
        user=user2;
        pass=pass2;
        url=url2;
        getInstance();
    }

    public static SQLQueryExecuter getInstance(){
        if (sqlQueryExecuter==null){
            return sqlQueryExecuter=new SQLQueryExecuter(user,pass,url);
        }
        return sqlQueryExecuter;
    }

    private SQLQueryExecuter(String user , String pass , String url) {
        SQLQueryExecuter.user = user;
        SQLQueryExecuter.pass = pass;
        SQLQueryExecuter.url = url;
        try {
            this.conn = java.sql.DriverManager.getConnection(SQLQueryExecuter.url, SQLQueryExecuter.user, SQLQueryExecuter.pass);
            this.stm = conn.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(SQLQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    


    public ResultSet select(String sql){
        System.out.println(sql);
        try {
            return this.stm.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void update(String sql) {
        System.out.println(sql);
        try {
            this.stm.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SQLQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void close(){
        try {
            this.stm.close();
            this.conn.close();
            this.rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLQueryExecuter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
