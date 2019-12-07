package com.zc.wrapper.model;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zc.wrapper.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author zhangchi
 * @date 2019/12/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 普通查询
     */
    @Test
    public void testSelect(){
        List admins = adminMapper.selectList(new QueryWrapper<Admin>().eq("name", "insertName"));
        List adminList = adminMapper.selectList(new QueryWrapper<Admin>().lambda().eq(Admin::getName, "insertName"));
        admins.forEach(System.out::println);
        adminList.forEach(System.out::println);
    }

    /**
     * in范围查询（sql注入）：查询指定字段值为指定SQL语句查询值的记录
     */
    @Test
    public void testSonSelect(){
        List<Admin> admins = adminMapper.selectList(new QueryWrapper<Admin>()
                .inSql("role_id", "select id from role where id = 1"));
        List<Admin> adminList = adminMapper.selectList(new QueryWrapper<Admin>().lambda()
                .inSql(Admin::getRoleId, "select id from role where id = 3"));
        admins.forEach(System.out::println);
        System.out.println("---------------------");
        adminList.forEach(System.out::println);
    }

    /**
     * 嵌套查询
     */
    @Test
    public void testNestedSelect(){
        List<Admin> admins = adminMapper.selectList(new QueryWrapper<Admin>()
                .nested(query -> query.eq("role_id", 1L).or().eq("role_id", 3L))
                .and(query -> query.eq("name", "insertName"))
        );
        List<Admin> adminList = adminMapper.selectList(new QueryWrapper<Admin>().lambda()
                .nested(wrapper -> wrapper.eq(Admin::getRoleId, 1L).or().eq(Admin::getRoleId, 3L))
                .and(wrapper -> wrapper.eq(Admin::getName, "insertName"))
        );
        admins.forEach(System.out::println);
        System.out.println("---------------------");
        adminList.forEach(System.out::println);
    }

    /**
     * 自定义（sql注入）
     */
    @Test
    public void testApply(){
        List<Admin> admins = adminMapper.selectList(new QueryWrapper<Admin>().apply("role_id = 2"));
        admins.forEach(System.out::println);
    }

    @Test
    public void testUpdateWrapper(){
        UpdateWrapper<Admin> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", "new mybatis plus");
        updateWrapper.eq("id", 1L);
        adminMapper.update(new Admin(), updateWrapper);
    }

    /**
     * SELECT id,name,age,email,role_id FROM user
     * WHERE ( 1 = 1 ) AND ( ( name = ? AND pwd = ? ) OR ( name = ? AND pwd = ? ) )
     */
    @Test
    public void testSql() {
        QueryWrapper<Admin> w = new QueryWrapper<>();
        w.and(i -> i.eq("1", 1))
                .nested(i ->
                        i.and(j -> j.eq("name", "a").eq("pwd", 2))
                                .or(j -> j.eq("name", "b").eq("pwd", 2)));
        adminMapper.selectList(w);
    }
}