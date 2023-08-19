package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.AdminMessage;
import com.models.Employee;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.gleidson28.GNAvatarView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrationEmployeeMenu {

    Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/registration_employee_menu.fxml"));
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
    private TextField Id_passport;

    @FXML
    private GNAvatarView avatar;

    @FXML
    private VBox block_reg;

    @FXML
    private Button close;

    @FXML
    private Button download_photo;

    @FXML
    private Label id_passport_icon;

    @FXML
    private TextField last_name;

    @FXML
    private Label last_name_icon;

    @FXML
    private TextField name;

    @FXML
    private Label name_icon;

    @FXML
    private TextField patronymic;

    @FXML
    private Label patronymic_icon;

    @FXML
    private TextField phone;

    @FXML
    private Label phone_icon;

    @FXML
    private ComboBox<String> position;

    @FXML
    private Label position_icon;

    @FXML
    private Button registration;

    @FXML
    private Label seria_icon;

    @FXML
    private TextField seria_pas;

    private File file_avatar=new File(System.getProperty("user.dir")+"/src/main/resources/static/style/image/avatar.png");

    @FXML
    void initialize() {
        position.getItems().add("Управляющий");
        position.getItems().add("Директор");
        position.getItems().add("Горничная");
        position.getItems().add("Консьерж");
        position.getItems().add("Охраник");
        close.setOnAction(actionEvent -> {
            close.getScene().getWindow().hide();
        });
        download_photo.setOnAction(actionEvent -> {
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("Выберите фото");
            FileChooser.ExtensionFilter filter=new FileChooser.ExtensionFilter("Select ...",".png",".jpg");
            fileChooser.getExtensionFilters()
                    .addAll(new FileChooser.ExtensionFilter("Select ...","*.png","*.jpg"));
            File photo=fileChooser.showOpenDialog(stage);
            if(photo!=null){
                Image image=new Image("file:///"+photo.getAbsolutePath());
                avatar.setImage(image);
                file_avatar=photo;
            }
            else {

                avatar.setImage(new Image("file:///"+System.getProperty("user.dir")+"/src/main/resources/static/style/image/avatar.png"));
            }
        });
        name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                name_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) name_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    name_icon.getStyleClass().remove("label_sec");
                    name_icon.getTooltip().setText("Неверный формат имени");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    name_icon.getStyleClass().remove("label_sec");
                    name_icon.getStyleClass().add("label_sec");
                    name_icon.getTooltip().setText("Успешно");
                }
            }
        });
        position.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                FontAwesomeIconView icon=(FontAwesomeIconView) position_icon.getChildrenUnmodifiable().get(0);
                icon.setIcon(FontAwesomeIcon.CHECK);
                position_icon.getStyleClass().remove("label_sec");
                position_icon.getStyleClass().add("label_sec");
                position_icon.getTooltip().setText("Успешно");
            }
        });
        last_name.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                last_name_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) last_name_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    last_name_icon.getStyleClass().remove("label_sec");
                    last_name_icon.getTooltip().setText("Неверный формат фамилии");

                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    last_name_icon.getStyleClass().remove("label_sec");
                    last_name_icon.getStyleClass().add("label_sec");
                    last_name_icon.getTooltip().setText("Успешно");

                }
            }
        });
        patronymic.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                patronymic_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) patronymic_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("[ЙЦУКЕНГШЩЗХФЫВАПРОЛДЖЭЯЧСМИТЬБЮйцукенгшщзхъфывапролджэюбьтимсчяёЁ]{2,40}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    patronymic_icon.getStyleClass().remove("label_sec");
                    patronymic_icon.getTooltip().setText("Неверный формат отчества");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    patronymic_icon.getStyleClass().remove("label_sec");
                    patronymic_icon.getStyleClass().add("label_sec");
                    patronymic_icon.getTooltip().setText("Успешно");
                }
            }
        });
        phone.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                phone_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) phone_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("(\\+375|80)\\d{9}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    phone_icon.getStyleClass().remove("label_sec");
                    phone_icon.getTooltip().setText("Неверный формат телефона +375 80 (29 44 25) xxxxxxx");

                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    phone_icon.getStyleClass().remove("label_sec");
                    phone_icon.getStyleClass().add("label_sec");
                    phone_icon.getTooltip().setText("Успешно");

                }
            }
        });
        Id_passport.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                id_passport_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) id_passport_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("\\d{7}[A-Z]{1}\\d{3}[A-Z]{2}\\d{1}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    id_passport_icon.getStyleClass().remove("label_sec");
                    id_passport_icon.getTooltip().setText("Неверный формат ID паспорта");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    id_passport_icon.getStyleClass().remove("label_sec");
                    id_passport_icon.getStyleClass().add("label_sec");
                    id_passport_icon.getTooltip().setText("Успешно");
                }
            }
        });
        seria_pas.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                seria_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) seria_icon.getChildrenUnmodifiable().get(0);
                if(!t1.matches("[A-Z]{2}\\d{7}")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    seria_icon.getStyleClass().remove("label_sec");
                    seria_icon.getTooltip().setText("Неверный формат серии номер паспорта MP 123456");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    seria_icon.getStyleClass().remove("label_sec");
                    seria_icon.getStyleClass().add("label_sec");
                    seria_icon.getTooltip().setText("Успешно");

                }
            }
        });
        registration.setOnAction(actionEvent -> {
            if(block_reg.getChildren()
                    .stream()
                    .filter(node -> node instanceof HBox)
                    .filter(node -> ((HBox) node).getChildren().get(1) instanceof Label && ((HBox) node).getChildren().get(1).getStyleClass().contains("label_sec"))
                    .collect(Collectors.toList())
                    .size()!=block_reg.getChildren().stream().filter(node -> node instanceof HBox).collect(Collectors.toList()).size()){
            }
            else {
                try {
                    ImageConfig imageConfig=new ImageConfig();
                    Employee employee=new Employee();
                    employee.setName(name.getText());
                    employee.setLast_name(last_name.getText());
                    employee.setPatronymic(patronymic.getText());
                    employee.setPhone(phone.getText());
                    employee.setPhoto(imageConfig.SerializableImage(file_avatar));
                    employee.setId_passport(Id_passport.getText());
                    employee.setSeria_number(seria_pas.getText());
                    employee.setPosition(position.getValue());

                    Client.getInstance().getOut().writeObject(AdminMessage.REGISTRATION_EMPLOYEE);
                    Client.getInstance().getOut().writeObject(employee);

                    Alert alert=new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Успешно");
                    alert.setHeaderText("Регистрация прошла успешно");
                    alert.setContentText("Регистрация сотрудника прошла успешно");
                    alert.setOnHiding(dialogEvent -> {
                        registration.getScene().getWindow().hide();
                    });
                    alert.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


}
