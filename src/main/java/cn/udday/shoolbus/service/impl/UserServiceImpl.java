package cn.udday.shoolbus.service.impl;

import cn.udday.shoolbus.mapper.UserMapper;
import cn.udday.shoolbus.model.Response;
import cn.udday.shoolbus.model.User;
import cn.udday.shoolbus.model.UserVo;
import cn.udday.shoolbus.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service("user")
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    //  配置logger
    private final static Logger logger = LoggerFactory.getLogger(UserService.class);
    private final static String projectName = "campus-bus-server";
    private final static String text = "用户";

    @Override
    public Object getUserList() {
        try {
            List<User> users = userMapper.selectList(null);
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, users);
            return new Response<>(users);
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object getUserListAssociated() {
        try {
            List<UserVo> userVoList = userMapper.getListAssociated();
            logger.info("[{}]:: 查询所有{}信息 >>> 查询成功 {}", projectName, text, userVoList);
            return new Response<>(userVoList);
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "未查询到{}！", text);
            logger.error("[{}]::查询所有{}信息 >>> 查询失败！{}", projectName, text, response);
            return response;
        }
    }

    @Override
    public Object queryUser(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<User> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                userMapper.selectPage(page, null);
                List<User> users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]getUserInfo:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(users, pageTotal);
            } else if ("id".equals(mode)) {
                User user = userMapper.selectById(options);
                List<User> users = new ArrayList<>();
                users.add(user);
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, users);
                return new Response<>(users);
            } else if ("name".equals(mode)) {
                QueryWrapper<User> wrapper = new QueryWrapper<>();
                wrapper.eq("user_name", options);
                userMapper.selectPage(page, wrapper);
                List<User> users = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(users, pageTotal);
            } else {
                Response<Object> response = new Response<>(-2, "查询模式错误！");
                logger.error("[{}]:: 查询所有{}信息 >>> 查询失败 [{}]", projectName, text, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "查询失败！");
            logger.error("[{}]::查询{}信息 >>> 查询失败！[{}]", projectName, text, e);
            return response;
        }
    }

    @Override
    public Object queryUserAssociated(Map<String, String> data) {
        String mode = data.get("mode");
        String options = data.get("options");
        logger.info("[{}]:: 查询{}信息:: 查询模式-> " + mode + " 查询参数->" + options, projectName, text);
        IPage<UserVo> page = new Page<>(Integer.parseInt(data.get("pageIndex")), Integer.parseInt(data.get("pageSize")));
        try {
            if ("all".equals(options)) {
                QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
                wrapper.isNotNull("user_id").orderByAsc("user_id");
                userMapper.queryAssociated(page, wrapper);
                List<UserVo> userVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询所有{}信息 >>> 查询成功", projectName, text);
                return new Response<>(userVoList, pageTotal);
            } else if ("id".equals(mode)) {
                QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
                wrapper.eq("order_id", options);
                userMapper.queryAssociated(page, wrapper);
                List<UserVo> userVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功 {}", projectName, text, mode, userVoList);
                return new Response<>(userVoList, pageTotal);
            } else if ("name".equals(mode)) {
                QueryWrapper<UserVo> wrapper = new QueryWrapper<>();
                wrapper.eq("user_name", options);
                userMapper.queryAssociated(page, wrapper);
                List<UserVo> userVoList = page.getRecords();
                int pageTotal = (int) page.getTotal();
                logger.info("[{}]:: 查询{}信息:: 查询模式-> {} >>> 查询成功", projectName, mode, text);
                return new Response<>(userVoList, pageTotal);
            } else {
                Response<Object> response = new Response<>(-2, "查询模式错误！");
                logger.error("[{}]:: 查询所有{}信息 >>> 查询失败 [{}]", projectName, text, response);
                return response;
            }
        } catch (NullPointerException e) {
            Response<Object> response = new Response<>(-1, "查询失败！");
            logger.error("[{}]::查询{}信息 >>> 查询失败！[{}]", projectName, text, e);
            return response;
        }
    }
}
