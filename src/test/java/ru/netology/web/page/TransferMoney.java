package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Data
public class TransferMoney {
    private SelenideElement transfer = $("[class='heading heading_size_xl heading_theme_alfa-on-white']");
    private SelenideElement beneficiaryCard = $("[class='input__control']");
    private SelenideElement summa = $("[class='input__control'][type=text]");
    private SelenideElement whence = $("[class='input__control'][type=tel]");
    private SelenideElement confirm = $("[data-test-id='action-transfer']");
    private SelenideElement titleError = $("[class='notification__title']");
    private int writeOffAmount = 3000;

    PersonalArea balance = new PersonalArea();



    public TransferMoney() {
//        transfer.shouldHave(text("Пополнение карты"));
    }

//    public PersonalArea card1WithCard2(){
//        beneficiaryCard.attr("**** **** **** 0001");
//        summa.setValue(Integer.toString(writeOffAmount));
//        whence.setValue("5559000000000002");
//        confirm.click();
//        return new PersonalArea();
//    }

    public PersonalArea card1WithCard2() {
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000002");
        confirm.click();
        if (writeOffAmount < balance.getSecondCardBalance())
            return new PersonalArea();
        return null;
    }

    public PersonalArea card1WithCard1() {
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000001");
        confirm.click();
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
        return new PersonalArea();
    }

    public PersonalArea card2WithCard2() {
        beneficiaryCard.attr("**** **** **** 0002");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000002");
        confirm.click();
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
