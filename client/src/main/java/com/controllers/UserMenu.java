package com.controllers;

import com.client.Client;
import com.configs.ImageConfig;
import com.message.AdminMessage;
import com.message.UserMessage;
import com.models.Account;
import com.models.Review;
import com.models.Room;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import io.github.gleidson28.AvatarType;
import io.github.gleidson28.GNAvatarView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static java.lang.Long.MAX_VALUE;

public class UserMenu {

    Stage stage;

    public  void Show(){
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/templates/user_menu.fxml"));
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
        stage.show();
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane block_comment;

    @FXML
    private AnchorPane block_settins;

    @FXML
    private Label header_label_fio;

    @FXML
    private Label label_sec;

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
    private Button tool_add_review;

    @FXML
    private VBox tool_block;

    @FXML
    private Button tool_comment;

    @FXML
    private AnchorPane header_background;

    @FXML
    private Button tool_exit;

    @FXML
    private HBox tool_history;

    @FXML
    private HBox tool_room;

    @FXML
    private VBox list_room;

    @FXML
    private AnchorPane dark_bg;

    @FXML
    private AnchorPane block_room;

    @FXML
    private VBox list_view;

    @FXML
    private HBox tool_settins;

    @FXML
    private HBox tool_statistick;

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
                        Client.getInstance().getOut().writeObject(UserMessage.VIEW_ROOM);
                        Client.getInstance().getOut().writeObject(room);
                        ViewRoomUser viewRoomUser=new ViewRoomUser();
                        viewRoomUser.Show();
                        dark_bg.setVisible(false);
                        list_room.getChildren().clear();
                        Client.getInstance().getOut().writeObject(UserMessage.UPDATE_ALL_ROOM);
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
    private void initialized() throws IOException, ClassNotFoundException {
        customComment((List<Review>) Client.getInstance().getIn().readObject());
        settinsIni((Account) Client.getInstance().getIn().readObject());
        roomCustom((List<Room>)Client.getInstance().getIn().readObject());
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
    private void blockCLose(){
        header_background.getChildren().stream().filter(node -> node.isVisible() && node instanceof AnchorPane)
                .findAny()
                .get()
                .setVisible(false);
    }

    private void toolClick(){
        tool_comment.setOnAction(actionEvent -> {
            toolOpen(tool_comment,block_comment);
        });
        tool_room.setOnMouseClicked(mouseEvent -> {
            toolOpen(tool_room,block_room);
        });
        tool_add_review.setOnAction(actionEvent -> {
            dark_bg.setVisible(true);
            AddReviewMenu addReviewMenu=new AddReviewMenu();
            addReviewMenu.Show();
            dark_bg.setVisible(false);
        });
    }

    @FXML
    void initialize() throws IOException, ClassNotFoundException {
        initialized();
        toolClick();

    }

}
