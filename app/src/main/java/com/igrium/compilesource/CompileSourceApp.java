package com.igrium.compilesource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.igrium.compilesource.config.Config;
import com.igrium.compilesource.ui.MainWindowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.NonNull;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.CompletableFuture;

public class CompileSourceApp extends Application {

    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private static final Path CONFIG_FILE = Paths.get("config.json");;

    @Getter
    private static CompileSourceApp instance;

    @Getter @NonNull
    private Config config = new Config();

    @Getter
    private MainWindowController mainWindow;

    @Override
    public void start(Stage stage) throws Exception {
        instance = this;
        try {
            loadOrCreateConfig();
        } catch (Exception e) {
            //noinspection CallToPrintStackTrace
            e.printStackTrace();
        }

        // noinspection ConstantConditions
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ui/MainWindow.fxml"));
        Parent parent = loader.load();
        mainWindow = loader.getController();

        stage.setScene(new Scene(parent));
        stage.show();
    }

    private static final Object configFileMutex = new Object();

    public void loadOrCreateConfig() throws IOException {
        synchronized (configFileMutex) {
            if (Files.isRegularFile(CONFIG_FILE)) {
                try (BufferedReader reader = Files.newBufferedReader(CONFIG_FILE)) {
                    config = GSON.fromJson(reader, Config.class);
                }
                System.out.println("Loaded config from " + CONFIG_FILE);
            } else {
                saveConfig();
            }
        }
    }

    public void saveConfig() {
        synchronized (configFileMutex) {
            try (BufferedWriter writer = Files.newBufferedWriter(CONFIG_FILE)) {
                GSON.toJson(config, writer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Saved config to " + CONFIG_FILE);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void saveConfigAsync() {
        CompletableFuture.runAsync(this::saveConfig).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
