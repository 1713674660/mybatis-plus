package com.zc.wrapper.model;

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
    private String email;
    private String addr;
    private String phone;
    private Long roleId;
}
