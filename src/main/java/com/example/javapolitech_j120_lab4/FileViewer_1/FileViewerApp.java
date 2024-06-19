package com.example.javapolitech_j120_lab4.FileViewer_1;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class FileViewerApp extends Application {

    private FileListView fileListView;
    private FileContentView fileContentView;
    private FileManager fileManager;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("File Viewer");

        fileContentView = new FileContentView();
        fileManager = new FileManager(fileContentView);
        fileListView = new FileListView(fileManager, primaryStage);

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.HORIZONTAL);
        splitPane.getItems().addAll(fileListView.getListView(), fileContentView.getTextArea());
        splitPane.setDividerPositions(0.3);

        BorderPane root = new BorderPane();
        root.setCenter(splitPane);
        root.setPadding(new Insets(10));
        root.setStyle("-fx-background-color: #2c3e50;");

        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        fileListView.updateFileList();
    }
}
