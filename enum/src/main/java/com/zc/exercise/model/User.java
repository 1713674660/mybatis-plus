package com.zc.exercise.model;

import com.zc.exercise.enumeration.AgeEnum;
import com.zc.exercise.enumeration.GenderEnum;
import com.zc.exercise.enumeration.GradeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author zhangchi
 * @date 2019/12/7
 */
@Data
@Accessors(chain = true)
public class User {
    private Long id;
    private String name;
    private AgeEnum age;
    private GenderEnum gender;
    private GradeEnum grade;
    private String email;
}
