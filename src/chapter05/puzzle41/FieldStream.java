package chapter05.puzzle41;

import java.io.*;

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

        /*
        The problem is in the finally block itself. The close method can throw an IOException too.
        If this happens when in.close is called, the exception prevents
        out.close from getting called, and the output stream remains open.
         */
    }

}
