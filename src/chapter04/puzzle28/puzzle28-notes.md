# Puzzle 28: Looper

consider this for loop:

<pre>

for (int i = start; i <= start + 1; i++) {
}
</pre>


It looks as though it should run for only two iterations, but it can be made to loop indefinitely by 
taking advantage of the overflow behavior illustrated in Puzzle 26. The following declaration does the trick:

<pre>
int start = Integer.MAX_VALUE - 1;
</pre>

Now it's your turn. What declaration turns this loop into an infinite loop?

<pre>
while (i == i + 1) {
}
</pre>

Looking at the while loop, it really seems as though it ought to terminate immediately.
A number is never equal to itself plus 1, right? Well, what if the number is infinity? 
Java mandates the use of IEEE 754 floating-point arithmetic [IEEE-754], 
which lets you represent infinity as a double or float. As we learned in school, 
infinity plus 1 is still infinity. If i is initialized to infinity before the loop starts, 
the termination test (i == i + 1) evaluates to true, and the loop never terminates

<pre>
double i = 1.0 / 0.0;

double i = Double.POSITIVE_INFINITY;
</pre>

In fact, you don't have to initialize i to infinity to make the loop spin forever. 
Any sufficiently large floating-point value will do; for example:

<pre>
double i = 1.0e40;
</pre>


In summary, it is possible to represent infinity as a double or a float.
Most people find this somewhat surprising the first time they hear of it, perhaps 
because you can't represent infinity by using any of the integral types. 

Second, adding a small floating-point value to a large one will not change the large value. 
This too may be counterintuitive, as it isn't true for the real numbers. 
It is worth remembering that binary floating-point arithmetic is only an approximation to real arithmetic.
