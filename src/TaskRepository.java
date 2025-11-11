import java.util.ArrayList;
import java.util.Iterator;
import java.time.LocalDate;

public class TaskRepository {

    // 여러 Task를 저장할 리스트
    private ArrayList<Task> tasks = new ArrayList<>(); // tasks 를 외부 클래스에서는 건들이지 못하게 -> 오직 TaskRepository 에서만 건들일 수 있게. 캡슐화(encapsulation)

    // 새로운 할 일 추가
    public boolean add(Task task) { // 상태를 boolean형으로 바꿔서 호출자에게 성공/실패 전달. -> void는 반환값이 없을때만
        if (task == null) {
            System.out.println("추가 실패: task가 존재하지 않습니다.");
            return false;
        }
        if (task.title == null || task.title.isBlank()) { //title이 null은 아니지만, 비어있거나("") 공백 문자(" ")로만 이루어져 있는지 확인. isEmpty()보다 강력한 검사
            System.out.println("추가 실패: 제목은 비어있을 수 없습니다.");
            return false;
        }

        // (간단한 중복 방지) 같은 날 같은 제목이 이미 있으면 추가 거부
        for (Task t : tasks) { // for-each / t는 루프가 한 번 돌 때마다 tasks 리스트에서 꺼내온 Task 객체를 임시로 가리키는 변수
            if (t.title.equals(task.title) && t.date.equals(task.date)) {
                System.out.println("추가 실패: 같은 제목의 할 일이 금일 있습니다. → " + task.title); // 나중에는 해당 task에 id번호를 부여해서 관리하게 하는게 나을 듯.
                return false;
            }
        }

        tasks.add(task);
        System.out.println("추가 완료: " + task.title);
        return true;
    }


    // 모든 할 일 목록 출력
    public void listAll() {
        if (tasks.isEmpty()) {
            System.out.println("등록된 할 일이 없습니다.");
            return;
        }
        System.out.println("==== 할 일 목록 ====");
        for (Task t : tasks) { // for-each 문법 사용 -> Iterator 에 대해 알아둬야함.
            String status = t.isDone ? "완료" : "미완료";
            System.out.println(t.title + " / " + t.date + " / " + status);
        }
    }

    //완료된 일 삭제
    public void removeCompleted() {
        Iterator<Task> it = tasks.iterator();
        int count = 0;

        while (it.hasNext()) {
            Task t = it.next();
            if (t.isDone) {
                it.remove();
                count++;
                System.out.println("완료된 할 일 삭제: " + t.title);
            }
        }

        if (count == 0) {
            System.out.println("삭제할 완료된 할 일이 없습니다.");
        } else {
            System.out.println("총 " + count + "개의 완료된 할 일을 삭제했습니다.");
        }
    }


    // 제목 및 날짜로 할 일 삭제 + iterator 사용
    public int removeByTitleAndDate(String title, LocalDate date) {
        if (title == null || title.isBlank() || date == null) return 0;

        int removed = 0;
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task t = it.next();
            if (title.equals(t.title) && date.equals(t.date)) {
                it.remove();
                removed++;
                System.out.println("삭제 완료: " + t.title + " / " + t.date);
            }
        }
        if (removed == 0) {
            System.out.println("삭제할 항목이 없습니다.");
        } else {
            System.out.println("총 " + removed + "개 삭제.");
        }
        return removed;
    }

    // 완료 표시
    public boolean markDoneByTitle(String title) {
        if (title == null || title.isBlank()) return false;

        boolean found = false;

        for (Task t : tasks) {
            if (title.equals(t.title)) {
                if (t.isDone) {
                    System.out.println("이미 완료된 항목입니다: " + t.title);
                } else {
                    t.markAsDone();
                    System.out.println("완료 처리: " + t.title);
                }
                found = true; // 존재는 함
            }
        }

        if (!found) {
            System.out.println("해당 제목의 할 일을 찾을 수 없습니다: " + title);
        }
        return found;
    }

}
