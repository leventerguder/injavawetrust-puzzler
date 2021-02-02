package chapter03.puzzler12;


// Broken - invokes the wrong overloading of println!
class Abc2 {
    public static void main(String[] args) {
        String letters = "ABC";
        Object numbers = new char[]{'1', '2', '3'};
        System.out.print(letters + " easy as ");
        System.out.println(numbers); // Invokes println(Object)
    }
}
