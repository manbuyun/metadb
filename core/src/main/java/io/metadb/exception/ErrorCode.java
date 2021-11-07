package io.metadb.exception;

import com.google.common.base.Preconditions;

import java.util.Objects;

/**
 * @author jinhai
 * @date 2021/11/07
 */
public class ErrorCode {
    private int code;
    private String name;
    private ErrorType type;

    public ErrorCode(int code, String name, ErrorType type) {
        Preconditions.checkArgument(code < 0, "Code is negative");
        this.code = code;
        this.name = name;
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ErrorCode that = (ErrorCode) obj;
        return Objects.equals(code, that.code);
    }

    @Override
    public String toString() {
        return name + ":" + code;
    }
}
