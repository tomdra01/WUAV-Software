package dk.easv.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ProjectTemplateController implements Initializable {
    @FXML private ImageView projectImg;


    public ImageView getProjectImg() {
        return projectImg;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
