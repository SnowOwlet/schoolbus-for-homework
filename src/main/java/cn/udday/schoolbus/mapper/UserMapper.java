package cn.udday.schoolbus.mapper;

import cn.udday.schoolbus.model.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.*, c.* " +
            "from sys_user u, sys_campus c " +
            "where u.campus_id=c.campus_id")
    List<User> getList();

    @Select("select u.*, c.* " +
            "from sys_user u, sys_campus c " +
            "where u.campus_id=c.campus_id and ${ew.sqlSegment}")
    IPage<User> query(IPage<User> page, @Param("ew") Wrapper<User> wrapper);
}
