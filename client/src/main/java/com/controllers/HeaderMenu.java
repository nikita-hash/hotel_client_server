package com.controllers;

import com.client.Client;
import com.message.HeaderMessage;
import com.models.Account;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.CustomPasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HeaderMenu {
    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/header_menu.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = loader.getRoot();
        Scene scene = new Scene(parent);
        stage = new Stage();
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }


    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bt_enter;

    @FXML
    private Label label_er;

    @FXML
    private TextField login;

    @FXML
    private CustomPasswordField password;

    @FXML
    private Button tool_reg;

    @FXML
    private Button close;

    @FXML
    private Button tool_reviews;

    @FXML
    private AnchorPane dark_bg;

    @FXML
    void initialize() {
        close.setOnAction(actionEvent -> {
            close.getScene().getWindow().hide();
        });
        tool_reg.setOnAction(actionEvent -> {
            dark_bg.setVisible(true);
            RegistrationAccountMenu registrationAccountMenu=new RegistrationAccountMenu();
            registrationAccountMenu.Show();
            dark_bg.setVisible(false);
        });
        bt_enter.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(HeaderMessage.AUTORIZATION);
                Account account=new Account();
                account.setLogin(login.getText());
                account.setPassword(password.getText());
                Client.getInstance().getOut().writeObject(account);
                HeaderMessage headerMessage=(HeaderMessage) Client.getInstance().getIn().readObject();
                if(headerMessage==HeaderMessage.NOT_FOUND_ACC){
                    label_er.setText("Аккаунт не был найден");
                }
                else {
                    headerMessage=(HeaderMessage) Client.getInstance().getIn().readObject();
                    if(headerMessage==HeaderMessage.BAN_STATUS){

                    }
                    else {
                        if(headerMessage==HeaderMessage.ADMIN_ACC){
                            bt_enter.getScene().getWindow().hide();
                            AdminMenu adminMenu=new AdminMenu();
                            adminMenu.Show();
                        }
                        if(headerMessage==HeaderMessage.USER_ACC){
                            bt_enter.getScene().getWindow().hide();
                            UserMenu userMenu=new UserMenu();
                            userMenu.Show();
                        }
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

    }

}
