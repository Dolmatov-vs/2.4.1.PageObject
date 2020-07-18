package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;

import static com.codeborne.selenide.Selenide.*;

@Data
public class TransferMoney {
    private SelenideElement transfer = $("[class='heading heading_size_xl heading_theme_alfa-on-white']");
    private SelenideElement beneficiaryCard = $("[class='input__control']");
    private SelenideElement summa = $("[class='input__control'][type=text]");
    private SelenideElement whence = $("[class='input__control'][type=tel]");
    private SelenideElement confirm = $("[data-test-id='action-transfer']");
    private int writeOffAmount = 3000;


    public TransferMoney(){
//        transfer.shouldHave(text("Пополнение карты"));
    }

    public PersonalArea topUpCard0001(){
        beneficiaryCard.attr("**** **** **** 0001");
        summa.setValue(Integer.toString(writeOffAmount));
        whence.setValue("5559000000000002");
        confirm.click();
        return new PersonalArea();
    }

}
