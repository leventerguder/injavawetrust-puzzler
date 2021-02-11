package chapter05.puzzle41;

import java.io.*;

public class FieldStreamFixed3 {

    static void copy(String src, String dest) throws IOException {

        /*
        https://www.baeldung.com/java-try-with-resources
        https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
         */
        try (InputStream in = new FileInputStream(src);
             OutputStream out = new FileOutputStream(dest);
        ) {
            byte[] buf = new byte[1024];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        }

    }

}
