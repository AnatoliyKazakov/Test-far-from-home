package Praktikum.TaskManager;

import Praktikum.TaskManager.data.Epic;
import Praktikum.TaskManager.data.Status;
import Praktikum.TaskManager.data.Subtask;
import Praktikum.TaskManager.service.InMemoryTaskManager;
import Praktikum.TaskManager.data.Task;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager inMemoryTaskManager = new InMemoryTaskManager();

        inMemoryTaskManager.addNewTask(new Task("Задача 1", "Описание задачи 1"));
        inMemoryTaskManager.addNewTask(new Task("Задача 2", "Описание задачи 2"));
        inMemoryTaskManager.addNewEpic(new Epic("Эпик 1", "Описание эпика 1"));
        inMemoryTaskManager.addNewSubtasks(new Subtask("Подзадача 1 эпика 1", "Описание подзадачи 1 эпика 1"), 3);
        inMemoryTaskManager.addNewSubtasks(new Subtask("Подзадача 2 эпика 1", "Описание подзадачи 2 эпика 1"), 3);
        inMemoryTaskManager.addNewSubtasks(new Subtask("Подзадача 3 эпика 1", "Описание подзадачи 1 эпика 1"), 3);
        inMemoryTaskManager.addNewEpic(new Epic("Эпик 2", "Описание эпика 2"));


        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(2));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getSubtaskById(4));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getEpicById(3));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(2));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println(inMemoryTaskManager.getTaskById(1));
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        System.out.println();



        inMemoryTaskManager.removeTask(2);
        System.out.println("История просмотров: " + inMemoryTaskManager.history());
        inMemoryTaskManager.removeEpic(3);
        System.out.println("История просмотров: " + inMemoryTaskManager.history());


        System.out.println(inMemoryTaskManager.getAllTasks());
        System.out.println(inMemoryTaskManager.getAllEpics());
        System.out.println(inMemoryTaskManager.getAllSubtasks());
    }
}
