# Puzzle 60: One-Liners

Now it's your turn to write some code. Each of the following puzzles can be solved with a method whose body contains 
but a single line. On your mark, get set, code!

A. Write a method that takes a List of elements and returns a new List containing the same elements in the 
same order with the second and subsequent occurrences of any duplicate elements removed. For example, 
if you pass in a list containing "spam", "sausage", "spam", "spam", "bacon", "spam", "tomato", and "spam", 
you'll get back a new list containing "spam", "sausage", "bacon", and "tomato".

B. Write a method that takes a string containing zero or more tokens separated by commas and returns an array of 
strings representing the tokens in the order they occur in the input string. 
Each comma may be followed by zero or more white space characters, which must be ignored by the method. 
For example, if you pass the string "fear, surprise, ruthless efficiency, an almost fanatical devotion to the Pope, 
nice red uniforms", you'll get back a five-element string array containing "fear", "surprise", "ruthless efficiency", 
"an almost fanatical devotion to the Pope", and "nice red uniforms".

C. Suppose that you have a multidimensional array that you want to print for debugging purposes. 
You don't know how many levels the array has or what type of objects are stored at each level in the array. 
Write a method that shows you all the elements at each level.

D. Write a method that takes two int values and returns true if the first value has more bits set 
than the second in its two's-complement binary representation.

A -
Luckily, there is a Set implementation that maintains its elements in insertion order, and it offers 
near-HashMap performance to boot. It's called LinkedHashSet
<pre>
  static <E> List<E> withoutDuplicates(List<E> original) {
           return new ArrayList<E>(new LinkedHashSet<E>(original));
}
</pre>

If you tried to solve this puzzle with StringTokenizer, you quickly realized that it isn't a very good fit. 
With regular expressions, it's a snap. To solve this puzzle in one line, use the convenience method String.split, 
which takes a regular expression describing the token delimiter.

<pre>
static String[] parse(String string) {
           return string.split(",\\s*");
}
</pre>


This is a trick question. You don't even have to write a method. 
The method is provided for you in release 5.0 and later releases, and is called Arrays.deepToString.

C -
This is a trick question. You don't even have to write a method. 
The method is provided for you in release 5.0 and later releases, and is called Arrays.deepToString. 


D -
In this case, what you need is Integer.bitCount, which returns the number of set bits in an int value:

<pre>
static boolean hasMoreBitsSet(int i, int j) {
           return (Integer.bitCount(i) > Integer.bitCount(j));
}

</pre>


Knowing what's in the libraries can save you lots of time and effort and 
can enhance the speed and quality of your programs
