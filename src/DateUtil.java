import java.time.LocalDate; //시,분초 없이 날짜만 가져오는 클래스
import java.time.format.DateTimeFormatter; // 문자열->날짜로 바꾸는 포멧터(포멧들을 담는 객체) 클래스 -> 원래 알고리즘에서 변환 하던걸 자바 자체에서 클래스화
import java.time.format.DateTimeParseException; // 위 과정에서 나오는 예외 타입 클래스
import java.util.List;

// 여러가지 입력(예외 상황)에 대한 솔루션 클래스
public class DateUtil { // 사용 안함

    private static final List<DateTimeFormatter> FORMATTERS = List.of( // 포멧터들의 형식을 지정한 '불변' 클래스
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),  //포멧(규칙)1
            DateTimeFormatter.ofPattern("yyyy/MM/dd"), //포멧2
            DateTimeFormatter.ofPattern("yyyy.MM.dd") //포멧3
    );

    public static LocalDate parseFlexible(String input) {
        if (input == null || input.isBlank()) return LocalDate.now(); // 기본값: 오늘 -> 값의 입력이 없을 경우 오늘로 세팅됨.

        String normalized = input.trim()
                .replace('/', '-')
                .replace('.', '-'); // 모든 구분자를 통일

        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(normalized, formatter);
            } catch (DateTimeParseException ignored) {}
        }

        throw new IllegalArgumentException("유효하지 않은 날짜 형식입니다. (예: 2025-10-28)");
    }
}

