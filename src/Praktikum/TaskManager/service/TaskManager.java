package Praktikum.TaskManager.service;

import Praktikum.TaskManager.data.Epic;
import Praktikum.TaskManager.data.Status;
import Praktikum.TaskManager.data.Subtask;
import Praktikum.TaskManager.data.Task;
import java.util.ArrayList;
import java.util.List;

public interface TaskManager {

    void addNewTask(Task task);

    void addNewEpic(Epic epic);

    void addNewSubtasks(Subtask subtask, int byEpic);

    ArrayList<Task> getAllTasks();

    ArrayList<Epic> getAllEpics();

    ArrayList<Subtask> getAllSubtasks();

    void clearTasks();

    void clearEpics();

    void clearSubtasks();

    void removeTask(int id);

    void removeEpic(int id);

    void removeSubtask(int id);

    Task getTaskById(int id);

    Epic getEpicById(int id);

    Subtask getSubtaskById(int id);

    ArrayList<Subtask> getSubtaskByEpic(int epicId);

    void updateTask(Task task);

    void updateEpic(Epic epic);

    void updateSubtask(Subtask subtask);

    Status updateEpicStatus(int id);

    List<Task> history();
}