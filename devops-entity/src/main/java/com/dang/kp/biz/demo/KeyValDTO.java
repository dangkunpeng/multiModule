package com.dang.kp.biz.demo;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class KeyValDTO {
    @NotBlank(message = "key 不能为空")
    private String key;
    @NotBlank(message = "val 不能为空")
    private String val;
    @NotNull(message = "indexInLine 不能为空")
    private Integer indexInLine;
    @NotBlank(message = "parentKey 不能为空")
    private String parentKey;
}
