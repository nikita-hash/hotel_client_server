package com.repository;

import com.config.DateBaseConfig;
import com.models.Men;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MenRepository extends DateBaseConfig {
    private static MenRepository MenRepository = new MenRepository();

    public  static MenRepository getMenRepository(){
        return MenRepository;
    }

    public Men findByAccountId(int account_id) throws SQLException, ClassNotFoundException {
        String request="select * from hotel.men where hotel.men.id_account = "+account_id;
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet res=preparedStatement.executeQuery();
        Men men=new Men();
        while (res.next()){
            men.setId(res.getInt("id"));
            men.setName(res.getString("name"));
            men.setLast_name(res.getString("last_name"));
            men.setPatronymic(res.getString("patronymic"));
            men.setPhone(res.getString("phone"));
        }
        return men;
    }

    public void changeMen(Men men) throws SQLException, ClassNotFoundException {
        String reques="UPDATE hotel.men set hotel.men.name = '"+men.getName()+"', hotel.men.last_name ='"+men.getLast_name()+
                "', hotel.men.patronymic = '"+men.getPatronymic()+"', hotel.men.patronymic = '"+men.getPhone()+"' where hotel.men.id = "+men.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(reques);
        preparedStatement.executeUpdate();
    }
}
