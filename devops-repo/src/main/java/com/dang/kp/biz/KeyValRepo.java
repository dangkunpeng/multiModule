package com.dang.kp.biz;

import com.dang.kp.biz.demo.KeyVal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KeyValRepo extends JpaRepository<KeyVal, String> {

    List<KeyVal> findByParentKeyOrderByIndexInLineAsc(String parentKey);
}
