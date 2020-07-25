package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import lombok.Data;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.CardInfo;

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

    public void setFromWhere(CardInfo.DataCards card){
        whence.setValue(card.getCards());
    }

    public void checkCardRecipient(CardInfo.DataCards value){
        Assertions.assertEquals(value.getValue(), beneficiaryCard.getValue());
    }

    public void transfer (CardInfo.DataCards value, int amount, CardInfo.DataCards card){
        checkCardRecipient(value);
        setAmount(amount);
        setFromWhere(card);
        confirm.click();
    }
}
