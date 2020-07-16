package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.UserData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");

    public VerificationPage(){
        codeField.shouldBe(visible);
    }

    public PersonalArea validVerify(UserData.VerifyCode code){
        codeField.setValue(code.getCode());
        verifyButton.click();
        return new PersonalArea();
    }
}
