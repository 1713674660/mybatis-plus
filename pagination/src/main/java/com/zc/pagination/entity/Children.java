package com.zc.pagination.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangchi
 * @date 2019/12/5
 */
@Data
@Accessors(chain = true)
public class Children {
    private Long id;
    private String name;
    private Long userId;
}
