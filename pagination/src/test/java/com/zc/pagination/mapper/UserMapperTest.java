package com.zc.pagination.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.pagination.entity.User;
import com.zc.pagination.model.MyPage;
import com.zc.pagination.model.ParamSome;
import com.zc.pagination.model.UserChildren;
import org.apache.ibatis.session.RowBounds;
import org.assertj.core.util.Maps;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangchi
 * @date 2019/12/5
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testLambdaPagination(){
        Page<User> page = new Page<>(1, 5);
        IPage<User> result = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().ge(User::getAge, 1).orderByAsc(User::getAge));
        System.out.println(JSONObject.toJSONString(result));
    }

    @Test
    public void testPaginationFirst(){
        //Mybatis Plus自带分页方法
        Page<User> page = new Page<>(1, 5);
        IPage<User> result = userMapper.selectPage(page, new QueryWrapper<User>().eq("age", "3").eq("name", "name3"));
        System.out.println("总条数：" + result.getTotal());
        System.out.println("当前页码：" + result.getCurrent());
        System.out.println("当前每页显示数："+ result.getSize());
        System.out.println("当前页的数据：" + JSONObject.toJSONString(result.getRecords()));

        //自定义分页方法
        MyPage<User> myPage = new MyPage<User>(1,3).setSelectInt(20).setSelectStr("Jack");
        ParamSome paramSome = new ParamSome().setYihao(3).setErhao("Tom");
        MyPage<User> resultPage = userMapper.mySelectPage(myPage, paramSome);
        System.out.println(JSONObject.toJSONString(resultPage));
    }

    @Test
    public void rowBoundsTest() {
        //RowBounds 代替使用limit offset
        RowBounds rowBounds = new RowBounds(0, 5);
        List<User> list = userMapper.rowBoundsList(rowBounds, Maps.newHashMap("name", "%"));
        System.out.println("list.size=" + list.size());
    }
}