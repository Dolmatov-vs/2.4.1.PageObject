package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.CardInfo;
import ru.netology.web.data.UserData;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.PersonalArea;
import ru.netology.web.page.TransferMoney;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {
    private int amount = 500;


        public void checkBalance(){
        val balance = new PersonalArea();
        if (amount > balance.getSecondCardBalance()) {
            val transferMoney = new TransferMoney();
            transferMoney.getTitleError().shouldHave(visible, text("Недостаточно средств для списания"));
        }
    }

    @BeforeEach
    void validLogin(){
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = UserData.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verificationCode = UserData.getVerifyCodeFor(authInfo);
        verificationPage.validVerify(verificationCode);
    }

    @Test
    @Order(1)
    void shouldSuccessfulTransferFromCard1ToCard2(){
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001();
        val transferMoney = new TransferMoney();
        transferMoney.transfer(CardInfo.getFirstCard(), amount, CardInfo.getSecondCard());
        checkBalance();
        int newBalanceFirstCard = personalArea.getFirstCardBalance();
        int newBalanceSecondCard = personalArea.getSecondCardBalance();
        assertEquals(personalArea.getBalanceFirstCard() + amount, newBalanceFirstCard);
        assertEquals(personalArea.getBalanceSecondCard() - amount, newBalanceSecondCard);

    }

    @Test
    @Order(2)
    void shouldSuccessfulTransferFromCard2ToCard1() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0002();
        val transferMoney = new TransferMoney();
        transferMoney.transfer(CardInfo.getSecondCard(), amount, CardInfo.getFirstCard());
        checkBalance();
        int newBalanceFirstCard = personalArea.getFirstCardBalance();
        int newBalanceSecondCard = personalArea.getSecondCardBalance();
        assertEquals(personalArea.getBalanceFirstCard() - amount, newBalanceFirstCard);
        assertEquals(personalArea.getBalanceSecondCard() + amount, newBalanceSecondCard);
    }

    @Test
    @Order(3)
    void shouldSuccessfulTransferFromCard1ToCard1() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001();
        val transferMoney = new TransferMoney();
        transferMoney.transfer(CardInfo.getFirstCard(), amount, CardInfo.getFirstCard());
        checkBalance();
        int newBalanceFirstCard = personalArea.getFirstCardBalance();
        int newBalanceSecondCard = personalArea.getSecondCardBalance();
        assertEquals(personalArea.getBalanceFirstCard() , newBalanceFirstCard);
        assertEquals(personalArea.getBalanceSecondCard(), newBalanceSecondCard);
    }

    @Test
    @Order(4)
    void shouldSuccessfulTransferFromCard2ToCard2() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0002();
        val transferMoney = new TransferMoney();
        transferMoney.transfer(CardInfo.getSecondCard(), amount, CardInfo.getSecondCard());
        checkBalance();
        int newBalanceFirstCard = personalArea.getFirstCardBalance();
        int newBalanceSecondCard = personalArea.getSecondCardBalance();
        assertEquals(personalArea.getBalanceFirstCard(), newBalanceFirstCard);
        assertEquals(personalArea.getBalanceSecondCard(), newBalanceSecondCard);
    }

    @Test
    @Order(5)
    void shouldErrorRechargeCard1IfCardWriteOffsDoesNotExist() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001();
        val transferMoney = new TransferMoney();
        val thirdCard = CardInfo.NonExistentCard;
        transferMoney.transfer(CardInfo.getFirstCard(), amount, new CardInfo.getNonExistentCard());
        transferMoney.getTitleError().shouldHave(visible, text("Ошибка"));
    }

//    @Test
//    @Order(6)
//    void shouldErrorRechargeCard2IfCardWriteOffsDoesNotExist() {
//
//        val personalArea = new PersonalArea();
//        personalArea.topUpCard0002();
//        val transferMoney = new TransferMoney();
//        transferMoney.transfer(CardInfo.getSecondCard(), amount, );
//        transferMoney.getTitleError().shouldHave(visible, text("Ошибка"));
//    }
//
    @Test
    @Order(7)
    void shouldErrorInsufficientFundsToWriteOffOnCard2() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001();
        val transferMoney = new TransferMoney();
        transferMoney.transfer(CardInfo.getFirstCard(), personalArea.getBalanceSecondCard()+1, CardInfo.getSecondCard());
        checkBalance();
    }
}
