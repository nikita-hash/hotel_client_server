package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.entit.RoomEntity;
import com.models.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewRoomUser {

    Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/view_room_user.fxml"));
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
    private Button bt_left;

    @FXML
    private Button bt_right;

    @FXML
    private Button close;

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<?> day;

    @FXML
    private TextField price;

    @FXML
    private ImageView image_view;

    @FXML
    private Label price_total;

    @FXML
    private Button registration;

    @FXML
    private TextField status;

    @FXML
    private TextField type;

    private Room room;
    private int check=0;

    private void initialized() throws IOException, ClassNotFoundException {
        room=(Room) Client.getInstance().getIn().readObject();
        ImageConfig imageConfig=new ImageConfig();
        image_view.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(0).getPhoto()));
        status.setText(room.getStatus());
        type.setText(room.getType());
        price.setText(room.getPrice());
        if(room.getListPhoto().size()==1){
            bt_left.setVisible(false);
            bt_right.setVisible(false);
        }
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        close.setOnAction(actionEvent -> {
            close.getScene().getWindow().hide();
        });
        bt_right.setOnAction(actionEvent -> {
            ImageConfig imageConfig=new ImageConfig();
            bt_left.setVisible(true);
            check++;
            if(room.getListPhoto().size()==check+1){
                try {
                    bt_right.setVisible(false);
                    image_view.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(check).getPhoto()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    image_view.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(check).getPhoto()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
        bt_left.setOnAction(actionEvent -> {
            ImageConfig imageConfig=new ImageConfig();
            bt_right.setVisible(true);
            check--;
            if(check==0){
                try {
                    bt_left.setVisible(false);
                    image_view.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(check).getPhoto()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    image_view.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(check).getPhoto()));
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }


}
