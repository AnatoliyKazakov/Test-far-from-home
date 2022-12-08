package Praktikum.TaskManager.data;

public class Subtask extends Task {
    private int byEpic;

    public Subtask(String title, String description) {
        super(title, description);
    }

    public int getByEpic() {
        return byEpic;
    }

    public void setByEpic(int byEpic) {
        this.byEpic = byEpic;
    }
}