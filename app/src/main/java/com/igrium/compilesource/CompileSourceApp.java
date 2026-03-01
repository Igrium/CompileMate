package com.igrium.compilesource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.igrium.compilesource.config.GameConfig;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CompileSourceApp extends Application {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Getter
    private static CompileSourceApp instance;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        System.out.println("Opening window");
        // noinspection ConstantConditions
        Parent parent = FXMLLoader.load(getClass().getResource("/ui/MainWindow.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }


}
