package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class PersonalArea {
    private SelenideElement heading = $("[data-test-id=dashboard]");

    public PersonalArea() {
        heading.shouldBe(visible);
    }
}
