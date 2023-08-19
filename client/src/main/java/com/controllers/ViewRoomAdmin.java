package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.entit.RoomEntity;
import com.message.AdminMessage;
import com.models.Room;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewRoomAdmin {

    Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/view_room_admin.fxml"));
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
    private ImageView image_view;

    @FXML
    private TextField price;

    @FXML
    private TextField status;

    @FXML
    private TextField type;

    @FXML
    private Button bt_repair;

    @FXML
    private Button bt_remove;

    @FXML
    private Button bt_stop;

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
        if(room.getStatus().equals(RoomEntity.statusBooked)){
            bt_repair.setDisable(true);
            bt_remove.setDisable(true);
            bt_stop.setDisable(true);
        }
        if (room.getStatus().equals(RoomEntity.statusRepair)){
            bt_repair.setText("Снять с ремонта");
        }
        if(room.getStatus().equals(RoomEntity.statusClose)){
            bt_repair.setText("Востановить заселение");
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
        bt_remove.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(AdminMessage.REMOVE_ROOM);
                Client.getInstance().getOut().writeObject(room);
                Alert alert=new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Удаление");
                alert.setContentText("Удаление прошло успешно !");
                alert.show();
                alert.setOnHiding(dialogEvent -> {
                    bt_remove.getScene().getWindow().hide();
                });
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bt_repair.setOnAction(actionEvent -> {
            if(bt_repair.getText().equals("Закрыть на ремонт")){
                try {
                    status.setText(RoomEntity.statusRepair);
                    bt_repair.setText("Открыть");
                    Client.getInstance().getOut().writeObject(AdminMessage.REPAIR_ROOM);
                    Client.getInstance().getOut().writeObject(room);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    status.setText(RoomEntity.statusOpen);
                    bt_repair.setText("Закрыть на ремонт");
                    Client.getInstance().getOut().writeObject(AdminMessage.OPEN_ROOM);
                    Client.getInstance().getOut().writeObject(room);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        bt_stop.setOnAction(actionEvent -> {
            if(bt_stop.getText().equals("Остановить заселение")){
                try {
                    status.setText(RoomEntity.statusClose);
                    bt_stop.setText("Возобновить заселение");
                    Client.getInstance().getOut().writeObject(AdminMessage.STOP_ROOM);
                    Client.getInstance().getOut().writeObject(room);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                try {
                    status.setText(RoomEntity.statusOpen);
                    bt_stop.setText("Остановить заселение");
                    Client.getInstance().getOut().writeObject(AdminMessage.OPEN_ROOM);
                    Client.getInstance().getOut().writeObject(room);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
