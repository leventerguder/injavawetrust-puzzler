package chapter10.puzzle95;

public class ApplePieFixed {
    public static void main(String[] args) {
        int count = 0;
        for (int i = 0; i < 100; i++)
        //removed semicolon
        {
            count++;
        }
        System.out.println(count);
    }

}
