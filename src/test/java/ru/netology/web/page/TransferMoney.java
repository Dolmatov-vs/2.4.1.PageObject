package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.junit.jupiter.api.Assertions;

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


    public TransferMoney() {
        transfer.shouldHave(visible, text("Пополнение карты"));
    }


    public void setAmount(int amount){
        summa.setValue(Integer.toString(amount));
    }

    public void setFromWhere(String card){
        whence.setValue(card);
    }

    public void checkCardRecipient(String valueCard){
        Assertions.assertEquals(valueCard, beneficiaryCard.getValue());
    }

    public void transfer (String valueCard, int amount, String card){
        checkCardRecipient(valueCard);
        setAmount(amount);
        setFromWhere(card);
        confirm.click();
    }
}
