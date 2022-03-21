package com.nyf.ExceptionHandler;


import com.nyf.ExceptionHandler.exception.MyException;
import com.nyf.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody//为了返回数据
    public R error(Exception e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }

    //特殊异常处理
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public R error(ArithmeticException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message("执行了特殊异常");
    }

    //自定义异常处理
    @ExceptionHandler(MyException.class)
    @ResponseBody
    public R error(MyException e){
        log.error(e.getMessage());
        e.printStackTrace();
        return R.error().message(e.getMsg()).code(e.getCode());
    }
}
