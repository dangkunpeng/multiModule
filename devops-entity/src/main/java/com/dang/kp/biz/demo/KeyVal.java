package com.dang.kp.biz.demo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class KeyVal {
    @Id
    private String key;
    @Column
    private String val;
    @Column
    private Integer indexInLine;
    @Column
    private String parentKey;
}
