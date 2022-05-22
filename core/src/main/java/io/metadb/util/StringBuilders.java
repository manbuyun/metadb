package io.metadb.util;

/**
 * @author jinhai
 * @date 2021/10/30
 */
public class StringBuilders {
    private static final int DISCARD_LIMIT = 1024;
    private static final ThreadLocal<StringBuilder> threadLocal = ThreadLocal.withInitial(() -> new StringBuilder(32));

    private StringBuilders() {
    }

    public static StringBuilder get() {
        StringBuilder buf = threadLocal.get();

        if (buf.capacity() > DISCARD_LIMIT) {
            buf.setLength(256);
            buf.trimToSize();
        }
        buf.setLength(0);
        return buf;
    }
}
