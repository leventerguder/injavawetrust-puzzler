# Puzzle 23: No Pain, No Gain

This program prints a word, using a random number generator to select the first character. 
Describe the behavior of the program:


At first glance, this program might appear to print out the words Pain, Gain, and Main with equal likelihood, 
varying from run to run. It appears to choose the first letter of the word, depending on the value chosen 
by the random number generator: M for 0, P for 1, and G for 2. The puzzle's title might have provided 
you with a clue that it doesn't actually print Pain or Gain. Perhaps more surprisingly, it doesn't print Main either, 
and its behavior doesn't vary from run to run. It always prints ain.

Three bugs conspire to cause this behavior. Did you spot them all? The first bug is that the random number 
is chosen so the switch statement can reach only two of its three cases. 
The specification for Random.nextInt(int) says: "Returns a pseudorandom, uniformly distributed int value between 0 
(inclusive) and the specified value (exclusive)" [Java-API]. 
This means that the only possible values of the expression rnd.nextInt(2) are 0 and 1. 
The switch statement will never branch to case 2, which suggests that the program will never print Gain. 
The parameter to nextInt should have been 3 rather than 2.

This is a fairly common source of problems, known as a fencepost error. 
The name comes from the common but incorrect answer of 10 to the question, If you build a fence 100 feet 
long with posts 10 feet apart, how many posts do you need? Both 11 and 9 are correct answers, 
depending on whether there are posts at the ends of the fence, but 10 is wrong. Watch out for fencepost errors. 
Whenever you are working with lengths, ranges, or moduli, be careful to determine which endpoints should be included, 
and make sure that your code behaves accordingly.


The second bug is that there are no break statements between the cases. 
Whatever the value of the switch expression, the program will execute that case and all subsequent cases [JLS 14.11]. 
Each case assigns a value to the variable word, and the last assignment wins. 
The last assignment will always be the one in the final case (default), which is new StringBuffer('M'). 
This suggests that the program will never print Pain or Gain but always Main.

The last and most subtle bug is that the expression new StringBuffer('M') probably does not do what you think it does. 
You may not be familiar with theStringBuffer(char)constructor, and with good reason: It does not exist. 
There is a parameterless constructor, one that takes a String indicating the initial contents of the string buffer 
sand one that takes an int indicating its initial capacity.

In this case, the compiler selects the int constructor, applying a widening primitive conversion to convert the char 
value 'M' into the int value 77 [JLS 5.1.2].


To avoid this kind of problem, use familiar idioms and APIs whenever possible. 
If you must use unfamiliar APIs, read the documentation carefully. In this case, the program should have used 
the common StringBuffer constructor that takes a String.

To summarize: First, be careful of fencepost errors. 
Second, remember to put a break after each case in switch statements. 
Third, use common idioms and APIs, and consult the documentation when you stray from the well-worn path. 
Fourth, a char is not a String but is more like an int. Finally, watch out for sneaky puzzlers.
