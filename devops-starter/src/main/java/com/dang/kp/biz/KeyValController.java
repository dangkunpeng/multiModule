package com.dang.kp.biz;

import com.dang.kp.biz.demo.KeyVal;
import com.dang.kp.biz.demo.KeyValDTO;
import com.dang.kp.common.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(value = "key val 键值对业务操作")
@RestController
@RequestMapping(value = "/keyVal", method = RequestMethod.POST)
public class KeyValController {

    @Resource
    private KeyValService keyValService;

    @ApiOperation(value = "查询所有")
    @RequestMapping(value = "/getAll")
    public Result getAll() {
        return this.keyValService.getAll();
    }

    @ApiOperation(value = "按父键查询所有")
    @RequestMapping(value = "/getAllByParentKey")
    public Result getAllByParentKey(@RequestBody @Validated KeyValDTO keyValDTO) {
        return this.keyValService.getAllByParentKey(convert(keyValDTO));
    }

    @ApiOperation(value = "按父键查询所有后排序")
    @RequestMapping(value = "/getAllByParentKeyOrderByIndex")
    public Result getAllByParentKeyOrderByIndex(@RequestBody @Validated KeyValDTO keyValDTO) {
        return this.keyValService.getAllByParentKeyOrderByIndex(convert(keyValDTO));
    }

    private static KeyVal convert(KeyValDTO keyValDTO) {
        return KeyVal.builder().parentKey(keyValDTO.getParentKey()).build();
    }
}
