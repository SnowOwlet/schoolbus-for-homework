package cn.udday.schoolbus;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static cn.udday.schoolbus.config.Config.KEY;

public class SchoolBusUtils {
    public static int token2UserId(String token){
        Claims claims = Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
        String tokenUserId = (String) claims.get("userid");
        return Integer.parseInt(tokenUserId);
    }
}
