package Praktikum.TaskManager.service;

import Praktikum.TaskManager.data.Task;

import java.util.ArrayList;
import java.util.List;

public class CustomLinkedList {
    private Node head;
    private Node tail;

    class Node {
        private Task data;
        private Node next;
        private Node prev;

        public Node(Task data) {
            this.data = data;
        }
    }

    public Node linkLast(Task task) {
        Node node = new Node(task);
        if (head == null) {
            head = node;
            tail = node;
            head.prev = null;
            tail.next = null;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
            tail.next = null;
        }
        return node;
    }

    public List<Task> getTasks() {
        List<Task> result = new ArrayList<>();
        Node current = head;

        while (current != null) {
            result.add(current.data);
            current = current.next;
        }
        return result;
    }

    public void removeNode(Node taskForRemove) {
        if (head == null || taskForRemove == null) {
            return;
        }

        if (head == taskForRemove) {
            head = taskForRemove.next;
        }

        if (taskForRemove.next != null) {
            taskForRemove.next.prev = taskForRemove.prev;
        }

        if (taskForRemove.prev != null) {
            taskForRemove.prev.next = taskForRemove.next;
        }
    }
}