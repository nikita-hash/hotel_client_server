package com.repository;

import com.config.DateBaseConfig;
import com.models.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepository extends DateBaseConfig {


    public static RoomRepository getRoomRepository() {
        return roomRepository;
    }

    private Room onlyRoomNoPhoto(ResultSet resultSet) throws SQLException {
        Room room=new Room();
        while (resultSet.next()){
            room.setId(resultSet.getInt("id"));
            room.setDescription(resultSet.getString("description"));
            room.setStatus(resultSet.getString("status"));
            room.setType(resultSet.getString("type"));
            room.setPrice(resultSet.getString("price"));
            room.setListPhoto(null);
        }
        return room;
    }
    private Room onlyRoomPhoto(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        Room room=new Room();
        while (resultSet.next()){
            room.setId(resultSet.getInt("id"));
            room.setDescription(resultSet.getString("description"));
            room.setStatus(resultSet.getString("status"));
            room.setType(resultSet.getString("type"));
            room.setPrice(resultSet.getString("price"));
            room.setListPhoto(PhotoRoomRepository.getRoomRepository().getAllPhotoRoomByID(room.getId()));
        }
        return room;
    }
    private List<Room> allRoom(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        List<Room> listRoom=new ArrayList<>();
        while (resultSet.next()){
            Room room=new Room();
            room.setId(resultSet.getInt("id"));
            room.setDescription(resultSet.getString("description"));
            room.setStatus(resultSet.getString("status"));
            room.setType(resultSet.getString("type"));
            room.setPrice(resultSet.getString("price"));
            room.setListPhoto(PhotoRoomRepository.getRoomRepository().getAllPhotoRoomByID(room.getId()));
            listRoom.add(room);
        }
        return listRoom;
    }
    private List<Room> allRoomNoPhoto(ResultSet resultSet) throws SQLException, ClassNotFoundException {
        List<Room> listRoom=new ArrayList<>();
        while (resultSet.next()){
            Room room=new Room();
            room.setId(resultSet.getInt("id"));
            room.setDescription(resultSet.getString("description"));
            room.setStatus(resultSet.getString("status"));
            room.setStatus(resultSet.getString("type"));
            room.setPrice(resultSet.getString("price"));
            room.setListPhoto(null);
            listRoom.add(room);
        }
        return listRoom;
    }

    private static RoomRepository roomRepository = new RoomRepository();

    public void registrationRoom(Room room) throws SQLException, ClassNotFoundException {
        String insert_user="INSERT INTO hotel.room( price, description, status, type) "+"VALUES(?,?,?,?)";
        PreparedStatement statement_account=getDbconnection().prepareStatement(insert_user,PreparedStatement.RETURN_GENERATED_KEYS);
        statement_account.setString(1,room.getPrice());
        statement_account.setString(2,room.getDescription());
        statement_account.setString(3,room.getStatus());
        statement_account.setString(4,room.getType());
        statement_account.executeUpdate();
        ResultSet res=statement_account.getGeneratedKeys();
        res.next();
        room.getListPhoto().stream().forEach(photoRoom -> {
            try {
                String requeset="insert into hotel.photo_room(id_room,photo) values(?,?)";
                PreparedStatement statement=getDbconnection().prepareStatement(requeset,PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setInt(1,res.getInt(1));
                statement.setString(2,photoRoom.getPhoto());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void updateStatusByID(Room room) throws SQLException, ClassNotFoundException {
        String request="update hotel.room set hotel.room.status = '"+room.getStatus()+"' where hotel.room.id ="+room.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }

    public Room findById(Room room) throws SQLException, ClassNotFoundException {
        String request="select * from hotel.room where hotel.room.id = "+room.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        return onlyRoomPhoto(preparedStatement.executeQuery());
    }

    public List<Room> getAllRoomAndPhoto() throws SQLException, ClassNotFoundException {
        String request="select * from hotel.room";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        return allRoom(preparedStatement.executeQuery());
    }

    public List<Room> findByStatus(String status) throws SQLException, ClassNotFoundException {
        String request="select * from hotel.room where hotel.room.status = '"+status+"'";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        return allRoomNoPhoto(preparedStatement.executeQuery());
    }

    public List<Room> getAllRoomNoPhoto() throws SQLException, ClassNotFoundException {
        String request="select * from hotel.room";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        return allRoomNoPhoto(preparedStatement.executeQuery());
    }

    public void removeRoom(Room room) throws SQLException, ClassNotFoundException {
        String request="DELETE  from hotel.room where hotel.room.id = "+room.getId();
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.executeUpdate();
    }
}
