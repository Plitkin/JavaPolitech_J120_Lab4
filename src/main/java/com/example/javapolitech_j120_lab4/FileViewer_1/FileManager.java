package com.example.javapolitech_j120_lab4.FileViewer_1;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager {

    private File currentDirectory;
    private FileContentView fileContentView;

    public FileManager(FileContentView fileContentView) {
        this.fileContentView = fileContentView;
        currentDirectory = new File(System.getProperty("user.dir"));
    }

    // Метод для получения текущего пути директории
    public String getCurrentDirectoryPath() {
        return currentDirectory.getAbsolutePath();
    }

    // Метод для получения списка файлов и директорий
    public List<String> getFileList() {
        File[] files = currentDirectory.listFiles();
        if (files != null) {
            return Arrays.stream(files)
                    .sorted(Comparator.comparing(File::isFile).thenComparing(File::getName))
                    .map(File::getName)
                    .collect(Collectors.toList());
        }
        return List.of();
    }

    // Метод для отображения содержимого файла
    public void displayFileContent(String fileName) {
        File selectedFile = new File(currentDirectory, fileName);
        if (selectedFile.isFile()) {
            try {
                String content = new String(Files.readAllBytes(selectedFile.toPath()));
                fileContentView.displayContent(content);
            } catch (IOException e) {
                fileContentView.displayContent("Failed to load file content.");
                showAlert("Error", "Failed to load file content.");
            }
        } else {
            fileContentView.clearContent();
        }
    }

    // Метод для открытия файла или директории
    public void openFileOrDirectory(String fileName) {
        File selectedFile = new File(currentDirectory, fileName);
        if (selectedFile.isDirectory()) {
            currentDirectory = selectedFile;
        }
    }

    // Метод для перехода на уровень выше
    public void navigateUp() {
        File parentDirectory = currentDirectory.getParentFile();
        if (parentDirectory != null) {
            currentDirectory = parentDirectory;
        }
    }

    // Метод для отображения сообщений об ошибках
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
