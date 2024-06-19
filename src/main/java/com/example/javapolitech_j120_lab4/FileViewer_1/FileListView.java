package com.example.javapolitech_j120_lab4.FileViewer_1;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class FileListView {

    private ListView<String> listView;
    private FileManager fileManager;
    private Stage primaryStage;

    public FileListView(FileManager fileManager, Stage primaryStage) {
        this.fileManager = fileManager;
        this.primaryStage = primaryStage;
        listView = new ListView<>();
        setupEventHandlers();
        styleListView();
    }

    public ListView<String> getListView() {
        return listView;
    }

    // Метод для обновления списка файлов
    public void updateFileList() {
        listView.getItems().clear();
        listView.getItems().add("..");
        for (String item : fileManager.getFileList()) {
            listView.getItems().add(item);
        }
        primaryStage.setTitle(fileManager.getCurrentDirectoryPath());
    }

    // Метод для установки обработчиков событий
    private void setupEventHandlers() {
        listView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                openSelectedFileOrDirectory();
            }
        });

        listView.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                openSelectedFileOrDirectory();
            }
        });

        listView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.equals("..")) {
                fileManager.displayFileContent(newValue);
            }
        });
    }

    // Метод для открытия выбранного файла или директории
    private void openSelectedFileOrDirectory() {
        String selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            if (selected.equals("..")) {
                fileManager.navigateUp();
            } else {
                fileManager.openFileOrDirectory(selected);
            }
            updateFileList();
        }
    }

    // Метод для стилизации списка файлов
    private void styleListView() {
        listView.setStyle("-fx-background-color: #333; -fx-text-fill: white; -fx-control-inner-background: #444;");
    }
}
