package problem2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class WordConverterTest {

    /**
     * 단어 변환기에서 가장 쉽게 테스트를 통과할수 있는 방법은
     * 아무것도 변환할 필요가 없을 때라고 생각했다.
     * 단어를 받았을 때 아무것도 변환할 필요가 없으면 있는 그대로 리턴해줘도 되기 때문이다.
     *
     * 4장 내용 적용
     * 단어 변환기의 요구사항에서
     * 1. 대문자 변환과 2. 특정 문자 제거 부분에서 구체화할 부분이 있다
     * 특정 문자는 대문자가 올 수도 있고, 소문자가 올 수도 있다
     * 대소문자가 아닌경우(ex. 숫자) 걸리지 않지만 예를들어
     * 특정 문자를 제거하는 시점이 소문자를 대문자로 변환하기 전인 시점인지? 아니면 후의 시점인지?
     * 예를들어 "aAb" 이고 'A'를 제거한다고 했을 떄
     * 전자일 경우 "AB"를 반환할 것이고, 후자일 경우는 "B"를 반환할 것이다.
     * 아니면 애초에 특정 문자를 대문자를 받지 않거나, 변환기에서 받는 문자열은 대문자를 포함하지 않아야 한다라는 요구사항이 추가될수도 있을것 같다.
     *
     * 해당 문제에서는 "소문자가 들어왔을땐 변환 전부터 제거하고 대문자가 들어왔을땐 변환 후 제거" 로 요구사항을 구체화하고 각 예외상황에 대해
     * 테스트 코드를 작성할 것이다.
     * 실제 프로젝트에서는 4장의 기능 명세 구체화와 같이 기획자와의 조율이 필요할 것 같다.
     */

    private WordConverter wc;

    @BeforeEach
    void setUp() {
        wc = new WordConverter();
    }

    @Test
    @DisplayName("아무것도 변경되지 않았을때")
    void nothingIsChanged() {
        /**
         * 1. 변환기 클래스인 WordConverter를 만들어주었고,
         * 2. 인자로 들어갈 단어는 반드시 조건에 걸리지 않는 단어여야 한다.
         * 3. 조건 2번인 특정 문자 제거는 현재 구현하지 않지만 모든 조건 케이스에 걸리지 않는것을 명시하기 위해 특정 문자 매개변수를 메소드에 추가한다.
         * 4. 테스트를 통과시킨다! 아무것도 바뀐게 없으므로 값은 당연히 같아야한다.
         */

        //WordConverter wc = new WordConverter();
        String str1 = "CHANGE";
        String convertStr1 = wc.convert(str1, "z");

        assertThat(convertStr1).isEqualTo(str1); // 값이 같음

        /**
         * 2번째 케이스 추가하고 바로 구현 일반화 해주자
         */

        String str2 = "HANGHAEPLUS";
        String convertStr2 = wc.convert(str2, "Y");

        assertThat(convertStr2).isEqualTo(str2); // 값이 같음
    }

    /**
     * 이제 가능한 쉬운 순서대로 조건을 맞춰주면 될 것 같다.
     * 길이 제한 조건은 특정 문자 제거 후 체크해야할 사항이니 후순위로 미룬다.
     * 특정 문자 제거도 대문자 변환 후 제거하기로 했으니 다음 후순위로 미룬다.
     * 그럼 가장 먼저 해야할 사항은 대문자 변환이다.
     */

    @Test
    @DisplayName("대문자 변환")
    void convertUpperCase() {
        //WordConverter wc = new WordConverter();

        String str = "hanghaeplus1[+]";
        String convertStr = wc.convert(str, "Y");

        assertThat(convertStr).isEqualTo("HANGHAEPLUS1[+]"); // 값이 같음

    }

    /**
     * 예외 상황에 대한 체크도 해준다.
     * null값이 들어올 경우 Exception
     */
    @Test
    @DisplayName("null 값이 들어올 경우 예외 발생")
    void nullInputThrowsException() {
        //WordConverter wc = new WordConverter();

        assertThatThrownBy(() -> wc.convert(null, "Y"))
                .isInstanceOf(InvalidWordException.class)
                .hasMessage("유효하지 않은 값입니다.");
    }

    /**
     * 다음은 특정 문자 제거 처리를 해야한다.
     * 문자 제거 로직을 안짰으니 당연히 테스트코드는 통과하지 않을것이고
     * 위에서 예외상황을 다 예측하고 요구사항 구체화를 해놓았지만
     * 일단 가장 간단하게 생각해서 소문자 - 소문자일 경우로 테스트 코드를 짜보자
     */

    @Test
    @DisplayName("특정 문자 제거 - 소문자, 소문자")
    void removeSpecificCharacter_lower_lower() {
        String str = "abcdefg";
        String convertStr = wc.convert(str, "a");

        assertThat(convertStr).isEqualTo("BCDEFG");
    }

    /**
     * 그리고 가장 쉽게 생각할 수 있는 예외 처리 해주자
     * 일단은 문자열 길이가 1이 아닐 경우를 예외 처리 해야한다.(null 경우도)
     */
    @Test
    @DisplayName("특정 문자 제거 - 문자 길이 1 아닐경우, null일경우")
    void charLengthIsNot_1() {
        assertThatThrownBy(() -> wc.convert("null", ""))
                .isInstanceOf(InvalidCharacterException.class)
                .hasMessage("제거할 문자를 다시 입력해주세요.");

        assertThatThrownBy(() -> wc.convert("null", "123a"))
                .isInstanceOf(InvalidCharacterException.class)
                .hasMessage("제거할 문자를 다시 입력해주세요.");

        assertThatThrownBy(() -> wc.convert("null", null))
                .isInstanceOf(InvalidCharacterException.class)
                .hasMessage("제거할 문자를 다시 입력해주세요.");
    }

    /**
     * 위에서 구체화했던 대소문자별로 따로 예외처리해야한다.
     * 특정 문자가 소문자면 특정 문자 제거후 대문자 변환
     * 특정 문자가 대문자면 대문자 변환 후 특정 문자 제거
     *
     * 이미 위에서 가장 쉬운 방법으로 소문자일 경우를 구현해놨으니 대문자로 뻗어가면서 구현하면 될 것 같다.
     */

    @Test
    @DisplayName("특정 문자 제거 - 대문자일 경우")
    void removeIfCharUpperCase() {
        String str = "abcdefg";
        String convertStr = wc.convert(str, "A");

        assertThat(convertStr).isEqualTo("BCDEFG");

        str = "Abcdefg";
        convertStr = wc.convert(str, "A");

        assertThat(convertStr).isEqualTo("BCDEFG");
    }

    @Test
    @DisplayName("특정 문자 제거 - 알파벳 이외")
    void removeIfCharNotAlpha() {
        String str = "abc123";
        String convertStr = wc.convert(str, "1");

        assertThat(convertStr).isEqualTo("ABC23");

        str = "123+1+";
        convertStr = wc.convert(str, "+");

        assertThat(convertStr).isEqualTo("1231");

    }

    /**
     * 변환에 대한 예외처리는 다 끝났으니 마지막으로 변환된 문자열 길이 제한 조건(최대 50자)만 맞으면 될 것 같다.
     */

    @Test
    @DisplayName("변환된 문자열 길이 제한 (최대 50자)")
    void convertedStringLengthLimit() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 51; i++) {
            sb.append("a");
        }
        String longStr = sb.toString();

        assertThatThrownBy(() -> wc.convert(longStr, "b"))
                .isInstanceOf(WordLengthException.class)
                .hasMessage("변환된 문자열의 길이가 너무 깁니다.");

        sb.setLength(0);
        for (int i = 0; i < 50; i++) {
            sb.append("a");
        }
        String validStr = sb.toString();
        String convertedStr = wc.convert(validStr, "b");

        assertThat(convertedStr.length()).isEqualTo(50); // 변환된 알파벳 개수 50개
    }
}
