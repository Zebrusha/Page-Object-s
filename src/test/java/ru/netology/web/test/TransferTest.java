package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.CardReplenishmentPage;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    DashboardPage dashboardPage;
    DataHelper.CardInfo firstCardInfo;
    DataHelper.CardInfo secondCardInfo;
    int firstCardBalance;
    int secondCardBalance;

    @BeforeEach
    void setUp(){
        var loginPageV1Page = open("http://localhost:9999", LoginPageV1.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPageV1Page.validLogin(authInfo);
        var verificatonCode = DataHelper.getVerificationCode(authInfo);
        dashboardPage = verificationPage.validfVerify(verificatonCode);
        firstCardInfo = DataHelper.getFirstCardInfo();
        secondCardInfo = DataHelper.getSecondCardInfo();
        firstCardBalance = dashboardPage.getCardBalance(firstCardInfo);
        secondCardBalance = dashboardPage.getCardBalance(secondCardInfo);
    }

    @Test
    void shouldTransferToSecondCardWithTheEntered(){
        var amount = DataHelper.getGenerateAmount(firstCardBalance);
        var expectedBalanceFirstCard = firstCardBalance - amount;
        var expactedBalanceSecondCard = secondCardBalance + amount;
        var transferPage = dashboardPage.selectedCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeValidTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalancFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        Assertions.assertEquals(expectedBalanceFirstCard,actualBalancFirstCard);
        Assertions.assertEquals(expactedBalanceSecondCard,actualBalanceSecondCard);
    }

    @Test
    void transferOfALargerAmountThanTheBalance(){
        var amount = DataHelper.getInvalidGenerateAmount(firstCardBalance);
        var transferPage = dashboardPage.selectedCardToTransfer(firstCardInfo);
        transferPage.makeTransfer(secondCardInfo, String.valueOf(amount));
        transferPage.findErrorMessage("Сумма перевода не должна превышать баланс карты!");
    }

































//    @Test
//    void shouldTransferMoneyBetweenOwnCardsV1(){
//        var loginPage = new LoginPageV1();
//        var authInfo = DataHelper.getAuthInfo();
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//    }
//    @Test
//    void shouldSelectingTheFirstMultipleForTransfer(){
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV1();
//
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//        var verifyPage = new VerificationPage();
//        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
//        var curentVerifyCode = verifyPage.validfVerify(verifyCode);
//
//        var dashboardPage = new DashboardPage();
//        var buttonFirstCard = dashboardPage.clickFirstButton();
//
//    }
//    @Test
//    void shouldSelectingTheSecondMultipleForTransfer(){
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV1();
//        var authInfo = DataHelper.getAuthInfo();
//
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//        var verifyPage = new VerificationPage();
//        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
//        var curentVerifyCode = verifyPage.validfVerify(verifyCode);
//
//        var dashboardPage = new DashboardPage();
//        var buttonFirstCard = dashboardPage.clickSecondButton();
//    }

//    void shouldTransferToFirstCardWithTheEntered(){
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV1();
//        var authInfo = DataHelper.getAuthInfo();
//
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//        var verifyPage = new VerificationPage();
//        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
//        var curentVerifyCode = verifyPage.validfVerify(verifyCode);
//
//        var dashboardPage = new DashboardPage();
//        var buttonFirstCard = dashboardPage.clickSecondButton();
//
//        var cardReplenishmentPage = new CardReplenishmentPage();
//        var transfer = cardReplenishmentPage.transferToCard("5559 0000 0000 0002");
//        var idCard = DataHelper.getIdCard();
//        var curentBalance = dashboardPage.curentBalnce(idCard);
//    }
//    @Test
//    void shouldTransferToSecondCardWithTheEntered(){
//        var loginPage = new LoginPageV1();
//        var authInfo = DataHelper.getAuthInfo();
//
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//        var verifyPage = new VerificationPage();
//        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
//        var curentVerifyCode = verifyPage.validfVerify(verifyCode);
//
//        var dashboardPage = new DashboardPage();
//        var buttonFirstCard = dashboardPage.clickFirstButton();
//
//        var amount = DataHelper.getGenerateAmount()
//
//    }

//    @Test
//    void transferOfALargerAmountThanTheBalance(){
//        open("http://localhost:9999");
//        var loginPage = new LoginPageV1();
//        var authInfo = DataHelper.getAuthInfo();
//
//        var verificationPage = loginPage.validLogin(authInfo);
//        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);
//
//        var verifyPage = new VerificationPage();
//        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
//        var curentVerifyCode = verifyPage.validfVerify(verifyCode);
//
//        var dashboardPage = new DashboardPage();
//        var buttonFirstCard = dashboardPage.clickSecondButton();
//
//        var cardReplenishmentPage = new CardReplenishmentPage();
//        var transfer = cardReplenishmentPage.SmallTransferToCard("5559 0000 0000 0001");
//        var idCard = DataHelper.getIdCard();
//        var curentBalance = dashboardPage.curentBalnce();
//        $("[data-test-id=error-message]").shouldHave(Condition.exactText("СУММА ПЕРЕВОДА НЕ ДОЛЖНА ПРЕВЫШАТЬ БАЛАНС КАРТЫ"));
//    }






}
