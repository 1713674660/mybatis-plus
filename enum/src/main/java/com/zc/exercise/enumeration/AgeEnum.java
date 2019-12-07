package com.zc.exercise.enumeration;

import com.baomidou.mybatisplus.core.enums.IEnum;
import lombok.Getter;

/**
 * @author zhangchi
 * @date 2019/12/7
 */
@Getter
public enum AgeEnum implements IEnum<Integer> {
    /**
     * 实现IEnum<与数据库交互的字段类型>的枚举类
     * 实现方法，返回该枚举与数据库交互的字段
     */
    ONE(1, "一岁"),
    TWO(2, "两岁"),
    THREE(3, "三岁");

    private Integer value;
    private String desc;

    AgeEnum(Integer value, String desc){
        this.value = value;
        this.desc = desc;
    }

    @Override
    public Integer getValue() {
        return value;
    }
}
