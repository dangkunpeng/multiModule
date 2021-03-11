package com.dang.kp.utils;

import com.dang.kp.biz.KeyValRepo;
import com.dang.kp.biz.demo.KeyVal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Slf4j
@Component
public class StartUpCommand implements CommandLineRunner {
    @Autowired
    private KeyValRepo keyValRepo;

    @Override
    public void run(String... args) throws Exception {
        init();
    }

    /**
     * 更新发布日期
     *
     * @return
     */
    private void init() {
        log.info("init");
        List<KeyVal> list = this.keyValRepo.findAll();
        if (CollectionUtils.isEmpty(list)) {
            for (int i = 1; i < 1000; i++) {
                String parentKey = MyUtils.getKey("pk");
                for (int j = 1; j < i % 9; j++) {
                    list.add(KeyVal.builder()
                            .key(MyUtils.getKey("k"))
                            .parentKey(parentKey)
                            .val(MyUtils.getKey("v"))
                            .indexInLine(j)
                            .build());
                }
            }
            this.keyValRepo.saveAll(list);
        }
        log.info("有 {} 条记录", list.size());
    }
}
