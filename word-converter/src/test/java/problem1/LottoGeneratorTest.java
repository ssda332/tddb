package problem1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LottoGeneratorTest {
    private LottoGenerator generator;
    private Set<Integer> lottoNumbers;

    @BeforeEach
    void setUp() {
        generator = new LottoGenerator();
        lottoNumbers = new HashSet<>();
    }

    @Test
    void 숫자_생성_1부터_45까지() {
        /**
         * 1-1
         * 3장의 테스트 코드 작성 순서에 따라, 쉬운 경우에서 어려운 경우로 진행하면서
         * 로또 번호 생성기를 구현해보기로 함.
         *
         * 먼저, 쉬운 경우에서 구현하기 쉬운 테스트부터 시작하기로 했고,
         * 2번째 조건인 1부터 45 사이의 숫자를 반환하는 테스트를 만들어보기로 했다.
         *
         */

        int number = generator.genUniqNumber(new HashSet<>());
        /**
        assertThat(number).isEqualTo(1);
         이와같이 예를 추가하고 그 다음 구현을 아래와 같이 일반화함.
        */
        assertThat(number).isBetween(1, 45);
    }

    @Test
    void 고유_번호_생성() {
        /**
         *  이 테스트가 어떻게 보면 "같은 값을 연속으로 넣는 예외"에 대한 처리인 것 같다.
         */
        lottoNumbers.add(1); // 임의의 기존 번호 추가

        int number = generator.genUniqNumber(lottoNumbers);
        assertThat(number).isBetween(1, 45);
        assertThat(lottoNumbers).doesNotContain(number);
    }

    @Test
    void 여섯_개의__번호_생성() {
        /**
         * 쉬운 순서부터 어려운 순서로 가고 있는 듯하다
         * 번호 생성 -> 고유 번호 생성 -> 여섯 개의 고유 번호 생성
         *  1. 메소드 생성
         */
        lottoNumbers = generator.genSixUniqNumbers();

        /**
         * 구현 후 검증
         */
        assertThat(lottoNumbers).hasSize(6);
        assertThat(lottoNumbers).allMatch(number -> number >= 1 && number <= 45);

    }

    @Test
    void 예외_테스트() {
        for (int i = 1; i <= 45; i++) {
            lottoNumbers.add(i);
        }

        assertThatThrownBy(() -> generator.genUniqNumber(lottoNumbers))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모든 숫자가 들어있습니다");
    }

    /**
     * 모든 코드를 작성하고 테스트 코드에 대한 리팩토링만 해주었다
     * @Before
     */
}
