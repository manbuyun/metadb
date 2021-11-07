package io.metadb.exception;

import static io.metadb.exception.ErrorType.USER_ERROR;

/**
 * @author jinhai
 * @date 2021/11/07
 */
public enum StandardErrorCode implements ErrorCodeSupplier {
    GENERIC_USER_ERROR(0, USER_ERROR),
    SYNTAX_ERROR(1, USER_ERROR),
    USER_CANCELED(3, USER_ERROR),
    FUNCTION_NOT_FOUND(6, USER_ERROR),
    ;

    private ErrorCode errorCode;

    StandardErrorCode(int code, ErrorType type) {
        this.errorCode = new ErrorCode(code, name(), type);
    }

    @Override
    public ErrorCode toErrorCode() {
        return null;
    }
}
