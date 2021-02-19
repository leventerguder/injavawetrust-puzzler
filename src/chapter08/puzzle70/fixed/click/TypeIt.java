package chapter08.puzzle70.fixed.click;

import chapter08.puzzle70.fixed.hack.CodeTalk;

public class TypeIt {

    private static class ClickIt extends CodeTalk {

        @Override
        public void printMessage() {
            System.out.println("Hack");
        }
    }

    public static void main(String[] args) {
        ClickIt clickit = new ClickIt();
        clickit.doIt();
    }

}
