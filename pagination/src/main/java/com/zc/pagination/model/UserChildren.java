package com.zc.pagination.model;

import com.zc.pagination.entity.Children;
import com.zc.pagination.entity.User;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author zhangchi
 * @date 2019/12/5
 */
@Data
@Accessors(chain = true)
public class UserChildren extends User {
    private List<Children> children;
}
