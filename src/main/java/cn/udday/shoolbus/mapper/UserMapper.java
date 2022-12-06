package cn.udday.shoolbus.mapper;

import cn.udday.shoolbus.model.User;
import cn.udday.shoolbus.model.UserVo;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select u.*, c.* " +
            "from sys_user u, sys_campus c " +
            "where u.campus_id=c.campus_id")
    List<UserVo> getListAssociated();

    @Select("select u.*, c.* " +
            "from sys_user u, sys_campus c " +
            "where u.campus_id=c.campus_id and ${ew.sqlSegment}")
    IPage<UserVo> queryAssociated(IPage<UserVo> page, @Param("ew") Wrapper<UserVo> wrapper);
}
