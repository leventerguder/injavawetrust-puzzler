# Puzzle 41: Field and Stream

This method copies one file to another and was designed to close every stream it creates, 
even if it encounters I/O errors. Unfortunately, it doesn't always do this. Why not, and how can you fix it?

<pre>

public class FieldStream {

    static void copy(String src, String dest) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }

}

</pre>


This program seems to have all the bases covered. The stream fields (in and out) are initialized to null and 
set to the new streams as soon as they are created. The finally block closes the stream referred to by each 
field if it is non-null. An error during the copy would cause an IOException, 
but the finally block would still execute before the method returns. What could go wrong?


The problem is in the finally block itself. The close method can throw an IOException too. If this happens when in.
close is called, the exception prevents out.close from getting called, and the output stream remains open.


Note that this program violates the advice of Puzzle 36: The calls to close can cause the finally block to 
complete abruptly. Unfortunately, the compiler doesn't help you find the problem, because close throws the 
same exception type as read and write, and the enclosing method (copy) is declared to propagate it.

The solution is to wrap each call to close in a nested try block. 
The following version of the finally block is guaranteed to invoke close on both streams:


In summary, when you call the close method in a finally block, 
protect it with a nested TRy-catch to prevent propagation of the IOException. 
More generally, handle any checked exception that can be thrown within a finally block rather 
sthan letting it propagate. This is a special case of the lesson in Puzzle 36, 
and the same lessons for language designers apply.
