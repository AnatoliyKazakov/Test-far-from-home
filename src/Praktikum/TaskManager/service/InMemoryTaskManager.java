package Praktikum.TaskManager.service;

import Praktikum.TaskManager.data.Epic;
import Praktikum.TaskManager.data.Status;
import Praktikum.TaskManager.data.Subtask;
import Praktikum.TaskManager.data.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    private static final HistoryManager historyManager = Managers.getDefaultHistory();
    private final HashMap<Integer, Task> taskMap = new HashMap<>();
    private final HashMap<Integer, Epic> epicMap = new HashMap<>();
    private final HashMap<Integer, Subtask> subtaskMap = new HashMap<>();
    private int nextId = 1;

    @Override
    public Task getTaskById(int id) {
        historyManager.add(taskMap.get(id));
        return taskMap.get(id);
    }

    @Override
    public Epic getEpicById(int id) {
        historyManager.add(epicMap.get(id));
        return epicMap.get(id);
    }

    @Override
    public Subtask getSubtaskById(int id) {
        historyManager.add(subtaskMap.get(id));
        return subtaskMap.get(id);
    }

    @Override
    public void addNewTask(Task task) {
        task.setId(nextId++);
        task.setStatus(Status.NEW);
        taskMap.put(task.getId(), task);
    }

    @Override
    public void addNewEpic(Epic epic) {
        epic.setId(nextId++);
        epic.setStatus(Status.NEW);
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public void addNewSubtasks(Subtask subtask, int byEpic) {
        epicMap.get(byEpic).getSubtaskIds().add(nextId);
        subtask.setId(nextId++);
        subtask.setByEpic(epicMap.get(byEpic).getId());
        subtask.setStatus(Status.NEW);
        subtaskMap.put(subtask.getId(), subtask);
    }

    @Override
    public ArrayList<Task> getAllTasks() {
        return new ArrayList<>(taskMap.values());
    }

    @Override
    public ArrayList<Epic> getAllEpics() {
        return new ArrayList<>(epicMap.values());
    }

    @Override
    public ArrayList<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtaskMap.values());
    }

    @Override
    public void clearTasks() {
        epicMap.clear();
    }

    @Override
    public void clearEpics() {
        epicMap.clear();
        subtaskMap.clear();
    }

    public void clearSubtasks() {
        subtaskMap.clear();
    }

    @Override
    public void removeTask(int id) {
        taskMap.remove(id);
        historyManager.remove(id);
        System.out.println("Задача " + id + " удалена");
    }

    @Override
    public void removeEpic(int id) {
        for (int key : epicMap.get(id).getSubtaskIds()) {
            subtaskMap.remove(key);
            historyManager.remove(key);
        }
        epicMap.remove(id);
        historyManager.remove(id);
        System.out.println("Эпик " + id + " удалён");
    }

    @Override
    public void removeSubtask(int id) {
        Epic epic = epicMap.get(subtaskMap.get(id).getByEpic());
        subtaskMap.remove(id);
        historyManager.remove(id);
        updateEpic(epic);
        System.out.println("Подзадача " + id + " удалена");
    }

    @Override
    public ArrayList<Subtask> getSubtaskByEpic(int epicId) {
        HashMap<Integer, Subtask> subtaskList = new HashMap<>();
        for (int id : getEpicById(epicId).getSubtaskIds()) {
            if (subtaskMap.get(id) != null) {
                subtaskList.put(id, subtaskMap.get(id));
            }
        }
        return new ArrayList<>(subtaskList.values());
    }

    @Override
    public void updateTask(Task task) {
        taskMap.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epic.setStatus(updateEpicStatus(epic.getId()));
        epicMap.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtaskMap.put(subtask.getId(), subtask);
        Epic epic = epicMap.get(subtask.getByEpic());
        updateEpic(epic);
    }

    @Override
    public Status updateEpicStatus(int id) {
        Status status = Status.NEW;
        ArrayList<Subtask> subtasksByEpic = getSubtaskByEpic(id);
        for (Subtask subtask : subtasksByEpic) {
            if (subtask.getStatus().equals(Status.DONE)) {
                status = Status.DONE;
            } else if (subtask.getStatus().equals(Status.IN_PROGRESS) ||
                    (subtask.getStatus().equals(Status.DONE) && status.equals(Status.NEW))) {
                status = Status.IN_PROGRESS;
                break;
            } else {
                status = Status.NEW;
            }
        }
        return status;
    }

    @Override
    public List<Task> history() {
        return historyManager.getHistory();
    }
}