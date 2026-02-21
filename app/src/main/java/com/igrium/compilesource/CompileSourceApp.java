package com.igrium.compilesource;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CompileSourceApp extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        var scene = new Scene(new Label("Hello World"));
        stage.setScene(scene);
        stage.show();
    }
}
