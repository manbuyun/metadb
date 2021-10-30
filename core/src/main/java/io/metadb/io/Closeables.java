package io.metadb.io;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author jinhai
 * @date 2021/10/30
 */
public class Closeables {

    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException impossible) {
                throw new AssertionError(impossible);
            }
        }
    }
}
