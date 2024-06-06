package problem2;

public class WordConverter {


    public String convert(String str, String word) {
        /**
         * 먼저 아무것도 변경되지 않았을때 그대로 반환해주기 위해 CHANGE 리턴
         * 그 다음 구현 일반화
         */
        // return "CHANGE";
        //return str;

        /**
         * 대문자 변환 만들어준다.
         */

        //return str.toUpperCase();

        /**
         * 예외처리
         */
/*

        if (str == null) {
            throw new InvalidWordException("유효하지 않은 값입니다.");
        }
*/

        //return str.toUpperCase();

        /**
         * 예외처리(소문자-소문자)
         * 아주 간단하게 생각해서 짜보자
         * 1. 먼저 받은 char 인자값을 바로 빼준후
         * 2. uppercase처리하면 쉽게 구현될 것 같다.
         */
        /*str = str.replace(word, "");

        return str.toUpperCase();*/

        /**
         * 예외처리(word null값, length != 1값
         */
/*

        if (word == null) throw new InvalidCharacterException("제거할 문자를 다시 입력해주세요.");
        if (word.length() != 1) throw new InvalidCharacterException("제거할 문자를 다시 입력해주세요.");
*/

        /*str = str.replace(word, "");

        return str.toUpperCase();*/

        /**
         * 대소문자 케이스를 막상 나눠서 구현하려고 보니
         * 그냥 전과 후 시점에서 계속 삭제해주면 되는것같다.
         * 따라서 다음과 같이 구현했다.
         */
/*
        str = str.replace(word, "");
        str = str.toUpperCase();
        str = str.replace(word, "");

        return str;*/

        /**
         * 이쯤에서 리팩토링 한번 한다.
         */
        /*checkException(str, word);

        str = str.replace(word, "");
        str = str.toUpperCase();
        str = str.replace(word, "");

        return str;*/

        /**
         * 변환된 단어 50자 제한 로직 추가 작성한다.
         */

        checkException(str, word);

        str = str.replace(word, "");
        str = str.toUpperCase();
        str = str.replace(word, "");

        lengthFiftyCheck(str);

        return str;
    }

    void checkException(String str, String word) {
        if (str == null) throw new InvalidWordException("유효하지 않은 값입니다.");
        if (word == null) throw new InvalidCharacterException("제거할 문자를 다시 입력해주세요.");
        if (word.length() != 1) throw new InvalidCharacterException("제거할 문자를 다시 입력해주세요.");
    }

    void lengthFiftyCheck(String str) {
        if (str.length() > 50) throw new WordLengthException("변환된 문자열의 길이가 너무 깁니다.");
    }
}
