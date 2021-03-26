package cn.bank.fake;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author xiaoxiong
 */
@Controller
public class SiteController {
    final SessionDatabase sessionDatabase;

    public SiteController(SessionDatabase sessionDatabase) {
        this.sessionDatabase = sessionDatabase;
    }

    /**
     * 用户访问网址/balance时，渲染模板balance.html，并对其中的name进行替换
     *
     * @param model     传递给模板balance.html的model
     * @param sessionId 用户的session
     * @return 要渲染的模板文件名
     */
    @GetMapping("/balance")
    public String balance(Model model, @CookieValue(name = "session_id") String sessionId) {
        String name = sessionDatabase.getSessionByID(sessionId);
        model.addAttribute("name", name);
        return "balance";
    }

    /**
     * 根据用户输入的账户名和密码进行登陆验证
     *
     * @param data     账户名和密码
     * @param response 用户最终收到的HTTP回应，主要用来设置HTTP状态码
     */
    @PostMapping("/login")
    public void login(@RequestBody Map<String, String> data, HttpServletResponse response) {
        String username = data.get("username");
        String password = sessionDatabase.decryptCaesar(data.get("password"));

        // 如果用户名密码正确，为该用户创建一个新的session
        if (sessionDatabase.validCredentials(username, password)) {
            String sessionId = sessionDatabase.newSession(username);
            Cookie cookie = new Cookie("session_id", sessionId);
            response.addCookie(cookie);
            response.setStatus(HttpStatus.OK.value());
        }
        // 如果不正确，禁止登陆，返回403Forbidden
        else {
            response.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }
}

