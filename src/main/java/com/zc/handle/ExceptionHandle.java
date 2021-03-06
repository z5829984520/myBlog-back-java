package com.zc.handle;

import com.zc.utils.CommonLogger;
import com.zc.utils.CommonResult;
import com.zc.utils.CommonException;
import com.zc.enums.CommonEnums;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
* 异常捕获
* */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public CommonResult allExceptionHandle(Exception e) {
        if(e instanceof CommonException) {
            CommonException commonException = (CommonException) e;

            return CommonResult.error(commonException.getCode(), commonException.getMessage());
        } else {
            // logger.info("【系统异常】:{}", e.toString());
            CommonLogger.error("【系统异常】:" + e.toString());

            return CommonResult.error(CommonEnums.ERROR.getCode(), e.toString());
        }
    }
}
