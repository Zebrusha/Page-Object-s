package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static ElementsCollection cards = $$(".list__item div");
    private static ElementsCollection topUpButtons = $$(".button_view_extra");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
    }



    public int getCardBalance(String id) {
        var text = cards.findBy(attribute("data-test-id", id)).text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
    public DashboardPage curentBalnce(DataHelper.idCard info){
        cards.first().shouldBe(visible);
        cards.last().shouldBe(visible);
        cards.first().shouldHave(exactText("**** **** **** 0001, баланс: " + getCardBalance(String.valueOf(info.getIdFirstCard())) + " р.\n" +
                "Пополнить")) ;
        cards.last().shouldHave(exactText("**** **** **** 0002, баланс: " + getCardBalance(String.valueOf(info.getIdSecondCard())) + " р.\n" +
                "Пополнить"));
        return new DashboardPage();
    }




    public DashboardPage clickFirstButton(){
        topUpButtons.first().click();
        return new DashboardPage();
    }
    public DashboardPage clickSecondButton(){
        topUpButtons.last().click();
        return new DashboardPage();
    }
    public DashboardPage transferToFirstCard(String sum,String fromCard){
        $("[data-test-id=amount] input").setValue(sum);
        $("[data-test-id=from] input").setValue(fromCard);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }
    public DashboardPage transferToSecondCard( String sum,String fromCard){
        $("[data-test-id=amount] input").setValue(sum);
        $("[data-test-id=from] input").setValue(fromCard);
        $("[data-test-id=action-transfer]").click();
        return new DashboardPage();
    }

}
