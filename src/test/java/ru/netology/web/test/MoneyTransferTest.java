package ru.netology.web.test;

import lombok.val;
import org.junit.jupiter.api.*;
import ru.netology.web.data.UserData;
import ru.netology.web.page.LoginPage;
import ru.netology.web.page.PersonalArea;
import ru.netology.web.page.TransferMoney;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MoneyTransferTest {

//    public void balanceCheck(){
//        val transferMoney = new TransferMoney();
//        val balance = new PersonalArea();
//        int writeOffAmount = transferMoney.getWriteOffAmount();
//        int newBalanceFirstCard = balance.getFirstCardBalance();
//        int newBalanceSecondCard = balance.getSecondCardBalance();
//
//        if (balance.getBalanceFirstCard() < newBalanceFirstCard)
//            assertEquals(balance.getBalanceFirstCard() + writeOffAmount, newBalanceFirstCard);
//        if (balance.getBalanceFirstCard() > newBalanceFirstCard)
//            assertEquals(balance.getBalanceFirstCard() - writeOffAmount, newBalanceFirstCard);
//        else return;
//
//        if (balance.getSecondCardBalance() < newBalanceSecondCard)
//            assertEquals(balance.getSecondCardBalance() + writeOffAmount, newBalanceSecondCard);
//        if (balance.getSecondCardBalance() > newBalanceSecondCard)
//            assertEquals(balance.getSecondCardBalance() - writeOffAmount, newBalanceSecondCard);
//        else return;
//    }

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
    void shouldSuccessfulTransferFromCard1ToCard2() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001().card1WithCard2();
//        balanceCheck();
    }

    @Test
    void shouldSuccessfulTransferFromCard2ToCard1() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0002().card2WithCard1();
//        balanceCheck();
    }

    @Test
    void shouldSuccessfulTransferFromCard1ToCard1() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001().card1WithCard1();
//        balanceCheck();
    }

    @Test
    void shouldSuccessfulTransferFromCard2ToCard2() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0002().card2WithCard2();
//        balanceCheck();
    }

    @Test
    void shouldErrorRechargeCard1IfCardWriteOffsDoesNotExist() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0001().card1WithCard3();
    }

    @Test
    void shouldErrorRechargeCard2IfCardWriteOffsDoesNotExist() {
        val personalArea = new PersonalArea();
        personalArea.topUpCard0002().card2WithCard3();
    }

    @Test
    @Order(7)
    void shouldErrorInsufficientFundsToWriteOffOnCard2() {
        val personalArea = new PersonalArea();
        val transfer = new TransferMoney();
        personalArea.topUpCard0001();
        transfer.setWriteOffAmount(personalArea.getBalanceSecondCard()+1);
        transfer.card1WithCard2();
    }
}
