/**
 * IIFE模式：https://en.wikipedia.org/wiki/Immediately_invoked_function_expression
 * 可以防止变量名/函数名成为全局变量/函数，造成污染
 */
(function () {
    /**
     * 对密码进行加密
     * @param {string} passwd 用户输入的密码
     * @returns {string} 加密后的密码
     */
    function encrypt(passwd) {
        const PASSWORD_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyz";
        let result = '';
        for (let i = 0; i < passwd.length; i++) {
            let char = passwd[i];
            let posInPlainText = PASSWORD_ALPHABET.indexOf(char);
            let posInCipherText = (posInPlainText - 9) % PASSWORD_ALPHABET.length;
            result += PASSWORD_ALPHABET.charAt(posInCipherText);
        }
        return result;
    }

    /**
     * 从页面的用户输入中提取帐号和密码
     */
    function extractCredentials() {
        const username = $('#txt_username_79443').val();
        let reg = new RegExp(/^[a-zA-Z]+([-_.][a-zA-Z])*\d*@bank\.c(n|om)$/);
        if (username.match(reg)) {
            const password = encrypt($('#input_div_password_79445').val());
            return {username, password}
        } else {
            return null;
        }
    }

    /**
     * 点击登陆按键时的回调函数，向服务器发送用户名和密码，验证是否正确
     */
    function login() {
        $('#error-message').text('').hide();
        const data = extractCredentials();
        if (data == null) {
            $('#error-message').text('您输入的用户名有误！').show();
            return;
        }
        // 利用jQuery的ajax API将数据发送到服务器
        // 文档：https://api.jquery.com/jquery.ajax/
        $.ajax({
            type: 'POST',
            url: 'login',
            data: JSON.stringify(data),
            contentType: 'application/json'
        })
            .done(function () {
                window.location.href = 'balance';
            })
            .fail(function (jqXHR) {
                if (jqXHR.status === 403) {
                    $('#error-message').text('用户名密码错误！').show();
                } else {
                    $('#error-message').text('未知错误！').show();
                }
            });
    }

    // 添加事件监听函数，用户点击登陆按钮时，调用login函数
    document.querySelector('#login-button').addEventListener('click', login);
})();