package org.example.javasuitejavafx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HomeFunctionality {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}