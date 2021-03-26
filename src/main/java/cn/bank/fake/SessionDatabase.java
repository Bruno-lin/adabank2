package cn.bank.fake;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * @author xiaoxiong
 */
@Component
public class SessionDatabase {
    private static final String[] USERS = new String[]{"13889401292", "13478783293", "admin@bank.cn"};
    private static final String[] PASSWORDS = new String[]{"123456", "helloworld", "adalab2020"}; // stuvwx   85ccfnfic4
    public static final String PASSWORD_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
    public static final int CAESAR_KEY = 9;

    // 用来记录下一个生成的sessionId应该是多少
    private static int nextSessionId = 0;

    // 储存所有的session，key是sessionId，value是用户名
    private final Map<String, String> sessions = new HashMap<>();

    public SessionDatabase() {
    }

    /**
     * 建立一个新的session
     *
     * @param username 用户名，该方法将假定此用户名是有效的
     * @return 新建立session的ID
     */
    public String newSession(String username) {
        String sessionId = "" + nextSessionId;
        nextSessionId += 1;
        sessions.put(sessionId, username);
        return sessionId;
    }

    /**
     * 判断session是否存在
     *
     * @param sessionId session的ID
     * @return 存在返回true，否则返回false
     */
    public boolean hasSession(String sessionId) {
        return sessions.containsKey(sessionId);
    }

    /**
     * 判断用户是否存在
     *
     * @param user 用户名
     * @return 存在返回true，否则返回false
     */
    public boolean hasUser(final String user) {
        return Arrays.asList(USERS).contains(user);
    }

    /**
     * 检查用户名密码是否匹配
     *
     * @param username 用户名
     * @param password 密码
     * @return 匹配返回true，否则返回false
     */
    public boolean validCredentials(String username, String password) {
        return IntStream.range(0, USERS.length).anyMatch(i -> username.equals(USERS[i]) && password.equals(PASSWORDS[i]));
    }

    /**
     * 获取已经登录的session
     *
     * @param sessionId 要查找的sessionId
     * @return 找到的该session所对应的用户名
     */
    public String getSessionByID(String sessionId) {
        return sessions.get(sessionId);
    }

    /**
     * 对密文进行解密，解密方式为翻转
     *
     * @param ciphertext 密文
     * @return 解密后的明文
     */
    public String decryptReverse(String ciphertext) {
        return new StringBuilder(ciphertext).reverse().toString();
    }

    /**
     * 对密文进行解密，解密方式为凯撒密码
     *
     * @param ciphertext 密文
     * @return 解密后的明文
     */
    public String decryptCaesar(String ciphertext) {
        StringBuilder result = new StringBuilder();
        for (char c : ciphertext.toCharArray()) {
            int posInCipherText = PASSWORD_ALPHABET.indexOf(c);
            int posInPlainText = (posInCipherText + CAESAR_KEY) % PASSWORD_ALPHABET.length();
            result.append(PASSWORD_ALPHABET.charAt(posInPlainText));
        }
        return result.toString();
    }
}
