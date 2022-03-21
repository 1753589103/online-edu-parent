package com.nyf.ExceptionHandler.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor//全参构造
@NoArgsConstructor//无参构造
public class MyException extends RuntimeException{
    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;
}
