package chapter05.puzzle41;

import java.io.*;

public class FieldStreamFixed2 {

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

            // you can refactor the code to take advantage of the Closeable interface:
            closeIgnoringException(in);
            closeIgnoringException(out);
        }

    }

    private static void closeIgnoringException(Closeable c) {
        if (c != null) {
            try {
                c.close();
            } catch (IOException ex) {
                // There is nothing we can do if close fails
            }
        }
    }

}
