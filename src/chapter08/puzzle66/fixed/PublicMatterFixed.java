package chapter08.puzzle66.fixed;

class Base {
    public String getClassName() {
        return "Base";
    }
}

class Derived extends Base {

    public String getClassName() {
        return "Derived";
    }
}


public class PublicMatterFixed {
    public static void main(String[] args) {
        System.out.println(new Derived().getClassName());
    }
}
