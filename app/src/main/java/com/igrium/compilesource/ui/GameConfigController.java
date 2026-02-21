package com.igrium.compilesource.ui;

import com.igrium.compilesource.config.GameConfig;
import javafx.beans.property.Property;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;

import java.io.File;

public class GameConfigController {
    @FXML
    private Parent root;

    @FXML
    private TextField gameExeField;

    @FXML
    private TextField gameDirField;

    @FXML
    private TextField bspExeField;

    @FXML
    private TextField visExeField;

    @FXML
    private TextField radExeField;

    @FXML
    private TextField mapDirField;

    @Getter
    private @Nullable GameConfig config;

    public void setConfig(GameConfig config) {
        this.config = config;
        if (config == null)
            return;
        gameExeField.setText(config.getGameExe());
        gameDirField.setText(config.getGameDir());
        bspExeField.setText(config.getBspExe());
        visExeField.setText(config.getVisExe());
        radExeField.setText(config.getRadExe());
        mapDirField.setText(config.getMapDir());
    }

    @FXML
    public void browseGameExe() {
        showFileChooser("Pick Game Executable", gameExeField.textProperty());
    }

    @FXML
    public void browsGameDir() {
        showDirChooser("Pick Gameinfo Directory", gameDirField.textProperty());
    }

    @FXML
    public void browsBspExe() {
        showFileChooser("Pick VBSP Executable", bspExeField.textProperty());
    }

    @FXML
    public void browsVisExe() {
        showFileChooser("Pick VVIS Executable", visExeField.textProperty());
    }

    @FXML
    public void browseRadExe() {
        showFileChooser("Pick VRAD Executable", radExeField.textProperty());
    }

    @FXML
    public void browseMapDir() {
        showDirChooser("Pick Map Destination", mapDirField.textProperty());
    }

    public void applyConfig() {
        if (config == null) {
            System.err.println("Config object not initialized!");
            return;
        }

        config.setGameExe(gameExeField.getText());
        config.setGameDir(gameDirField.getText());
        config.setBspExe(bspExeField.getText());
        config.setVisExe(visExeField.getText());
        config.setRadExe(radExeField.getText());
        config.setMapDir(mapDirField.getText());
    }

    private void showFileChooser(String title, Property<String> textProperty) {
        File initial = new File(textProperty.getValue());
        FileChooser fc = new FileChooser();

        fc.setTitle(title);
        fc.setInitialFileName(initial.getName());
        fc.setInitialDirectory(initial.getParentFile());

        File chosen = fc.showOpenDialog(root.getScene().getWindow());
        if (chosen != null) {
            textProperty.setValue(chosen.toString());
        }
    }

    private void showDirChooser(String title, Property<String> textProperty) {
        File initial = new File(textProperty.getValue());
        DirectoryChooser dc = new DirectoryChooser();

        dc.setTitle(title);
        dc.setInitialDirectory(initial);

        File chosen = dc.showDialog(root.getScene().getWindow());
        if (chosen != null) {
            textProperty.setValue(chosen.toString());
        }
    }
}
