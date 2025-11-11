import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskRepository repo = new TaskRepository();

        while (true) {
            System.out.println("\n==== To-Do List ====");
            System.out.println("1. 할 일 추가");
            System.out.println("2. 전체 목록 보기");
            System.out.println("3. 완료 표시");
            System.out.println("4. 완료된 항목 삭제");
            System.out.println("5. 제목 + 날짜로 삭제");
            System.out.println("6. 종료");
            System.out.print("메뉴 선택: ");

            int choice = InputUtils.readInt(sc); // 변수명 통일

            switch (choice) {
                case 1 -> { // 할 일 추가
                    System.out.print("할 일 제목: ");
                    String title = sc.nextLine().trim();

                    System.out.print("날짜 입력 (예: 2025-10-28): ");
                    LocalDate date = InputUtils.readSimpleDate(sc);

                    repo.add(new Task(title, date));
                    System.out.println("추가되었습니다.");
                }
                case 2 -> {
                    repo.listAll();
                }
                case 3 -> { // 완료 표시
                    System.out.print("완료로 표시할 제목: ");
                    String doneTitle = sc.nextLine().trim();
                    repo.markDoneByTitle(doneTitle);
                }
                case 4 -> {
                    repo.removeCompleted();
                }
                case 5 -> { // 제목+날짜로 삭제
                    System.out.print("삭제할 제목: ");
                    String delTitle = sc.nextLine().trim();

                    System.out.print("삭제할 날짜 (예: 2025-10-28): ");
                    LocalDate date = InputUtils.readSimpleDate(sc);

                    repo.removeByTitleAndDate(delTitle, date); // delDate -> date 로 수정
                }
                case 6 -> {
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
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
                return DateUtil.parseFlexible(input);
            } catch (Exception e) {
                System.out.println("⚠ " + e.getMessage());
            }
        }
    }
}*/