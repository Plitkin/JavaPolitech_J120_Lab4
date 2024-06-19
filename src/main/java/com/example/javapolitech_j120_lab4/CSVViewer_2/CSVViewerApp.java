package com.example.javapolitech_j120_lab4.CSVViewer_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class CSVViewerApp extends Application {

    private CSVTableView csvTableView;
    private CSVFileManager csvFileManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("CSV Viewer");

        csvTableView = new CSVTableView();
        csvFileManager = new CSVFileManager(csvTableView, primaryStage);

        MenuBar menuBar = createMenuBar();

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setCenter(csvTableView.getTableView());
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #2c3e50;");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Метод для создания строки меню
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem openMenuItem = new MenuItem("Open");
        openMenuItem.setOnAction(e -> csvFileManager.openCSVFile());
        fileMenu.getItems().add(openMenuItem);
        menuBar.getMenus().add(fileMenu);
        return menuBar;
    }
}
