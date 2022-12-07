package cn.udday.schoolbus.config;

import cn.udday.schoolbus.mapper.TokenMapper;
import cn.udday.schoolbus.model.Response;
import cn.udday.schoolbus.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private TokenMapper tokenMapper;

    //提供查询
    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
            throws Exception {
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
        //普通路径放行
        if (
                "/API/user/login".equals(arg0.getRequestURI()) ||
                "/API/user/register".equals(arg0.getRequestURI())
        ) {
            return true;
        }
        //权限路径拦截
        arg1.setCharacterEncoding("UTF-8");
        PrintWriter resultWriter = arg1.getWriter();
        final String headerToken = arg0.getHeader("XW-Token");
        //判断请求信息
        if (null == headerToken || headerToken.trim().equals("")) {
            resultWriter.write(Response.error("你没有token,需要登录").toJson());
            return false;
        }
        //解析Token信息
        try {
            Claims claims = Jwts.parser().setSigningKey("dahao").parseClaimsJws(headerToken).getBody();
            String tokenUserId = (String) claims.get("userid");
            int itokenUserId = Integer.parseInt(tokenUserId);
            //根据客户Token查找数据库Token
            Token myToken = tokenMapper.findByUserId(itokenUserId);

            //数据库没有Token记录
            if (null == myToken) {
                resultWriter.write(Response.error("token已过期,需要登录").toJson());
                return false;
            }
            //数据库Token与客户Token比较
            if (!headerToken.equals(myToken.getToken())) {
                resultWriter.write(Response.error("token已过期,需要登录").toJson());
                return false;
            }
            //判断Token过期
            Date tokenDate = (Date) claims.getExpiration();
            int chaoshi = (int) (new Date().getTime() - tokenDate.getTime()) / 1000;
            if (chaoshi > 60 * 60 * 24 * 3) {
                resultWriter.write(Response.error("token已过期,需要登录").toJson());
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            resultWriter.write(Response.error("token已过期,需要登录").toJson());
            return false;
        }
        //最后才放行
        return true;
    }
}