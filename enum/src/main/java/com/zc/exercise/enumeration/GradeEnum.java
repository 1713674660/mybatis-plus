package com.zc.exercise.enumeration;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author zhangchi
 * @date 2019/12/7
 */
@Getter
public enum GradeEnum {
    /**
     * 带@EnumValue的原生枚举类。@EnumValue标识该枚举与数据库交互的字段
     */
    PRIMARY(1, "小学"),
    MIDDLE(2, "中学"),
    COLLEGE(3, "大学");

    @EnumValue
    private Integer code;
    private String desc;

    GradeEnum(Integer code, String desc){
        this.code = code;
        this.desc = desc;
    }
}
