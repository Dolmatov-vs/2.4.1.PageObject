package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.UserData;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;

public class MoneyTransferTest {
    @Test
    void shouldlogin() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = UserData.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = UserData.getVerifyCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }
}
