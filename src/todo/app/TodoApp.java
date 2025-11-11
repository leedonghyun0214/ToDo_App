package todo.app;

import todo.model.Task;
import todo.repository.TaskRepository;
import todo.util.InputUtils;

import java.time.LocalDate;
import java.util.Scanner;

public class TodoApp { // ← Main 대신 TodoApp 권장
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskRepository repo = new TaskRepository();

        boolean running = true;
        while (running) {
            System.out.println("\n==== To-Do List ====");
            System.out.println("1. 할 일 추가");
            System.out.println("2. 전체 목록 보기");
            System.out.println("3. 완료 표시");
            System.out.println("4. 완료된 항목 삭제");
            System.out.println("5. 제목 + 날짜로 삭제");
            System.out.println("6. 종료");
            System.out.print("메뉴 선택: ");

            int choice = InputUtils.readInt(sc);

            switch (choice) {
                case 1 -> { // 할 일 추가
                    System.out.print("할 일 제목: ");
                    String title = sc.nextLine().trim();

                    System.out.print("날짜 입력 (예: 2025-10-28): ");
                    LocalDate date = InputUtils.readSimpleDate(sc);

                    boolean added = repo.add(new Task(title, date));
                    System.out.println(added ? "✅ 추가되었습니다." : "❌ 추가 실패(중복/유효성)");
                }
                case 2 -> {
                    System.out.println("\n--- 전체 목록 ---");
                    System.out.println(repo.formattedList()); // 리스트 출력
                }
                case 3 -> { // 완료 표시
                    System.out.print("완료로 표시할 제목: ");
                    String doneTitle = sc.nextLine().trim();
                    boolean ok = repo.markDoneByTitle(doneTitle);
                    System.out.println(ok ? "✅ 완료 처리" : "❌ 해당 제목 없음");
                }
                case 4 -> {
                    int deleted = repo.removeCompleted();
                    System.out.println("🗑 완료된 항목 " + deleted + "개 삭제");
                }
                case 5 -> { // 제목+날짜로 삭제
                    System.out.print("삭제할 제목: ");
                    String delTitle = sc.nextLine().trim();

                    System.out.print("삭제할 날짜 (예: 2025-10-28): ");
                    LocalDate date = InputUtils.readSimpleDate(sc);

                    int removed = repo.removeByTitleAndDate(delTitle, date);
                    System.out.println("🗑 삭제된 항목 수: " + removed);
                }
                case 6 -> {
                    System.out.println("👋 프로그램을 종료합니다.");
                    // sc.close(); // 권장: 생략 (System.in 닫힘 이슈 방지)
                    running = false;
                }
                default -> System.out.println("잘못된 입력입니다.");
            }
        }
    }
}


    /* 공통: 정규식 검증 + 유연 파싱으로 LocalDate 받기
    private static LocalDate readDate(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine();
            try {
                return todo.util.DateUtil.parseFlexible(input);
            } catch (Exception e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }
}*/