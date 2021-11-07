package io.metadb.exception;

/**
 * @author jinhai
 * @date 2021/11/07
 */
public class MetaDBException extends RuntimeException {
    private ErrorCode errorCode;

    public MetaDBException(ErrorCodeSupplier supplier, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = supplier.toErrorCode();
    }
}
