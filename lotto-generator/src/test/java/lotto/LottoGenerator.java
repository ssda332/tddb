package lotto;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LottoGenerator {

    private Random random = new Random();

    public int getNumber() {
        //return 1;
        return random.nextInt(45) + 1;
    }
}
