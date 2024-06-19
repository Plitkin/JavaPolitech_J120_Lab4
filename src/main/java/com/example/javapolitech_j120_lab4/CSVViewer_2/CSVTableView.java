package com.example.javapolitech_j120_lab4.CSVViewer_2;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class CSVTableView {

    private TableView<List<String>> tableView;

    public CSVTableView() {
        tableView = new TableView<>();
        styleTableView();
    }

    public TableView<List<String>> getTableView() {
        return tableView;
    }

    // Метод для добавления столбцов в таблицу
    public void addColumns(String[] headers) {
        tableView.getColumns().clear();
        for (String header : headers) {
            TableColumn<List<String>, String> column = new TableColumn<>(header);
            int columnIndex = tableView.getColumns().size();
            column.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(columnIndex)));
            column.setStyle("-fx-background-color: #626262; -fx-text-fill: white;");
            tableView.getColumns().add(column);
        }
    }

    // Метод для добавления строк в таблицу
    public void addRow(List<String> rowValues) {
        tableView.getItems().add(rowValues);
    }

    // Метод для очистки таблицы
    public void clearTable() {
        tableView.getItems().clear();
        tableView.getColumns().clear();
    }

    // Метод для стилизации таблицы
    private void styleTableView() {
        tableView.setStyle("-fx-background-color: #333; -fx-text-fill: white;");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }
}

