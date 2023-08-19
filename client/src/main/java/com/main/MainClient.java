package com.main;

import com.client.Client;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainClient extends Application {
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {
        Client.getInstance().connection();
    }

    public static void main(String[] args) {
        launch();
    }
}