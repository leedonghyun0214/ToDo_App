package todo.repository;

import todo.model.Task;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskRepository {
    // 내부 컬렉션은 List 인터페이스로 선언(유연성 ↑)
    private final List<Task> tasks = new ArrayList<>();

    // 새 Task 추가 (중복/유효성 검사만 하고, 출력은 하지 않음)
    public boolean add(Task task) {
        if (task == null) return false;

        String title = task.getTitle();
        LocalDate date = task.getDate();
        if (title == null || title.isBlank() || date == null) return false;

        boolean dup = tasks.stream()
                .anyMatch(t -> title.equals(t.getTitle()) && date.equals(t.getDate()));
        if (dup) return false;

        tasks.add(task);
        return true;
    }

    // 전체 목록 조회(방어적 복사)
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    // 완료 표시(제목 1개 대상으로)
    public boolean markDoneByTitle(String title) {
        if (title == null || title.isBlank()) return false;

        boolean found = false;
        for (Task t : tasks) {
            if (title.equals(t.getTitle())) {
                if (!t.isDone()) {
                    t.markDone();     // Task의 메서드 이름과 일치
                }
                found = true;
            }
        }
        return found;
    }

    // 완료된 항목 일괄 삭제 (삭제 개수 리턴)
    public int removeCompleted() {
        int count = 0;
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (t.isDone()) {         // 필드 접근 X, 메서드 사용
                it.remove();
                count++;
            }
        }
        return count;
    }

    // 제목 + 날짜로 삭제 (삭제 개수 리턴)
    public int removeByTitleAndDate(String title, LocalDate date) {
        if (title == null || title.isBlank() || date == null) return 0;

        int removed = 0;
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (title.equals(t.getTitle()) && date.equals(t.getDate())) {
                it.remove();
                removed++;
            }
        }
        return removed;
    }

    // 콘솔 출력용 문자열(편의 기능) — 출력은 App에서 수행
    public String formattedList() {
        if (tasks.isEmpty()) return "(항목 없음)";
        return tasks.stream()
                .map(Task::toString)
                .collect(Collectors.joining("\n"));
    }
}
