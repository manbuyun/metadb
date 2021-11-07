package io.metadb.exception;

/**
 * @author jinhai
 * @date 2021/11/07
 */
public interface ErrorCodeSupplier {
    ErrorCode toErrorCode();
}
