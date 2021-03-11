package com.dang.kp.biz;

import com.alibaba.fastjson.JSONObject;
import com.dang.kp.biz.demo.KeyVal;
import com.dang.kp.common.result.MyResult;
import com.dang.kp.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KeyValService {

    @Autowired
    private KeyValRepo keyValRepo;

    public Result getAll() {
        return MyResult.success(this.keyValRepo.findAll());
    }

    public Result getAllByParentKey(KeyVal keyVal) {
        log.info("getAllByParentKey = {}", JSONObject.toJSONString(keyVal, true));
        Example<KeyVal> example = Example.of(keyVal);
        return MyResult.success(this.keyValRepo.findAll(example));
    }

    public Result getAllByParentKeyOrderByIndex(KeyVal keyVal) {
        log.info("getAllByParentKeyOrderByIndex = {}", JSONObject.toJSONString(keyVal, true));
        return MyResult.success(this.keyValRepo.findByParentKeyOrderByIndexInLineAsc(keyVal.getParentKey()));
    }
}
