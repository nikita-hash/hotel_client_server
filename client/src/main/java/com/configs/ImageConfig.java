package com.configs;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class ImageConfig {
    public String SerializableImage(Image image)throws IOException {
        byte[] byteArrayOutputStream = new byte[0];
        ByteArrayOutputStream b=new ByteArrayOutputStream(10000);
        BufferedImage buf= ImageIO.read(new ByteArrayInputStream(byteArrayOutputStream));
        buf= SwingFXUtils.fromFXImage(image,buf);
        ImageIO.write(buf, "", b);
        String photo= Base64.getEncoder().encodeToString(b.toByteArray());
        return photo;
    }
    public String SerializableImage(File file_image_user) throws IOException {
        file_image_user.getAbsolutePath().replaceAll("%"," ");
        System.out.println(file_image_user.getAbsolutePath());
        BufferedImage image= ImageIO.read(file_image_user);
        ByteArrayOutputStream b=new ByteArrayOutputStream(10000);
        ImageIO.write(image, FileConfig.getFileConfigur().getFileExtension(file_image_user), b);
        b.flush();
        String msg=Base64.getEncoder().encodeToString(b.toByteArray());
        return msg;
    }
    public Image DeserializableImage( String msg) throws IOException, ClassNotFoundException {
        byte[] byteArrayOutputStream= Base64.getDecoder().decode(msg);
        BufferedImage image1= ImageIO.read(new ByteArrayInputStream(byteArrayOutputStream));
        Image image= SwingFXUtils.toFXImage(image1, null);
        return image;
    }
}
