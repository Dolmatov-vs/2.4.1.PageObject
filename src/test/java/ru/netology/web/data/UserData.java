package ru.netology.web.data;

import lombok.Value;

public class UserData {
    private UserData() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }
    public static AuthInfo getAuthInfo () {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerifyCode{
        private String code;
    }
    public static VerifyCode getVerifyCodeFor(AuthInfo authInfo){
        return new VerifyCode("12345");
    }
}
