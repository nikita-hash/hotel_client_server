package com.repository;

import com.config.DateBaseConfig;
import com.models.Network;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NeworkRepository extends DateBaseConfig {
    private static NeworkRepository NeworkRepository = new NeworkRepository();

    public  static NeworkRepository getNeworkRepository(){
        return NeworkRepository;
    }

    public Network findByIdAccount(int id_account) throws SQLException, ClassNotFoundException {
        String request="select * from hotel.network where hotel.network.id_account = "+id_account;
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet resultSet=preparedStatement.executeQuery();
        Network network=new Network();
        while (resultSet.next()){
            network.setInst(resultSet.getString("inst"));
            network.setTg(resultSet.getString("tg"));
            network.setVK(resultSet.getString("vk"));
            if(network.getInst().equals(""))network.setInst("Не указано");
            if(network.getTg().equals(""))network.setTg("Не указано");
            if(network.getVK().equals(""))network.setVK("Не указано");
        }
        return network;
    }
}
