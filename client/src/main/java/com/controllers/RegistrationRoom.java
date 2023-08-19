package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.AdminMessage;
import com.models.Employee;
import com.models.PhotoRoom;
import com.models.Room;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.gleidson28.GNAvatarView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrationRoom {
    Stage stage=new Stage();


    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/registration_room.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Scene scene=new Scene(parent);
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
    private HBox block_img;

    @FXML
    private Button close;

    @FXML
    private TextArea description;

    @FXML
    private Label last_name_icon;

    @FXML
    private Label last_name_icon1;

    @FXML
    private VBox block_reg;

    @FXML
    private Button bt_add_photo;

    @FXML
    private TextField price;

    @FXML
    private Button registration;

    @FXML
    private ComboBox<String> type;

    @FXML
    private List<PhotoRoom> list=new ArrayList<>();

    @FXML
    void initialize() {

        type.getItems().add("Презеденский");
        type.getItems().add("Люкс");
        type.getItems().add("Обычный");

        close.setOnAction(actionEvent -> {
            close.getScene().getWindow().hide();
        });

        type.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                FontAwesomeIconView icon=(FontAwesomeIconView) last_name_icon1.getChildrenUnmodifiable().get(0);
                icon.setIcon(FontAwesomeIcon.CHECK);
                last_name_icon1.getStyleClass().remove("label_sec");
                last_name_icon1.getStyleClass().add("label_sec");
                last_name_icon1.getTooltip().setText("Успешно");
            }
        });

        bt_add_photo.setOnAction(actionEvent -> {
            if(list.size()<3){
                ImageConfig imageConfig=new ImageConfig();
                FileChooser fileChooser=new FileChooser();
                fileChooser.setTitle("Выберите фото");
                FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select ...",".png",".jpg");
                fileChooser.getExtensionFilters()
                        .addAll(new FileChooser.ExtensionFilter("Select ...","*.png","*.jpg"));
                File photo=fileChooser.showOpenDialog(stage);
                if(photo!=null){
                    try {
                        HBox anchorPane=new HBox();
                        anchorPane.setPadding(new Insets(10,10,10,10));
                        anchorPane.getStyleClass().add("img_baground");
                        GNAvatarView avatar=new GNAvatarView();
                        avatar.setPrefWidth(160);
                        avatar.setPrefHeight(152);
                        avatar.setMinWidth(160);
                        Image image=new Image("file:///"+photo.getAbsolutePath());
                        avatar.setImage(image);
                        avatar.getStyleClass().add("avatar");
                        Button remove=new Button();
                        FontAwesomeIconView fontAwesomeIconView=new FontAwesomeIconView(FontAwesomeIcon.REMOVE);
                        fontAwesomeIconView.setFill(Paint.valueOf("white"));
                        fontAwesomeIconView.setSize("35px");
                        fontAwesomeIconView.setCursor(Cursor.HAND);

                        anchorPane.getChildren().add(avatar);
                        anchorPane.getChildren().add(fontAwesomeIconView);
                        PhotoRoom photoRoom=new PhotoRoom();
                        photoRoom.setPhoto(imageConfig.SerializableImage(photo));
                        list.add(photoRoom);
                        HBox.setMargin(anchorPane,new Insets(0,10,0,0));
                        block_img.getChildren().add(0,anchorPane);

                        fontAwesomeIconView.setOnMouseClicked(mouseEvent -> {
                                block_img.getChildren().remove(fontAwesomeIconView.getParent());
                                list.remove(photoRoom);
                                bt_add_photo.setVisible(true);
                        });

                        if(list.size()==3)bt_add_photo.setVisible(false);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {

                }
            }
            else {
                bt_add_photo.setVisible(false);
            }
        });

        price.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                last_name_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) last_name_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("^[-+]?[0-9]*[.][0-9]+(?:[eE][-+]?[0-9]+)?$")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    last_name_icon.getStyleClass().remove("label_sec");
                    last_name_icon.getTooltip().setText("Неверный формат денежного поля");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    last_name_icon.getStyleClass().remove("label_sec");
                    last_name_icon.getStyleClass().add("label_sec");
                    last_name_icon.getTooltip().setText("Успешно");
                }
            }
        });

        registration.setOnAction(actionEvent -> {
            if(block_reg.getChildren()
                    .stream()
                    .filter(node -> node instanceof HBox)
                    .filter(node -> ((HBox) node).getChildren().get(1) instanceof Label && ((HBox) node).getChildren().get(1).getStyleClass().contains("label_sec"))
                    .collect(Collectors.toList())
                    .size()!=block_reg.getChildren().stream().filter(node -> node instanceof HBox).collect(Collectors.toList()).size() || list.size()==0){
                if(list.size()==0){
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Ошибка регистрации");
                    alert.setContentText("Добавьте хотя бы одно фото !");
                    alert.show();
                }
                else {
                    System.out.println(block_reg.getChildren()
                            .stream()
                            .filter(node -> node instanceof HBox)
                            .filter(node -> ((HBox) node).getChildren().get(1) instanceof Label && ((HBox) node).getChildren().get(1).getStyleClass().contains("label_sec"))
                            .collect(Collectors.toList())
                            .size());
                    System.out.println(block_reg.getChildren().stream().filter(node -> node instanceof HBox).collect(Collectors.toList()).size());
                }
            }
            else {
                try {
                    Room room=new Room();
                    room.setPrice(price.getText());
                    room.setDescription(description.getText());
                    room.setType(type.getValue());
                    room.setListPhoto(list);
                    Client.getInstance().getOut().writeObject(AdminMessage.ADD_ROOM);
                    Client.getInstance().getOut().writeObject(room);
                    list=new ArrayList<>();
                    description.setText("");
                    price.setText("");
                    block_img.getChildren().removeIf(node -> node instanceof HBox);
                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("Команта умпешно зарегестрирована");
                    alert.setHeaderText("Успешно");
                    alert.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
