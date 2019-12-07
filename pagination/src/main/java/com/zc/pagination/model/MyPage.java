package com.zc.pagination.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangchi
 * @date 2019/12/5
 */
@Data
@Accessors(chain = true)
public class MyPage<T> extends Page<T> {
    private Integer selectInt;
    private String selectStr;
    private String name;

    public MyPage(long current, long size){
        super(current, size);
    }
}
