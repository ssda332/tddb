package lotto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

public class LottoGeneratorTest {
    @Test
    void 고유한_번호_생성() {
        /**
         * 1-1
         * 3장의 테스트 코드 작성 순서에 따라, 쉬운 경우에서 어려운 경우로 진행하면서
         * 로또 번호 생성기를 구현해보기로 함.
         *
         * 먼저, 쉬운 경우에서 구현하기 쉬운 테스트부터 시작하기로 했고,
         * 2번째 조건인 1부터 45 사이의 숫자를 반환하는 테스트를 만들어보기로 했다.
         *
         */

        LottoGenerator generator = new LottoGenerator();
        int number = generator.getNumber();
        /**
        assertThat(number).isEqualTo(1);
         이와같이 예를 추가하고 그 다음 구현을 아래와 같이 일반화함.
        */
        assertThat(number).isBetween(1, 45);



    }

}
