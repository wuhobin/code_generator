package com.code.generator.service.function;

/**
 *
 * @author aurora
 * @className ThrowExceptionFunction
 * @date 2024/3/29
 * @description TODO
 */
@FunctionalInterface
public interface ThrowExceptionFunction {

    /**
     * 如果为true时 抛出异常
     * @param message
     */
    void throwException(String message);

}
