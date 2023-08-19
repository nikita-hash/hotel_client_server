module com {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.base;
    requires javafx.graphics;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires de.jensd.fx.glyphs.fontawesome;
    requires GNAvatarView;
    requires javafx.swing;
    requires org.apache.commons.lang3;


    opens com.main to javafx.fxml;
    exports com.main;
    opens com.controllers to javafx.fxml;
    exports com.controllers;
    opens com.table to javafx.base;
    exports com.table ;
}