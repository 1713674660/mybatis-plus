package com.zc.quickstart.model;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.quickstart.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangchi
 * @date 2019/12/4
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class AdminTest {
    @Resource
    private AdminMapper adminMapper;

    @Test
    public void testUserMapper(){
        List<Admin> userList =adminMapper.selectList(null);
        userList.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        Admin admin = new Admin();
        admin.setAddr("insertAddr");
        admin.setEmail("insertEmail");
        admin.setName("insertName");
        admin.setPhone("insertPhone");
        admin.setPwd("insertPWd");
        adminMapper.insert(admin);
    }

    @Test
    public void testDelete(){
        adminMapper.deleteById(3);
        adminMapper.delete(new QueryWrapper<Admin>().eq(true, "name", "admin2"));
    }

    @Test
    public void testUpdate(){
        Admin admin = new Admin();
        admin.setId(1L);
        admin.setPwd("123123123");
        admin.setPhone("1111111111111111");
        //根据实体id字段修改表数据，只更改实体设置值的属性对应的字段。
        adminMapper.updateById(admin);
    }

    @Test
    public void testSelect(){
        System.out.println(adminMapper.selectById(1L).toString());
        adminMapper.selectList(new QueryWrapper<Admin>()
                .eq("name", "insertName"))
                .forEach(System.out::println);
        System.out.println(adminMapper.selectList(Wrappers.<Admin>lambdaQuery().select(Admin::getPwd)).toString());
        System.out.println(adminMapper.selectList(new QueryWrapper<Admin>().select("name", "pwd")));
    }

    @Test
    public void testOrderBy(){
        adminMapper.selectList(Wrappers.<Admin>query().orderByAsc("name"))
                .forEach(System.out::println);
    }

    @Test
    public void testOrderByLambda(){
        List<Admin> list = adminMapper.selectList(Wrappers.<Admin>lambdaQuery().orderByAsc(Admin::getPwd));
        System.out.println(list);
    }

    @Test
    public void testSelectMap(){
        List<Map<String, Object>> mapList = adminMapper.selectMaps(Wrappers.<Admin>query().orderByDesc("pwd"));
        System.out.println(mapList);
    }

    @Test
    public void testSelectMapPage(){
        IPage<Map<String, Object>> page = adminMapper.selectMapsPage(new Page<>(1, 3), Wrappers.<Admin>query().orderByDesc("pwd"));
        System.out.println(page.getRecords());
    }

    @Test
    public void testMaxId(){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("max(id) as id");
        Admin admin = adminMapper.selectOne(queryWrapper);
        System.out.println(admin.toString());
    }

    @Test
    public void testGroup(){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("pwd").groupBy("pwd");
        List<Map<String, Object>> mapList =adminMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);

        System.out.println("-----------------------");

        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new QueryWrapper<Admin>()
                .lambda()
                .select(Admin::getId)
                .groupBy(Admin::getId)
                .orderByAsc(Admin::getPwd);
        adminMapper.selectList(lambdaQueryWrapper).forEach(System.out::println);
    }

    @Test
    public void testTableFieldExistFalse(){
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name, pwd, phone");
        List list = adminMapper.selectList(queryWrapper);
        list.forEach(System.out::println);

        Admin admin = new Admin();
        admin.setName("newName");
        admin.setPhone("newPhone");
        admin.setPwd("newPwd");
        admin.setEmail("newEmail");
        admin.setAddr("newAddr");
        adminMapper.insert(admin);
        Map<String,String> param = new HashMap<>();
        param.put("name", "newName");
        param.put("phone", "newPhone");
        System.out.println(adminMapper.selectList(new QueryWrapper<Admin>().allEq(param)));
    }
}