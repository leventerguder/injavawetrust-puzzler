package chapter07.puzzle60;

import java.util.Arrays;

public class SplitParse {

    public static void main(String[] args) {

        String content = "fear, surprise, ruthless efficiency, an almost fanatical devotion to the Pope, nice red uniforms";

        Arrays.stream(parse(content)).forEach(System.out::println);
    }

    static String[] parse(String string) {
        return string.split(",\\s*");
    }

}
