package com.dang.kp.common.result;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result {

    private Integer code;

    private String msg;

    private Object data;
}
