package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import lombok.val;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Data
public class TransferMoney {
    private SelenideElement transfer = $("h1.heading_theme_alfa-on-white");
    private SelenideElement beneficiaryCard = $("[data-test-id=to] input");
    private SelenideElement summa = $("[data-test-id=amount] input");
    private SelenideElement whence = $("[data-test-id=from] input");
    private SelenideElement confirm = $("[data-test-id='action-transfer']");
    private SelenideElement titleError = $("[class='notification__title']");

    public int writeOffAmount = 500;


    public TransferMoney() {
//        transfer.shouldHave(visible, text("Пополнение карты"));
    }

    public void checkBalance(){
        val balance = new PersonalArea();
        if (writeOffAmount > balance.getSecondCardBalance()) {
            System.out.println("Недостаточно средств для списания");
            titleError.shouldHave(visible, text("Недостаточно средств для списания"));
        }
    }

    public PersonalArea card1WithCard2() {
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000002");
        confirm.click();
        checkBalance();
        return new PersonalArea();
    }

    public PersonalArea card1WithCard1() {
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000001");
        confirm.click();
        checkBalance();
        return new PersonalArea();
    }

    public void card1WithCard3() {
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000003");
        confirm.click();
        titleError.shouldHave(visible, text("Ошибка"));
    }

    public PersonalArea card2WithCard1() {
        beneficiaryCard.attr("**** **** **** 0002");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000001");
        confirm.click();
        checkBalance();
        return new PersonalArea();
    }

    public PersonalArea card2WithCard2() {
        beneficiaryCard.attr("**** **** **** 0002");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000002");
        confirm.click();
        checkBalance();
        return new PersonalArea();
    }

    public void card2WithCard3() {
        beneficiaryCard.attr("**** **** **** 0002");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000003");
        confirm.click();
        titleError.shouldHave(visible, text("Ошибка"));
    }
}
