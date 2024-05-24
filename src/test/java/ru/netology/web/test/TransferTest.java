package ru.netology.web.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.netology.web.page.LoginPageV1;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;
import ru.netology.web.page.VerificationPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TransferTest {


    @Test
    void shouldTransferMoneyBetweenOwnCardsV1(){
        open("http://localhost:9999");

        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

    }
    @Test
    void shouldViewBalanceTwoCards(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var idCard = DataHelper.getIdCard();
        var curentBalance = dashboardPage.curentBalnce(idCard);
    }
    @Test
    void shouldSelectingTheFirstMultipleForTransfer(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var buttonFirstCard = dashboardPage.clickFirstButton();

    }
    @Test
    void shouldSelectingTheSecondMultipleForTransfer(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var buttonFirstCard = dashboardPage.clickSecondButton();
    }
    @Test
    void shouldTransferToFirstCardWithTheEntered(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var buttonFirstCard = dashboardPage.clickSecondButton();

        var numberCard = DataHelper.numberFirstCard();
        var transfer = dashboardPage.transferToFirstCard("400", "5559 0000 0000 0002");
        var idCard = DataHelper.getIdCard();
        var curentBalance = dashboardPage.curentBalnce(idCard);
    }
    @Test
    void shouldTransferToSecondCardWithTheEntered(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var buttonFirstCard = dashboardPage.clickFirstButton();

        var numberCard = DataHelper.numberFirstCard();
        var transfer = dashboardPage.transferToSecondCard("400", "5559 0000 0000 0001");
        var idCard = DataHelper.getIdCard();
        var curentBalance = dashboardPage.curentBalnce(idCard);
    }

    @Test
    void transferOfALargerAmountThanTheBalance(){
        open("http://localhost:9999");
        var loginPage = new LoginPageV1();
        var authInfo = DataHelper.getAuthInfo();

        var verificationPage = loginPage.validLogin(authInfo);
        var verificayionCode = DataHelper.getVerificationCodeFor(authInfo);

        var verifyPage = new VerificationPage();
        var verifyCode = DataHelper.getVerificationCodeFor(authInfo);
        var curentVerifyCode = verifyPage.validfVerify(verifyCode);

        var dashboardPage = new DashboardPage();
        var buttonFirstCard = dashboardPage.clickSecondButton();

        var numberCard = DataHelper.numberFirstCard();
        var transfer = dashboardPage.transferToSecondCard("8000", "5559 0000 0000 0001");
        var idCard = DataHelper.getIdCard();
        var curentBalance = dashboardPage.curentBalnce(idCard);
        $("[data-test-id=error-message]").shouldHave(Condition.exactText("СУММА ПЕРЕВОДА НЕ ДОЛЖНА ПРЕВЫШАТЬ БАЛАНС КАРТЫ"));
    }






}
