# Puzzle 38: The Unwelcome Guest

Java doesn't allow static initializers to throw checked exceptions,
the initialization must be wrapped in a try-finally block. What does the program print?


<pre>
public class UnwelcomeGuest {
	public static final long GUEST_USER_ID = -1;
	private static final long USER_ID;
	static {
		try {
			USER_ID = getUserIdFromEnvironment();
		} catch (IdUnavailableException e) {
			USER_ID = GUEST_USER_ID;
			System.out.println("Logging in as guest");
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
</pre>

If you tried to compile it, you saw an error message that looked something like this:

UnwelcomeGuest.java:10:
variable USER_ID might already have been assigned
USER_ID = GUEST_USER_ID;


What's the problem? The USER_ID field is a blank final, which is a final field 
whose declaration lacks an initializer [JLS 4.12.4]. 
It is clear that the exception can be thrown in the try block only if the assignment to USER_ID fails, 
so it is perfectly safe to assign to USER_ID in the catch block. Any execution of the static initializer 
block will cause exactly one assignment to USER_ID, which is just what is required for blank finals. 
Why doesn't the compiler know this?

Determining whether a program can perform more than one assignment to a blank final is a hard problem. 
In fact, it's impossible.


The best way to solve this kind of problem is to turn the offending field from a blank final into an ordinary final, 
replacing the static initializer block with a static field initializer. 
This is best done by refactoring the code in the static block into a helper method:


<pre>
public class UnwelcomeGuestFixed {

    public static final long GUEST_USER_ID = -1;
    private static final long USER_ID = getUserIdOrGuest();

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

</pre>



This version of the program is clearly correct and is more readable than the original because it adds 
a descriptive name for the field value computation, where the original version had only an anonymous 
static initializer block. With this change to the program, it works as expected.


In summary, most programmers do not need to learn the details of the definite assignment rules. 
Usually the rules just do the right thing. If you must refactor a program to eliminate a compilation 
error caused by the definite assignment rules, consider adding a new method.
Besides solving the definite assignment problem, it may offer an opportunity to make the program more readable.
