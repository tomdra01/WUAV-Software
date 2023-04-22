package dk.easv.gui.controller;

import dk.easv.util.BlurEffectUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private void handleNewProject(ActionEvent actionEvent) throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/new_project_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("New Project");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        NewProjectWindowController newProjectWindowController = fxmlLoader.getController();
        newProjectWindowController.setAnchorPane(anchorPane);
        newProjectWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    @FXML
    private void handleCustomers(ActionEvent actionEvent) throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/customers_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Customers");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        CustomersWindowController customersWindowController = fxmlLoader.getController();
        customersWindowController.setAnchorPane(anchorPane);
        customersWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    @FXML
    private void handleReports(ActionEvent actionEvent) throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/reports_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Reports");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        ReportsWindowController reportsWindowController = fxmlLoader.getController();
        reportsWindowController.setAnchorPane(anchorPane);
        reportsWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    @FXML
    private void handleSettings(ActionEvent actionEvent) throws IOException {
        BlurEffectUtil.applyBlurEffect(anchorPane,10);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/settings_window.fxml"));
        Parent createEventParent = fxmlLoader.load();

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Settings");
        stage.setResizable(false);
        Scene scene = new Scene(createEventParent);
        stage.setScene(scene);

        SettingsWindowController settingsWindowController = fxmlLoader.getController();
        settingsWindowController.setAnchorPane(anchorPane);
        settingsWindowController.setOnCloseRequestHandler(stage);

        stage.show();
    }

    @FXML
    private void handleLogOut(ActionEvent actionEvent){

    }
}
