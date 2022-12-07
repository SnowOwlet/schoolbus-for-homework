package cn.udday.schoolbus.mapper;

import cn.udday.schoolbus.model.Token;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TokenMapper extends BaseMapper<Token> {
    @Select("select * from token where user_id = ${userid}")
    Token findByUserId(int userId);

}
