package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.AdminMessage;
import com.message.HeaderMessage;
import com.models.Account;
import com.models.Employee;
import com.models.Review;
import com.models.Room;
import com.table.TableEmployee;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.gleidson28.AvatarType;
import io.github.gleidson28.GNAvatarView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.util.List;

import static java.lang.Long.MAX_VALUE;

public class AdminMenu {

    private Stage stage;

    public void Show(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/admin_menu.fxml"));
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
    private AnchorPane block_black_list;

    @FXML
    private AnchorPane block_client;

    @FXML
    private AnchorPane block_comment;

    @FXML
    private AnchorPane block_employee;

    @FXML
    private AnchorPane block_room;

    @FXML
    private AnchorPane block_settins;

    @FXML
    private AnchorPane block_statistic;

    @FXML
    private AnchorPane dark_bg;

    @FXML
    private AnchorPane header_background;

    @FXML
    private Label label_sec;

    @FXML
    private VBox list_room;

    @FXML
    private TextField persPatronymic;

    @FXML
    private TextField pers_id_passport;

    @FXML
    private TextField pers_inst;

    @FXML
    private TextField pers_lastName;

    @FXML
    private TextField pers_login;

    @FXML
    private TextField pers_name;

    @FXML
    private TextField pers_password;

    @FXML
    private TextField pers_phone;

    @FXML
    private GNAvatarView pers_photo;

    @FXML
    private GNAvatarView pers_photo_avatar;

    @FXML
    private TextField pers_seria;

    @FXML
    private TextField pers_tg;

    @FXML
    private TextField pers_vk;

    @FXML
    private Button tool_add_admin;

    @FXML
    private Button tool_add_employee;

    @FXML
    private Button tool_add_room;

    @FXML
    private HBox tool_settins;

    @FXML
    private VBox tool_block;


    @FXML
    private HBox tool_client;

    @FXML
    private Button tool_comment;

    @FXML
    private HBox tool_employee;

    @FXML
    private Button tool_exit;

    @FXML
    private HBox tool_history;

    @FXML
    private HBox tool_room;

    @FXML
    private Label stat_room;

    @FXML
    private Label progress_room_label;

    @FXML
    private ProgressBar progress_room;

    @FXML
    private Label stat_acc;

    @FXML
    private Label header_label_fio;

    @FXML
    private Label stat_ban;

    @FXML
    private VBox list_account;

    @FXML
    private HBox tool_statistick;

    @FXML
    private VBox list_view;

    @FXML
    private TableView<TableEmployee>tb_employee;

    @FXML
    private TableColumn<TableEmployee, String> tb_id_empl;

    @FXML
    private TableColumn<TableEmployee, String> tb_avatar_empl;

    @FXML
    private TableColumn<TableEmployee, String> tb_FIO_empl;

    @FXML
    private TableColumn<TableEmployee, String> tb_phone_empl;

    @FXML
    private TableColumn<TableEmployee, String> tb_position_empl;

    @FXML
    private TableColumn<TableEmployee, String> tb_action_empl;

    private void customComment(List<Review> list){
        list.stream().forEach(review -> {
            try {
                ImageConfig imageConfig=new ImageConfig();
                VBox vBox=new VBox();
                vBox.getStyleClass().add("container");

                VBox.setMargin(vBox,new Insets(20,0,0,0));

                HBox hBox=new HBox();
                Label label=new Label(review.getReview());
                label.setMaxWidth(MAX_VALUE);
                label.setWrapText(true);
                label.setFont(Font.font(18));
                label.setTextFill(Paint.valueOf("white"));
                HBox.setMargin(label,new Insets(0,0,20,0));
                hBox.getChildren().add(label);

                HBox hBox1=new HBox();
                hBox1.getStyleClass().add("comment_footer");
                hBox1.setPrefHeight(73);
                hBox1.setPadding(new Insets(10,0,0,0));

                GNAvatarView avatarView=new GNAvatarView();
                avatarView.setPrefWidth(100);
                avatarView.setPrefHeight(61);
                avatarView.setType(AvatarType.CIRCLE);
                avatarView.setImage(imageConfig.DeserializableImage(review.getAccount().getAvatar()));
                hBox1.getChildren().add(avatarView);

                Label fio=new Label(review.getAccount().getMen().getName()+" "+review.getAccount().getMen().getLast_name()+" "+review.getAccount().getMen().getPatronymic());
                fio.setFont(Font.font("bold",18));
                fio.setTextFill(Paint.valueOf("white"));
                fio.setPrefWidth(500);
                fio.getStyleClass().add("label_fio");
                fio.setPrefHeight(61);
                hBox1.getChildren().add(fio);

                label.setOnMouseClicked(mouseEvent -> {
                    if(label.getHeight()!=250){
                        label.setMaxHeight(250);
                    }
                    else {
                        label.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    }
                });

                HBox hBox2=new HBox();
                hBox2.setPrefWidth(200);
                hBox2.setPrefHeight(100);
                hBox2.setAlignment(Pos.CENTER);
                hBox2.getStyleClass().add("comment_rating");
                for(int i=0;i<review.getStar();i++){
                    FontAwesomeIconView fontAwesomeIconView=new FontAwesomeIconView(FontAwesomeIcon.STAR);
                    fontAwesomeIconView.setFill(Paint.valueOf("GOLD"));
                    fontAwesomeIconView.setSize("25");
                    hBox2.getChildren().add(fontAwesomeIconView);
                }

                hBox1.getChildren().add(hBox2);

                vBox.getChildren().add(hBox);
                vBox.getChildren().add(hBox1);

                list_view.getChildren().add(vBox);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void accountCustom(List<Account> list){
        if(list!=null){

        }
        list.stream().forEach(account -> {
            try {
            ImageConfig imageConfig=new ImageConfig();
            HBox hBox=new HBox();
            VBox.setMargin(hBox,new Insets(0,0,10,0));
            hBox.setPadding(new Insets(5,5,5,10));
            hBox.setPrefWidth(200);
            hBox.setPrefHeight(200);
            hBox.getStyleClass().add("img_baground");

            VBox vBox_img=new VBox();
            vBox_img.setAlignment(Pos.CENTER);
            vBox_img.setPrefHeight(200);
            vBox_img.setPrefWidth(180);
            HBox.setMargin(vBox_img,new Insets(0,20,0,0));

            GNAvatarView avatar=new GNAvatarView();
            avatar.setImage(imageConfig.DeserializableImage(account.getAvatar()));
            avatar.setStrokeWidth(0);
            vBox_img.getChildren().add(avatar);

            VBox vBox_bt=new VBox();
            vBox_bt.setAlignment(Pos.CENTER);
            vBox_bt.getStyleClass().add("container");
            vBox_bt.setPrefWidth(122);

            FontAwesomeIconView remove=new FontAwesomeIconView(FontAwesomeIcon.TRASH);
            remove.setFill(Paint.valueOf("white"));
            remove.setSize("40px");
                remove.setCursor(Cursor.HAND);
                FontAwesomeIconView block=new FontAwesomeIconView(FontAwesomeIcon.LOCK);
                if(account.getStatus().equals("Заблокирован"))block.setIcon(FontAwesomeIcon.UNLOCK);
                block.setFill(Paint.valueOf("white"));
                block.setSize("40px");
                block.setCursor(Cursor.HAND);
                FontAwesomeIconView history=new FontAwesomeIconView(FontAwesomeIcon.HISTORY);
                history.setFill(Paint.valueOf("white"));
                history.setSize("40px");
                history.setCursor(Cursor.HAND);
                VBox.setMargin(remove,new Insets(0,0,10,0));
                VBox.setMargin(block,new Insets(0,0,10,0));


                VBox vBox_text=new VBox();
                HBox.setMargin(vBox_text,new Insets(0,20,0,0));
                vBox_text.setPrefWidth(516);
                vBox_text.setPrefHeight(190);

                HBox hBox_inpFIO=new HBox();
                hBox_inpFIO.setPrefWidth(598);
                hBox_inpFIO.setPrefHeight(37);
                TextField textFildFio=new TextField(account.getMen().getName()+" "+account.getMen().getLast_name()+" "+account.getMen().getPatronymic());
                textFildFio.setAlignment(Pos.CENTER);
                textFildFio.setPrefWidth(510);
                textFildFio.setCursor(Cursor.DEFAULT);
                textFildFio.setEditable(false);
                hBox_inpFIO.getChildren().add(textFildFio);


                HBox Hbox4=new HBox();
                Hbox4.setPrefWidth(500);
                Hbox4.setPrefHeight(37);
                TextField textLogin=new TextField(account.getLogin());
                HBox.setMargin(textLogin,new Insets(0,20,0,0));
                textLogin.setAlignment(Pos.CENTER);
                textLogin.setPrefWidth(240);
                TextField textPassword=new TextField("Скрыто");
                textPassword.setAlignment(Pos.CENTER);
                textPassword.setPrefWidth(240);
                textPassword.setCursor(Cursor.DEFAULT);
                textPassword.setAlignment(Pos.CENTER);
                textPassword.setEditable(false);
                textLogin.setEditable(false);
                textLogin.setCursor(Cursor.DEFAULT);
                Hbox4.getChildren().add(textLogin);
                Hbox4.getChildren().add(textPassword);

                HBox Hbox3=new HBox();
                Hbox3.setPrefWidth(500);
                Hbox3.setPrefHeight(37);
                TextField textSeria=new TextField(account.getPassport().getSeria_number());
                HBox.setMargin(textSeria,new Insets(0,20,0,0));
                textSeria.setAlignment(Pos.CENTER);
                textSeria.setPrefWidth(240);
                textSeria.setCursor(Cursor.DEFAULT);
                textSeria.setEditable(false);
                TextField textDate_reg=new TextField(account.getDate_registration());
                textDate_reg.setAlignment(Pos.CENTER);
                textDate_reg.setPrefWidth(240);
                textDate_reg.setCursor(Cursor.DEFAULT);
                textDate_reg.setAlignment(Pos.CENTER);
                textDate_reg.setEditable(false);
                Hbox3.getChildren().add(textSeria);
                Hbox3.getChildren().add(textDate_reg);

                HBox Hbox2=new HBox();
                Hbox2.setPrefWidth(500);
                Hbox2.setPrefHeight(37);
                TextField textIdPassport=new TextField(account.getPassport().getId_passport());
                HBox.setMargin(textIdPassport,new Insets(0,20,0,0));
                textIdPassport.setAlignment(Pos.CENTER);
                textIdPassport.setPrefWidth(240);
                textIdPassport.setCursor(Cursor.DEFAULT);
                textIdPassport.setEditable(false);
                TextField textPosition=new TextField(account.getRole());
                textPosition.setAlignment(Pos.CENTER);
                textPosition.setPrefWidth(240);
                textPosition.setEditable(false);
                textPosition.setCursor(Cursor.DEFAULT);
                textPosition.setAlignment(Pos.CENTER);
                Hbox2.getChildren().add(textIdPassport);
                Hbox2.getChildren().add(textPosition);

                HBox hBox_inpone=new HBox();
                hBox_inpone.setPrefWidth(500);
                hBox_inpone.setPrefHeight(37);
                TextField textFildPhone=new TextField(account.getMen().getPhone());
                HBox.setMargin(textFildPhone,new Insets(0,20,0,0));
                textFildPhone.setAlignment(Pos.CENTER);
                textFildPhone.setCursor(Cursor.DEFAULT);
                textFildPhone.setEditable(false);
                textFildPhone.setPrefWidth(240);
                TextField textFildStatus=new TextField(account.getStatus());
                textFildStatus.setEditable(false);
                textFildStatus.setCursor(Cursor.DEFAULT);
                textFildStatus.setAlignment(Pos.CENTER);
                textFildStatus.setPrefWidth(240);
                hBox_inpone.getChildren().add(textFildPhone);
                hBox_inpone.getChildren().add(textFildStatus);

                vBox_text.getChildren().add(hBox_inpFIO);
                vBox_text.getChildren().add(hBox_inpone);
                vBox_text.getChildren().add(Hbox2);
                vBox_text.getChildren().add(Hbox3);


                vBox_bt.getChildren().add(remove);
                vBox_bt.getChildren().add(block);
                vBox_bt.getChildren().add(history);
            hBox.getChildren().add(vBox_img);
            hBox.getChildren().add(vBox_text);
            hBox.getChildren().add(vBox_bt);

                remove.setOnMouseClicked(mouseEvent -> {
                    try {
                        Client.getInstance().getOut().writeObject(AdminMessage.REMOVE_ACCOUNT);
                        Client.getInstance().getOut().writeObject(account);
                        if((HeaderMessage)Client.getInstance().getIn().readObject()==HeaderMessage.YOU_ACCOUNT){
                            Alert alert=new Alert(Alert.AlertType.ERROR);
                            alert.setHeaderText("Вы не можете удалить себя");
                            alert.setTitle("Ошибка удаления");
                            alert.show();
                        }
                        else {
                            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setHeaderText("Удаление прошло успешно");
                            alert.setTitle("Успешно");
                            alert.show();
                            list_account.getChildren().remove(hBox);
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                block.setOnMouseClicked(mouseEvent -> {
                    if(!block.getGlyphName().equals("UNLOCK")){
                        try {
                            Client.getInstance().getOut().writeObject(AdminMessage.BLOCK_ACC);
                            Client.getInstance().getOut().writeObject(account);
                            if((HeaderMessage)Client.getInstance().getIn().readObject()==HeaderMessage.YOU_ACCOUNT){
                                Alert alert=new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText("Вы не можете заблокировать сами себя");
                                alert.setTitle("Ошибка блокировки");
                                alert.show();
                            }
                            else {
                                block.setIcon(FontAwesomeIcon.UNLOCK);
                                textFildStatus.setText((String) Client.getInstance().getIn().readObject());
                                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText("Пользователь заблокирован успешно");
                                alert.setTitle("Успешно");
                                alert.show();
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        try {
                            Client.getInstance().getOut().writeObject(AdminMessage.UNBLOCK_ACC);
                            Client.getInstance().getOut().writeObject(account);
                            if((HeaderMessage)Client.getInstance().getIn().readObject()==HeaderMessage.YOU_ACCOUNT){
                                Alert alert=new Alert(Alert.AlertType.ERROR);
                                alert.setHeaderText("Вы не можете разблокировать сами себя");
                                alert.setTitle("Ошибка блокировки");
                                alert.show();
                            }
                            else {
                                block.setIcon(FontAwesomeIcon.LOCK);
                                textFildStatus.setText((String) Client.getInstance().getIn().readObject());
                                Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                                alert.setHeaderText("Пользователь разблокирован успешно");
                                alert.setTitle("Успешно");
                                alert.show();
                            }
                        } catch (IOException | ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });

            list_account.getChildren().add(hBox);

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void roomCustom(List<Room> list){
        list.stream().forEach(room -> {
            try {
                int check=0;
                HBox hBox;
                VBox vBox=new VBox();
                ImageConfig imageConfig=new ImageConfig();
                if(list_room.getChildren().size()==0 || ((HBox) list_room.getChildren().get(list_room.getChildren().size()-1)).getChildren().size()%2==0){
                    hBox=new HBox();
                    hBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
                    hBox.setAlignment(Pos.TOP_LEFT);
                    hBox.setMinHeight(Region.USE_COMPUTED_SIZE);
                    hBox.setMinWidth(Region.USE_COMPUTED_SIZE);
                    hBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
                    hBox.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    hBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
                    VBox.setMargin(hBox,new Insets(0,0,30,0));
                    HBox.setMargin(vBox,new Insets(0,60,0,0));
                    check=1;
                }
                else {
                    hBox=(HBox) list_room.getChildren().get(list_room.getChildren().size()-1);
                    check=-1;

                }

                vBox.getStyleClass().add("img_baground");
                vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
                vBox.setMinHeight(Region.USE_COMPUTED_SIZE);
                vBox.setMinWidth(Region.USE_COMPUTED_SIZE);
                vBox.setPrefWidth(Region.USE_COMPUTED_SIZE);
                vBox.setMaxHeight(Region.USE_PREF_SIZE);
                vBox.setMaxWidth(Region.USE_COMPUTED_SIZE);
                vBox.setAlignment(Pos.TOP_CENTER);

                HBox hBox_header=new HBox();
                hBox_header.setPrefHeight(35);
                HBox.setMargin(hBox_header,new Insets(0,0,10,0));

            Label label_header=new Label("Комната №" +room.getId());
            label_header.setPrefHeight(35);
            label_header.setPrefWidth(368);
            label_header.setFont(Font.font(20));
            label_header.setTextFill(Paint.valueOf("white"));
            label_header.setAlignment(Pos.CENTER);
            hBox_header.getChildren().add(label_header);

            HBox hBox_img=new HBox();
            hBox_img.setPrefHeight(199);
            hBox_img.setAlignment(Pos.CENTER);
            hBox_img.setMinHeight(199);
            hBox_img.setMinWidth(Region.USE_COMPUTED_SIZE);
            hBox_img.setPrefWidth(350);
            hBox_img.setMaxHeight(250);
            hBox_img.setMaxWidth(Region.USE_COMPUTED_SIZE);
            VBox.setMargin(hBox_img,new Insets(0,0,20,0));

            ImageView imageView=new ImageView();
            imageView.setImage(imageConfig.DeserializableImage(room.getListPhoto().get(0).getPhoto()));
            imageView.setPreserveRatio(false);
            imageView.setFitWidth(357);
            imageView.setFitHeight(207);
            imageView.setCursor(Cursor.HAND);
            hBox_img.getChildren().add(imageView);

            HBox hBox_description=new HBox();
                hBox_description.setPrefHeight(Region.USE_COMPUTED_SIZE);
                hBox_description.setAlignment(Pos.TOP_LEFT);
                hBox_description.setMinHeight(Region.USE_COMPUTED_SIZE);
                hBox_description.setMinWidth(Region.USE_COMPUTED_SIZE);
                hBox_description.setPrefWidth(Region.USE_COMPUTED_SIZE);
                hBox_description.setMaxHeight(Region.USE_PREF_SIZE);
                hBox_description.setMaxWidth(Region.USE_COMPUTED_SIZE);
                hBox_description.setPadding(new Insets(5,10,5,10));

            Label label_desc=new Label(room.getDescription());
            label_desc.setWrapText(true);
            label_desc.setAlignment(Pos.TOP_LEFT);
            label_desc.setFont(Font.font(15));
            label_desc.setTextFill(Paint.valueOf("white"));
                label_desc.setPrefHeight(Region.USE_COMPUTED_SIZE);
                label_desc.setAlignment(Pos.TOP_CENTER);
                label_desc.setMinHeight(Region.USE_COMPUTED_SIZE);
                label_desc.setMinWidth(Region.USE_COMPUTED_SIZE);
                label_desc.setPrefWidth(350);
                label_desc.setMaxHeight(Region.USE_COMPUTED_SIZE);
                label_desc.setMaxWidth(Region.USE_COMPUTED_SIZE);
                label_desc.setCursor(Cursor.HAND);
                hBox_description.getChildren().add(label_desc);

                vBox.getChildren().add(hBox_header);
                vBox.getChildren().add(hBox_img);
                vBox.getChildren().add(hBox_description);

                imageView.setOnMouseClicked(mouseEvent -> {
                    try {
                        dark_bg.setVisible(true);
                        Client.getInstance().getOut().writeObject(AdminMessage.VIEW_ROOM);
                        Client.getInstance().getOut().writeObject(room);
                        ViewRoomAdmin viewRoomAdmin=new ViewRoomAdmin();
                        viewRoomAdmin.Show();
                        dark_bg.setVisible(false);
                        list_room.getChildren().clear();
                        Client.getInstance().getOut().writeObject(AdminMessage.UPDATE_ALL_ROOM);
                        roomCustom((List<Room>) Client.getInstance().getIn().readObject());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

                label_desc.setOnMouseClicked(mouseEvent -> {
                    if(label_desc.getHeight()==250){
                        label_desc.setMaxHeight(Region.USE_COMPUTED_SIZE);
                    }
                    else {
                        label_desc.setMaxHeight(250);
                    }
                });

                hBox.getChildren().add(vBox);
                if(check==1){
                    list_room.getChildren().add(hBox);
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
    private void settinsIni(Account account) throws IOException, ClassNotFoundException {
        ImageConfig imageConfig=new ImageConfig();
        pers_lastName.setText(account.getMen().getLast_name());
        pers_name.setText(account.getMen().getName());
        persPatronymic.setText(account.getMen().getPatronymic());
        pers_phone.setText(account.getMen().getPhone());
        pers_id_passport.setText(account.getPassport().getId_passport());
        pers_seria.setText(account.getPassport().getSeria_number());
        pers_login.setText(account.getLogin());
        pers_password.setText(account.getPassword());
        pers_photo_avatar.setImage(imageConfig.DeserializableImage(account.getAvatar()));
        pers_photo.setImage(imageConfig.DeserializableImage(account.getAvatar()));
        pers_inst.setText(account.getNetwork().getInst());
        pers_inst.setText(account.getNetwork().getTg());
        pers_inst.setText(account.getNetwork().getVK());
        header_label_fio.setText(account.getMen().getName()+" "+account.getMen().getLast_name()+" "+account.getMen().getPatronymic());
    }
    private void statisticIni() throws IOException, ClassNotFoundException {
        stat_acc.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        stat_ban.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        stat_room.setText(Integer.toString((int) Client.getInstance().getIn().readObject()));
        String procentRoom=(String)Client.getInstance().getIn().readObject();
        progress_room.setProgress(Double.parseDouble(procentRoom.replace(",",".")));
        progress_room_label.setText(procentRoom.replace(",",".")+"%");
    }
    private void employeeIni() throws IOException, ClassNotFoundException {
        List<Employee>list=(List<Employee>)Client.getInstance().getIn().readObject();
        ObservableList<TableEmployee>tableEmployees= FXCollections.observableArrayList();
        list.stream().forEach(employee -> {
            try {
                TableEmployee tableEmployee=new TableEmployee(employee);
                tableEmployees.add(tableEmployee);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        tb_id_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("id"));
        tb_FIO_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("FIO"));
        tb_phone_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("phone"));
        tb_position_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("position"));
        tb_avatar_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("avatar"));
        tb_action_empl.setCellValueFactory(new PropertyValueFactory<TableEmployee,String>("remove"));

        tb_employee.setItems(tableEmployees);
    }

    private void initialized() throws IOException, ClassNotFoundException {
        roomCustom((List<Room>) Client.getInstance().getIn().readObject());
        settinsIni((Account) Client.getInstance().getIn().readObject());
        accountCustom((List<Account>) Client.getInstance().getIn().readObject());
        statisticIni();
        employeeIni();
        customComment((List<Review>)Client.getInstance().getIn().readObject());
    }

    private void toolClick(){
        tool_employee.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_employee,block_employee);
        });
        tool_settins.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_settins,block_settins);
        });
        tool_statistick.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_statistick,block_statistic);
        });
        tool_client.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_client,block_client);
        });
        tool_comment.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_comment,block_comment);
        });
        tool_room.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_room,block_room);
        });
        tool_exit.setOnMouseClicked(mouseEvent -> {
            try {
                Client.getInstance().getOut().writeObject(AdminMessage.EXIT);
                tool_exit.getScene().getWindow().hide();
                HeaderMenu headerMenu=new HeaderMenu();
                headerMenu.Show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        tool_add_admin.setOnMouseClicked(mouseEvent -> {
            dark_bg.setVisible(true);
            RegistrationAdminMenu adminMenu=new RegistrationAdminMenu();
            adminMenu.Show();
            dark_bg.setVisible(false);
        });
        tool_add_employee.setOnAction(actionEvent -> {
            dark_bg.setVisible(true);
            RegistrationEmployeeMenu registrationEmployeeMenu=new RegistrationEmployeeMenu();
            registrationEmployeeMenu.Show();
            dark_bg.setVisible(false);
        });
        tool_add_room.setOnAction(actionEvent -> {
            try {
                dark_bg.setVisible(true);
                RegistrationRoom registrationRoom=new RegistrationRoom();
                registrationRoom.Show();
                dark_bg.setVisible(false);
                Client.getInstance().getOut().writeObject(AdminMessage.UPDATE_ALL_ROOM);
                list_room.getChildren().clear();
                roomCustom((List<Room>) Client.getInstance().getIn().readObject());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    private void blockCLose(){
        header_background.getChildren().stream().filter(node -> node.isVisible() && node instanceof AnchorPane)
                .findAny()
                .get()
                .setVisible(false);
    }
    private void toolOpen(Node tool , AnchorPane block){
        Node check=tool_block.getChildren()
                .stream()
                .filter(node -> node.getStyleClass().contains("tool_open"))
                .findAny().orElse(null);
        if(check!=null){
            check.getStyleClass()
                    .remove("tool_open");
        }
        tool.getStyleClass().add("tool_open");
        blockCLose();
        block.setVisible(true);
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        toolClick();
    }
}
