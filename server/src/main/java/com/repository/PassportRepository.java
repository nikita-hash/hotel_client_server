package com.repository;

import com.config.DateBaseConfig;
import com.models.Passport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PassportRepository extends DateBaseConfig {
    private static PassportRepository PassportRepository = new PassportRepository();

    public  static PassportRepository getPassportRepository(){
        return PassportRepository;
    }

    public Passport findByIdAccount(int id_account) throws SQLException, ClassNotFoundException {
        String reqeust="select * from hotel.pasport where hotel.pasport.id_account = "+id_account;
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(reqeust);
        ResultSet res=preparedStatement.executeQuery();
        Passport passport=new Passport();
        while (res.next()){
            passport.setId_passport(res.getString("id"));
            passport.setSeria_number(res.getString("seria_number"));
        }
        return passport;
    }

    public void changePassport(Passport passport) throws SQLException, ClassNotFoundException {
        String request="";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }
}
