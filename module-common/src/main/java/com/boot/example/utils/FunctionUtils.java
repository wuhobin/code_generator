package com.boot.example.utils;


import com.boot.example.exception.CustomException;
import com.boot.example.service.function.BranchHandlerFunction;
import com.boot.example.service.function.ThrowExceptionFunction;

/**
 * packageName com.ai.chat.utils
 *
 * @author aurora
 * @className FunctionUtils
 * @date 2024/3/29
 * @description TODO
 */
public class FunctionUtils {


    /**
     * 结果为true 抛出异常
     * @param flag
     * @return
     */
    public static ThrowExceptionFunction isTrue(boolean flag){
        return (errorMessage) -> {
            if(flag){
                throw new CustomException(500, errorMessage);
            }
        };
    }

    /**
     * 结果不同时 执行不同的handler
     * @param flag
     * @return
     */
    public static BranchHandlerFunction isTrueOrFalse(boolean flag){
        return (trueHandler, falseHandler) -> {
            if(flag){
                trueHandler.run();
            }else{
                falseHandler.run();
            }
        };
    }


}
