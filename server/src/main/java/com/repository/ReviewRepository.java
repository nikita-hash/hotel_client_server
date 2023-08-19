package com.repository;

import com.config.DateBaseConfig;
import com.models.Account;
import com.models.Review;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository extends DateBaseConfig {
    private static ReviewRepository ReviewRepository = new ReviewRepository();
    public static ReviewRepository getReviewRepository() {
        return ReviewRepository;
    }
    
    public void addReview(Review review) throws SQLException, ClassNotFoundException {
        String request="insert into hotel.comment( description, id_account, star) values (?,?,?)";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        preparedStatement.setString(1,review.getReview());
        preparedStatement.setInt(2,review.getAccount().getId());
        preparedStatement.setInt(3,review.getStar());
        preparedStatement.executeUpdate();
    }

    public List<Review> findAll() throws SQLException, ClassNotFoundException {
        String request="select * from hotel.comment";
        PreparedStatement preparedStatement= getDbconnection().prepareStatement(request);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<Review>list=new ArrayList<>();
        while (resultSet.next()){
            Review review=new Review();
            review.setId(resultSet.getInt("id"));
            review.setAccount(AccountRepository.getAccountRepository().findById(resultSet.getInt("id_account")));
            review.setStar(resultSet.getInt("star"));
            review.setReview(resultSet.getString("description"));
            list.add(review);
        }
        return list;
    }
}
