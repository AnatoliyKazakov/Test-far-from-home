package Praktikum.TaskManager.service;

import Praktikum.TaskManager.data.Task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {
    CustomLinkedList history = new CustomLinkedList();
    Map<Integer, CustomLinkedList.Node> historyMap = new HashMap<>();
    int MAX_VALUES = 10;

    @Override
    public void add(Task task) {
        if (getHistory().size() == MAX_VALUES) {
            remove(0);
        }
        if (historyMap.containsKey(task.getId())) {
            remove(task.getId());
        }
        CustomLinkedList.Node node = history.linkLast(task);
        historyMap.put(task.getId(), node);
    }

    @Override
    public void remove(int id) {
        CustomLinkedList.Node taskForRemove = historyMap.get(id);
        history.removeNode(taskForRemove);
        historyMap.remove(id);
    }

    @Override
    public List<Task> getHistory() {
        return history.getTasks();
    }
}
