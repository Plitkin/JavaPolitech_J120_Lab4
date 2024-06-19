package com.example.javapolitech_j120_lab4.FileViewer_1;

import javafx.scene.control.TextArea;

public class FileContentView {

    private TextArea textArea;

    public FileContentView() {
        textArea = new TextArea();
        textArea.setEditable(false);
        styleTextArea();
    }

    public TextArea getTextArea() {
        return textArea;
    }

    // Метод для отображения содержимого файла
    public void displayContent(String content) {
        textArea.setText(content);
    }

    // Метод для очистки содержимого
    public void clearContent() {
        textArea.clear();
    }

    // Метод для стилизации текстовой области
    private void styleTextArea() {
        textArea.setStyle("-fx-control-inner-background: #333; -fx-text-fill: #e1e1e1;");
    }
}
