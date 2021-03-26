package cn.bank.fake;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xiaoxiong
 */
@RestController
@RequestMapping("/api")
@SuppressWarnings("PMD.ClassNamingShouldBeCamelRule")
public class APIController {

    final SessionDatabase sessionDatabase;

    public APIController(SessionDatabase sessionDatabase) {
        this.sessionDatabase = sessionDatabase;
    }

    /**
     * 用于快速检测此API服务是否在线
     * TODO 未来可考虑统一改成json格式
     *
     * @return 服务在线信息
     */
    @GetMapping("/ping")
    public String ping() {
        return "Don't panic. This API is up :)";
    }

    /**
     * 登陆
     *
     * @param data     用户名和密码，保存在一个Map里
     * @param response HTTP回复，用于设置cookie
     * @return 登陆结果，以json字符串返回
     */
    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> data, HttpServletResponse response) {
        String username = data.get("username");
        String password = sessionDatabase.decryptCaesar(data.get("password"));
        String responseJson;

        // 如果用户名密码正确，为该用户创建一个新的session
        if (sessionDatabase.validCredentials(username, password)) {
            String sessionId = sessionDatabase.newSession(username);
            Cookie cookie = new Cookie("session_id", sessionId);
            cookie.setHttpOnly(false);
            cookie.setPath("/");
            response.addCookie(cookie);
            responseJson = "{\"status\": \"good\"}";
        }
        // 如果不正确，返回一个错误的json信息
        else {
            responseJson = "{\"status\": \"bad\", \"errMsg\": \"用户名密码错误\"}";
        }
        return responseJson;
    }

    /**
     * 根据cookie获取用户名
     *
     * @param sessionId cookie中包含的sessionId
     * @return 用户名
     */
    @GetMapping("/name")
    public String getName(@CookieValue(name = "session_id") String sessionId) {
        String name = sessionDatabase.getSessionByID(sessionId);
        return String.format("{\"status\": \"good\", \"name\": \"%s\"}", name);
    }
}
