package com.paul.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author paul
 * @Description
 * @create 2021-11-06 21:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {

    private Integer code;

    private String  message;

    private T       data;

    public CommonResult(Integer code, String message) {
        this(code, message, null);
    }

}
