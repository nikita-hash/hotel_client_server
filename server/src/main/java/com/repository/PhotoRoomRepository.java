package com.repository;

import com.config.DateBaseConfig;
import com.models.PhotoRoom;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhotoRoomRepository extends DateBaseConfig {
    public static PhotoRoomRepository getRoomRepository() {
        return roomPhotoRepository;
    }

    private static PhotoRoomRepository roomPhotoRepository = new PhotoRoomRepository();

    public List<PhotoRoom> getAllPhotoRoomByID(int id) throws SQLException, ClassNotFoundException {
        String request="select * from hotel.photo_room where hotel.photo_room.id_room = "+id;
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<PhotoRoom>listPhoto=new ArrayList<>();
        while (resultSet.next()){
            PhotoRoom photoRoom=new PhotoRoom();
            photoRoom.setId(resultSet.getInt("id"));
            photoRoom.setId_room(resultSet.getInt("id_room"));
            photoRoom.setPhoto(resultSet.getString("photo"));
            listPhoto.add(photoRoom);
        }
        return listPhoto;
    }
}
