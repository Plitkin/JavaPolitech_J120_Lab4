package com.example.javapolitech_j120_lab4.CSVViewer_2;

import javafx.scene.control.Alert;

public class AlertManager {

    // Метод для отображения сообщений об ошибках
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
