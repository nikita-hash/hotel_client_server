package com.config;

import com.repository.AccountRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DateBaseConfig {
    private   String dbHost = "localhost";
    private  String dbport = "3306";
    private  String dbUsers = "root";
    private  String dbPass = "7899398";
    private  String dbName = "hotel";

    public Connection getDbconnection()throws ClassNotFoundException, SQLException {
        Connection dbconnection;
        String ConectionString ="jdbc:mysql://"+dbHost+":"+
                dbport+"/"+dbName+"?serverTimezone=Europe/Moscow";
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.println(ConectionString);
        dbconnection= DriverManager.getConnection(ConectionString, dbUsers,dbPass);
        return dbconnection;
    }

}
