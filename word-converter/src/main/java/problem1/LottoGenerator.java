package problem1;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {

    private Random random = new Random();

    public int genUniqNumber(Set<Integer> lottoNumbers) {
        //return 1;
        //return random.nextInt(45) + 1;
        /**
         * 거의 일어나지 않는 경우지만 모든 숫자가 들어있을 예외 케이스 작성
         */
        if (lottoNumbers.size() >= 45) {
            throw new IllegalStateException("모든 숫자가 들어있습니다");
        }


        /**
         * 처음엔 1부터 45까지의 숫자만 반환하는 테스트를 했고
         * 그 다음 고유 번호 로직을 추가함
         */

        int number;
        do {
            number = random.nextInt(45) + 1;
        } while (lottoNumbers.contains(number));
        return number;
    }

    public Set<Integer> genSixUniqNumbers() {
        Set<Integer> lottoNumbers = new HashSet<>();

        /**
         * 6개의 번호를 ArrayList에 담아 리턴해서 작은 구현부터 하려했지만
         * 그냥 한꺼번에 Set을 이용해서 구현을 함
         */
        while (lottoNumbers.size() < 6) {
            int number = genUniqNumber(lottoNumbers);
            lottoNumbers.add(number);
        }

        return lottoNumbers;
    }
}
