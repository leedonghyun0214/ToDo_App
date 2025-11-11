package todo.util;



import java.util.Scanner;
import java.time.LocalDate;

public class InputUtils {
    Scanner sc = new Scanner(System.in);

    /** 안전한 정수 입력 */
    public static int readInt(Scanner sc) {
        while (true) {
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("숫자를 입력하세요: ");
            }
        }
    }

    /** 간단한 날짜 입력 (yyyy-MM-dd 형식만 지원) */
    public static LocalDate readSimpleDate(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return LocalDate.parse(input);
            } catch (Exception e) {
                System.out.print("⚠ 날짜 형식이 잘못되었습니다. 예: 2025-10-28 → ");
            }
        }
    }
}

