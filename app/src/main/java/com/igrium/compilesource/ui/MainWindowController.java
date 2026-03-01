package com.igrium.compilesource.ui;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class MainWindowController {
    @FXML
    private CheckBox postExistsCheck;

    @FXML
    private TextField postExistsField;

    @FXML
    void initialize() {
        postExistsField.disableProperty().bind(postExistsCheck.selectedProperty().not());
    }
}
