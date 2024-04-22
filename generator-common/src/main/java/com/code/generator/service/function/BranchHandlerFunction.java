package com.code.generator.service.function;

/**
 * packageName com.ai.chat.service.function
 *
 * @author aurora
 * @className BranchHandlerFunction
 * @date 2024/3/29
 * @description TODO
 */
@FunctionalInterface
public interface BranchHandlerFunction {

    /**
     * 结果为true 执行 trueHandler 否则执行falseHandler
     * @param trueHandler
     * @param falseHandler
     */
    void handler(Runnable trueHandler, Runnable falseHandler);

}
