# Puzzle 82: Beer Blast

What does this program print if you run it with the single command line argument slave? 
What does it print if you run it with no command line arguments?

<pre>
public class BeerBlast {

    static final String COMMAND = "java BeerBlast slave";

    public static void main(String[] args) throws Exception {
        if (args.length == 1 && args[0].equals("slave")) {
            for (int i = 99; i > 0; i--) {
                System.out.println(i + " bottles of beer on the wall");
                System.out.println(i + " bottles of beer");
                System.out.println("You take one down, pass it around,");
                System.out.println((i - 1) + " bottles of beer on the wall");
                System.out.println();
            }
        } else {
            Process process = Runtime.getRuntime().exec(COMMAND);
            int exitValue = process.waitFor();
            System.out.println("exit value = " + exitValue);
        }
    }
}
</pre>


If you run it with no command line argument, it starts a slave process that prints the ditty, 
but you won't see the output of the slave process.

The main process waits for the slave process to finish and then prints the exit value of the slave. 
By convention, the value 0 indicates normal termination, so that is what you might expect the program to print. 
If you ran it, you probably found that it just hung there, printing nothing at all. 


The clue to this mystery is in the documentation for the Process class, which says: 
"Because some native platforms only provide limited buffer size, failure to promptly read the output stream of the 
subprocess may cause the subprocess to block, and even deadlock" [Java-API]. 
That is exactly what's happening here: There is insufficient space in the buffer to hold the interminable ditty. 
To ensure that the slave process terminates, the parent must drain its output stream, which is an input stream 
from the perspective of the master. The following utility method performs this task in a background thread:

<pre>
static void drainInBackground(final InputStream is) {
    new Thread(new Runnable() {
        public void run() {
            try {
                while(is.read() >= 0) ;
            } catch (IOException e) {
                // return on IOException
            }
        }
    }).start();
}

</pre>

If we modify the program to invoke this method prior to waiting for the slave process, the program prints 0 as expected:

<pre>
    Process process = Runtime.getRuntime().exec(COMMAND);
    drainInBackground(process.getInputStream());
    int exitValue = process.waitFor();
    System.out.println(exitValue);
</pre>


The lesson is that you must drain the output stream of a child process in order to ensure its termination; 
the same goes for the error stream, which can be even more troublesome because you can't predict when a process 
will dump lots of output to it
