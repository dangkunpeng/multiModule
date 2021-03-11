package com.dang.kp.common.result;

import java.util.Objects;

public class MyResult {

    public static final Integer CODE_SUCCESS = 200;
    public static final Integer CODE_FAIL = 500;
    public static final Integer CODE_VALID_FAIL = 300;

    /**
     * 包装:成功的操作结果
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        return Result.builder().code(CODE_SUCCESS).msg("success").data(data).build();
    }


    /**
     * 判断:true=成功,否则=失败
     *
     * @param result 操作结果
     * @return
     */
    public static Boolean success(Result result) {
        return Objects.nonNull(result) && Objects.nonNull(result.getCode()) && result.getCode() == CODE_SUCCESS;
    }

    /**
     * 包装:失败的消息
     *
     * @param msg
     * @return
     */
    public static Result fail(String msg) {
        return Result.builder().code(CODE_FAIL).msg(msg).build();
    }

    /**
     * 包装:失败的消息和数据
     *
     * @param msg
     * @param data
     * @return
     */
    public static Result fail(String msg, Object data) {
        return Result.builder().code(CODE_FAIL).msg(msg).data(data).build();
    }

    public static Result validFail(Object data) {
        return Result.builder().code(CODE_VALID_FAIL).msg("validFail").data(data).build();
    }
}
