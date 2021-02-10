package chapter05.puzzle38;

public class UnwelcomeGuestFixed {

    /*

    This version of the program is clearly correct and is more readable than the original because
    it adds a descriptive name for the field value computation, where the original version
    had only an anonymous static initializer block. With this change to the program, it works as expected.

     */

    public static final long GUEST_USER_ID = -1;
    private static final long USER_ID = getUserIdOrGuest();

    /*
     This is best done by refactoring the code in the static block into a helper method:s
     */
    private static long getUserIdOrGuest() {

        try {
            return getUserIdFromEnvironment();
        } catch (IdUnavailableException e) {

            System.out.println("Logging in as guest");
            return GUEST_USER_ID;
        }
    }

    private static long getUserIdFromEnvironment() throws IdUnavailableException {
        throw new IdUnavailableException(); // Simulate an error
    }

    public static void main(String[] args) {
        System.out.println("User ID: " + USER_ID);
    }
}

class IdUnavailableException extends Exception {

}


