package com.controller;

import com.message.UserMessage;
import com.models.Account;
import com.models.Review;
import com.models.Room;
import com.repository.AccountRepository;
import com.repository.ReviewRepository;
import com.repository.RoomRepository;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.SQLException;
import java.util.List;

public class UserController {

    private void initializedRoom(ObjectOutputStream out) throws IOException, SQLException, ClassNotFoundException {
        out.writeObject(RoomRepository.getRoomRepository().getAllRoomAndPhoto());
    }

    private void accountInitialized(Account account ,ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(account);
    }

    private void reviewInitialized(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(ReviewRepository.getReviewRepository().findAll());
    }

    public void start(ObjectOutputStream out, ObjectInputStream in, Account account) throws IOException, ClassNotFoundException, SQLException {
        reviewInitialized(out);
        accountInitialized(account,out);
        initializedRoom(out);
        while (true){
            UserMessage userMessage=(UserMessage)in.readObject();
            switch (userMessage){
                case VIEW_ROOM:viewRoom(in, out);break;
                case EXIT:HeaderController headerController=new HeaderController();headerController.start(out, in);break;
                case ADD_REVIEW:addReview(in, account);break;
                case UPDATE_ALL_ROOM:getAllRoom(out);break;
            }
        }
    }

    private void addReview(ObjectInputStream in,Account account) throws IOException, ClassNotFoundException, SQLException {
        Review review=(Review) in.readObject();
        review.setAccount(account);
        ReviewRepository.getReviewRepository().addReview(review);
    }

    private void viewRoom(ObjectInputStream in,ObjectOutputStream out) throws IOException, ClassNotFoundException, SQLException {
        Room room=(Room) in.readObject();
        out.writeObject(RoomRepository.getRoomRepository().findById(room));
    }

    private void getAllRoom(ObjectOutputStream out) throws SQLException, ClassNotFoundException, IOException {
        out.writeObject(RoomRepository.getRoomRepository().getAllRoomAndPhoto());
    }

}
