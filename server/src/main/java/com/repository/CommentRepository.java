package com.repository;

import com.config.DateBaseConfig;
import com.models.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepository extends DateBaseConfig {
    private static CommentRepository CommentRepository = new CommentRepository();

    public  static CommentRepository getCommentRepository(){
        return CommentRepository;
    }


    private List<Comment> getAllComment() throws SQLException, ClassNotFoundException {
        String request="select * from hotel.comment";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Comment>list=new ArrayList<>();
        while (resultSet.next()){
            Comment comment=new Comment();
            comment.setDescription(resultSet.getString("description"));
            comment.setId(resultSet.getInt("id"));
            list.add(comment);
        }
        return list;
    }

    public void addComment(Comment comment) throws SQLException, ClassNotFoundException {
        String request="insert into  hotel.comment( description, id_account, star) values (?,?,?)";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.setString(1,comment.getDescription());
        preparedStatement.setInt(2,comment.getAccount().getId());
        preparedStatement.setInt(3,comment.getStars());
        preparedStatement.executeUpdate();
    }
}
