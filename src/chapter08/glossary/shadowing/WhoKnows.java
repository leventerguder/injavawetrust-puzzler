package chapter08.glossary.shadowing;

class WhoKnows {
    static String sentence = "I don't know.";

    public static void main(String[] args) {
        String sentence = "I know!";   // shadows static field
        System.out.println(sentence);  // prints local variable
    }
}
