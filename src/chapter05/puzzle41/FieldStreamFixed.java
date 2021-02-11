package chapter05.puzzle41;

import java.io.*;

public class FieldStreamFixed {

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

            /*
            The solution is to wrap each call to close in a nested try block.
            The following version of the finally block is guaranteed to invoke close on both streams:
             */

            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    // There is nothing we can do if close fails
                }
            }


            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    // Again, there is nothing we can do if close fails
                }
            }
        }

    }

}
