package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private static ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public DashboardPage() {
    }



    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        var text = cards.findBy(attribute("data-test-id", cardInfo.getTestId())).text();
        return extractBalance(text);
    }
    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

    public CardReplenishmentPage selectedCardToTransfer(DataHelper.CardInfo cardInfo){
        cards.findBy(attribute("data-test-id", cardInfo.getTestId())).$("button").click();
        return new CardReplenishmentPage();
    }


}
