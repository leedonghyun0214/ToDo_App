package todo.model;

import java.time.LocalDate;
import java.util.Objects;

public class Task {
    // 캡슐화: 외부에서 직접 못 만지게 private
    private String title;
    private LocalDate date;
    private boolean isDone;

    public Task(String title, LocalDate date) {
        this.title = title;
        this.date = date;
        this.isDone = false;
    }

    // === 레포지토리가 기대하는 표준 메서드 ===
    public String getTitle() { return title; }
    public LocalDate getDate() { return date; }
    public boolean isDone() { return isDone; }
    public void markDone() { this.isDone = true; }

    // === 네가 쓰던 이름도 그대로 지원(호환용) ===
    public void markAsDone() { markDone(); }
    public void markAsTodo() { this.isDone = false; }
    public void updateTitle(String newTitle) { this.title = newTitle; }

    @Override
    public String toString() {
        String status = isDone ? "완료" : "미완료";
        return title + " | " + date + " | " + status;
    }

    // (선택) 동일성 기준: 제목+날짜
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task task = (Task) o;
        return Objects.equals(title, task.title) &&
                Objects.equals(date, task.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, date);
    }
}
