package com.zc.pagination.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zc.pagination.entity.User;
import com.zc.pagination.model.MyPage;
import com.zc.pagination.model.ParamSome;
import com.zc.pagination.model.UserChildren;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * @author zhangchi
 * @date 2019/12/5
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 3.x 的 page 可以进行取值,多个入参记得加上注解
     * 自定义 page 类必须放在入参第一位
     * 返回值可以用 IPage<T> 接收 也可以使用入参的 MyPage<T> 接收
     * <li> 3.1.0 之前的版本使用注解会报错,写在 xml 里就没事 </li>
     * <li> 3.1.0 开始支持注解,但是返回值只支持 IPage ,不支持 IPage 的子类</li>
     *
     * @param myPage 自定义 page
     * @param paramSome 自定义过滤数据的参数
     * @return 分页数据
     *
     * 注解方法：@Select("select * from user where (age = #{pg.selectInt} and name = #{pg.selectStr}) or (age = #{ps.yihao} and name = #{ps.erhao})")
     */
    MyPage<User> mySelectPage(@Param("pg") MyPage<User> myPage, @Param("ps") ParamSome paramSome);

    MyPage<User> mySelectPageMap(@Param("pg") MyPage<User> myPage, @Param("map") Map param);

    List<User> mySelectMap(Map param);

    List<User> myPageSelect(MyPage<User> page);

    List<User> rowBoundsList(RowBounds rowBounds, Map map);

    /**
     * 查找的数据为连接表的数据，需要resultMap。
     * 在注解方式中，要使用动态sql就需要使用<script></script>。
     * 一个入参对象，可以不使用@Param且可以直接使用#{属性名}获取值、在if test中直接使用属性名获取值。
     * @param myPage
     * @return
     */
    @ResultMap("userChildrenMap")
    @Select("<script>select u.id,u.name,u.email,u.age,c.id as \"c_id\",c.name as \"c_name\",c.user_id as \"c_user_id\" " +
            "from user u " +
            "left join children c on c.user_id = u.id " +
            "<where>" +
            "<if test=\"selectInt != null\"> " +
            "and u.age = #{selectInt} " +
            "</if> " +
            "<if test=\"selectStr != null and selectStr != ''\"> " +
            "and c.name = #{selectStr} " +
            "</if> " +
            "</where>" +
            "</script>")
    MyPage<UserChildren> userChildrenPage(MyPage<UserChildren> myPage);
}
