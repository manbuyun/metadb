package io.metadb.util;

/**
 * @author jinhai
 * @date 2021/10/30
 */
public class StringBuilders {
    // 2k
    private static final int DISCARD_LIMIT = 1024 << 1;
    private static final ThreadLocal<StringBuilder> threadLocal = ThreadLocal.withInitial(() -> new StringBuilder(256));

    private StringBuilders() {
    }

    public static StringBuilder get() {
        StringBuilder buf = threadLocal.get();

        if (buf.capacity() > DISCARD_LIMIT) {
            buf.setLength(1024);
            buf.trimToSize();
        }
        buf.setLength(0);
        return buf;
    }
}
