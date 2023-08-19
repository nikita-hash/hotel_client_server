package com.table;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.AdminMessage;
import com.models.Employee;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class TableEmployee {
    HBox id;
    HBox FIO;
    HBox phone;
    HBox position;
    HBox avatar;

    public TableEmployee(Employee employee) throws IOException, ClassNotFoundException {
        ImageConfig imageConfig=new ImageConfig();
        setId(employee.getId());
        setName(employee.getName()+" "+employee.getLast_name()+" "+employee.getPatronymic());
        setPosition(employee.getPosition());
        setPhone(employee.getPhone());
        setAvatar(imageConfig.DeserializableImage(employee.getPhoto()));
        setAction(employee);
    }

    public HBox getAvatar() {
        return avatar;
    }

    public HBox getId() {
        return id;
    }

    public HBox getFIO() {
        return FIO;
    }

    public HBox getPhone() {
        return phone;
    }

    public HBox getPosition() {
        return position;
    }

    public HBox getRemove() {
        return remove;
    }

    HBox remove;

    public  void setId(int id){
        this.id=new HBox();
        this.id.setMaxHeight(40);
        this.id.setMaxHeight(40);
        this.id.setPrefHeight(40);
        this.id.setPrefWidth(40);
        this.id.setAlignment(Pos.CENTER);
        Label label=new Label(Integer.toString(id));
        label.setWrapText(true);
        this.id.getChildren().add(label);
    }
    public void setAvatar(Image image) {
        avatar=new HBox();
        avatar.setMaxHeight(40);
        avatar.setMaxHeight(40);
        avatar.setPrefHeight(40);
        avatar.setPrefWidth(40);
        avatar.setAlignment(Pos.CENTER);
        ImageView imageView=new ImageView(image);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        avatar.getChildren().add(imageView);
    }

    public void setName(String fio) {
        this.FIO=new HBox();
        this.FIO.setMaxHeight(40);
        this.FIO.setMaxHeight(40);
        this.FIO.setPrefHeight(40);
        this.FIO.setPrefWidth(40);
        this.FIO.setAlignment(Pos.CENTER);
        Label label=new Label(fio);
        label.setWrapText(true);
        this.FIO.getChildren().add(label);
    }

    public void setPhone(String phone) {
        this.phone=new HBox();
        this.phone.setMaxHeight(40);
        this.phone.setMaxHeight(40);
        this.phone.setPrefHeight(40);
        this.phone.setPrefWidth(40);
        this.phone.setAlignment(Pos.CENTER);
        Label label=new Label(phone);
        label.setWrapText(true);
        this.phone.getChildren().add(label);
    }

    public void setPosition(String position) {
        this.position=new HBox();
        this.position.setMaxHeight(40);
        this.position.setMaxHeight(40);
        this.position.setPrefHeight(40);
        this.position.setPrefWidth(40);
        this.position.setAlignment(Pos.CENTER);
        Label label=new Label(position);
        label.setWrapText(true);
        this.position.getChildren().add(label);
    }

    public void setAction(Employee employee){
        this.remove=new HBox();
        this.remove.setMaxHeight(40);
        this.remove.setMaxHeight(40);
        this.remove.setPrefHeight(40);
        this.remove.setPrefWidth(40);
        this.remove.setAlignment(Pos.CENTER);
        Button button=new Button("Уволить сотрудника");
        button.setOnAction(actionEvent -> {
            try {
                Client.getInstance().getOut().writeObject(AdminMessage.REMOVE_EMPLOYEE);
                Client.getInstance().getOut().writeObject(employee);
                TableView<TableEmployee> tableAcPeTableView=(TableView<TableEmployee>)button.getParent().getParent().getParent().getParent().getParent().getParent().getParent();
                tableAcPeTableView.getItems().remove(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        this.remove.getChildren().add(button);
    }
}
