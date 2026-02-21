package com.igrium.compilesource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CompileSourceApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        System.out.println("Opening window");
        Parent parent = FXMLLoader.load(getClass().getResource("/ui/GameConfig.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
