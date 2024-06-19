package com.example.javapolitech_j120_lab4.CSVViewer_2;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CSVFileManager {

    private CSVTableView csvTableView;
    private Stage primaryStage;

    public CSVFileManager(CSVTableView csvTableView, Stage primaryStage) {
        this.csvTableView = csvTableView;
        this.primaryStage = primaryStage;
    }

    // Метод для открытия CSV-файла
    public void openCSVFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV Files", "*.csv"));
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            primaryStage.setTitle(selectedFile.getName());
            loadCSVFile(selectedFile);
        }
    }

    // Метод для загрузки CSV-файла и отображения его содержимого
    private void loadCSVFile(File file) {
        csvTableView.clearTable();

        try {
            List<String> lines = Files.readAllLines(file.toPath());
            if (lines.isEmpty()) {
                AlertManager.showAlert("Error", "The file is empty.");
                return;
            }

            String[] headers = lines.get(0).split(",");
            csvTableView.addColumns(headers);

            for (int i = 1; i < lines.size(); i++) {
                String[] values = lines.get(i).split(",");
                if (values.length != headers.length) {
                    AlertManager.showAlert("Error", "Row " + (i + 1) + " has a different number of columns.");
                    return;
                }
                List<String> rowValues = new ArrayList<>();
                for (String value : values) {
                    rowValues.add(value);
                }
                csvTableView.addRow(rowValues);
            }

        } catch (IOException e) {
            AlertManager.showAlert("Error", "Failed to load the file.");
        }
    }
}

