package com.zc.quickstart.model;

import lombok.Data;

/**
 * @author zhangchi
 * @date 2019/12/4
 */
@Data
public class Admin {
    private Long id;
    private String name;
    private String pwd;
    private String phone;
    private String email;
    private String addr;
}
