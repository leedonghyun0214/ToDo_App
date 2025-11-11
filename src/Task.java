import java.time.LocalDate;

public class Task {
    String title;
    LocalDate date;
    boolean isDone;

    public Task(String title, LocalDate date) {
        this.title = title;
        this.date = date;
        this.isDone = false;
    }

    public void markAsDone() { this.isDone = true; }
    public void markAsTodo() { this.isDone = false; }
    public void updateTitle(String newTitle) { this.title = newTitle; }

    @Override
    public String toString() {
        String status = isDone ? "완료" : "미완료";
        return title + " | " + date + " | " + status;
    }
}


