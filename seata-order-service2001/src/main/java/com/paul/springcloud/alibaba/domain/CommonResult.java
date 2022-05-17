package com.paul.springcloud.alibaba.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Desc: // TODO Please describe the class information
 * @Date: 2021-12-06 22:06
 * @Author: paul
 */
@Data
@NoArgsConstructor // 无参构造函数
@AllArgsConstructor // 全参构造函数
public class CommonResult<T> {

    private Integer code;
    private String  message;
    private T       data;

    public CommonResult(Integer code, String message) {
        this(code,message,null);
    }

}
