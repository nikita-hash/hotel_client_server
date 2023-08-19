package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.HeaderMessage;
import com.models.Account;
import com.models.Men;
import com.models.Passport;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import io.github.gleidson28.GNAvatarView;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class RegistrationAdminMenu {

    Stage stage=new Stage();
    

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/registration_admin_account.fxml"));
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
    private TextField login;

    @FXML
    private Label login_icon;

    @FXML
    private TextField name;

    @FXML
    private Label name_icon;

    @FXML
    private TextField password;

    @FXML
    private Label password_icon;

    @FXML
    private TextField patronymic;

    @FXML
    private Label patronymic_icon;

    @FXML
    private TextField phone;

    @FXML
    private Label phone_icon;

    @FXML
    private Button registration;

    @FXML
    private Label seria_icon;

    @FXML
    private TextField seria_pas;

    private File file_avatar=new File(System.getProperty("user.dir")+"/src/main/resources/static/style/image/avatar.png");

    private void generatedLoginAndPassword(){
        login.setText("User - "+Integer.toString(1000+(int) ( Math.random() * 10000)));
        password.setText(RandomStringUtils.randomAlphabetic(7));
    }

    @FXML
    void initialize() {
        generatedLoginAndPassword();


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
        login.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                login_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) login_icon.getChildrenUnmodifiable().get(0);
                if(t1.matches("")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    login_icon.getStyleClass().remove("label_sec");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    login_icon.getStyleClass().remove("label_sec");
                    login_icon.getStyleClass().add("label_sec");
                }
            }
        });
        password.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                password_icon.setVisible(true);
                FontAwesomeIconView icon=(FontAwesomeIconView) password_icon.getChildrenUnmodifiable().get(0);
                if(t1.matches("")){
                    icon.setIcon(FontAwesomeIcon.INFO);
                    password_icon.getStyleClass().remove("label_sec");
                }
                else {
                    icon.setIcon(FontAwesomeIcon.CHECK);
                    password_icon.getStyleClass().remove("label_sec");
                    password_icon.getStyleClass().add("label_sec");
                }
            }
        });
        registration.setOnAction(actionEvent -> {
            System.out.println(avatar.getImage().getUrl());

            if(block_reg.getChildren()
                    .stream()
                    .filter(node -> node instanceof HBox)
                    .filter(node -> ((HBox) node).getChildren().get(1) instanceof Label && ((HBox) node).getChildren().get(1).getStyleClass().contains("label_sec"))
                    .collect(Collectors.toList())
                    .size()!=block_reg.getChildren().stream().filter(node -> node instanceof HBox).collect(Collectors.toList()).size()){
            }
            else {
                ImageConfig imageConfig=new ImageConfig();
                Men men=new Men();
                Passport passport=new Passport();
                passport.setSeria_number(seria_pas.getText());
                passport.setId_passport(Id_passport.getText());
                men.setName(name.getText());
                men.setLast_name(last_name.getText());
                men.setPatronymic(patronymic.getText());
                men.setPhone(phone.getText());
                Account account=new Account();
                account.setPassword(password.getText());
                account.setLogin(login.getText());
                try {
                    account.setAvatar(imageConfig.SerializableImage(file_avatar));
                    account.setMen(men);
                    account.setPassport(passport);
                    Client.getInstance().getOut().writeObject(HeaderMessage.REGISTRATION);
                    Client.getInstance().getOut().writeObject(account);
                    HeaderMessage headerMessage=(HeaderMessage) Client.getInstance().getIn().readObject();
                    if(headerMessage==HeaderMessage.ACCOUNT_IS_EXISTS){
                        Alert alert=new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Ошибка регистрации");
                        alert.setHeaderText("Ошибка регистрации");
                        alert.setContentText("Данный логин уже используется придумайте новый");
                        alert.show();
                    }
                    else {
                        Alert alert=new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Успешно");
                        alert.setHeaderText("Регистрация прошла успешно");
                        alert.setContentText("Регистрация прошла успешно аккаунт администратора активен ");
                        alert.show();
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }

        });

    }
}
