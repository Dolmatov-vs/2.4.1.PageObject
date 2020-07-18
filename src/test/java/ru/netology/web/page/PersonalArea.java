package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Data
public class PersonalArea {
    private SelenideElement youCards = $("h1.heading_theme_alfa-on-white");
    private SelenideElement card0001 = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement card0002 = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    private int balanceFirstCard = getFirstCardBalance();
    private int balanceSecondCard = getSecondCardBalance();

    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public PersonalArea() {
        youCards.shouldHave(visible, text("Ваши карты"));
    }

    public int getFirstCardBalance() {
        val text = card0001.text();
        return extractBalance(text);
    }

    public int getSecondCardBalance() {
        val text = card0002.text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


    public TransferMoney topUpCard0001(){
        card0001.$("button").click();
     return new TransferMoney();
    }

    public TransferMoney topUpCard0002(){
        card0002.$("button").click();
        return new TransferMoney();
    }
}
