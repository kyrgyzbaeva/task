package com.example.task;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class TaskManagerController {

    @FXML
    private ListView<Task> listView;
    @FXML
    private TextField taskNameField;
    @FXML
    private TextField taskDescriptionField;
    @FXML
    private ComboBox<Priority> priorityComboBox;
    @FXML
    private Button addButton;
    @FXML
    private Button completeButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Label selectedTaskLabel;

    private ObservableList<Task> tasks = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        listView.setItems(tasks);
        priorityComboBox.setItems(FXCollections.observableArrayList(Priority.values()));
        completeButton.setDisable(true);
        deleteButton.setDisable(true);
    }

    @FXML
    public void onAddButtonClicked() {
        // Добавление новой задачи в список
        HomeworkTask newTask = new HomeworkTask();
        newTask.setTask(taskNameField.getText(), taskDescriptionField.getText());
        newTask.setPriority(priorityComboBox.getValue());
        tasks.add(newTask);

        // Очистка полей ввода
        taskNameField.clear();
        taskDescriptionField.clear();
        priorityComboBox.setValue(null);
    }

    @FXML
    public void onCompleteButtonClicked() {
        // Отметка задачи как выполненной
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = listView.getItems().get(selectedIndex);
            selectedTask.markAsComplete();
        }
    }

    @FXML
    public void onDeleteButtonClicked() {
        // Удаление выбранной задачи из списка
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            listView.getItems().remove(selectedIndex);
        }
    }

    @FXML
    public void onListClicked() {
        // Сохраняет
        int selectedIndex = listView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Task selectedTask = listView.getItems().get(selectedIndex);
            selectedTaskLabel.setText(selectedTask.toString());
            completeButton.setDisable(selectedTask.isComplete());
            deleteButton.setDisable(false);
        }
    }
}
