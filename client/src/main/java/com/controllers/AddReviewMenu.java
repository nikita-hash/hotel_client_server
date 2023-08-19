package com.controllers;

import com.client.Client;
import com.message.UserMessage;
import com.models.Review;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Rating;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddReviewMenu {

    Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/add_review.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Scene scene=new Scene(parent);
        stage=new Stage();
        stage.setScene(scene);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button close;

    @FXML
    private TextArea description;

    @FXML
    private Rating rating;

    @FXML
    private Button send;

    @FXML
    void initialize() {
        close.setOnAction(actionEvent -> {
            close.getScene().getWindow().hide();
        });
        send.setOnAction(actionEvent -> {
            if(description.getText().equals("")){
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Ошибка создания отзыва");
                alert.setContentText("Введите содрежание отзыва");
                alert.show();
            }
            else {
                try {
                    Client.getInstance().getOut().writeObject(UserMessage.ADD_REVIEW);
                    Review review=new Review();
                    review.setStar((int) rating.getRating());
                    review.setReview(description.getText());
                    Client.getInstance().getOut().writeObject(review);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setContentText("Отзыв успешно добавлен");
                    alert.show();
                    description.setText("");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
