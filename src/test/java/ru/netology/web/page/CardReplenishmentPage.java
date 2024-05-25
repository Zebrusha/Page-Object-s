package ru.netology.web.page;

import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CardReplenishmentPage {
    public CardReplenishmentPage() {
    }

    public DashboardPage makeValidTransfer(String amountToTransfer, DataHelper.CardInfo cardInfo){
        makeTransfer(cardInfo, amountToTransfer);
        return new DashboardPage();
    }

    public void makeTransfer(DataHelper.CardInfo cardInfo,String amountToTransfer){
        $("[data-test-id=amount] input").setValue(amountToTransfer);
        $("[data-test-id=from] input").setValue(String.valueOf(cardInfo));
        $("[data-test-id=action-transfer]").click();
    }

    public void findErrorMessage(String expectedText){
        $("[data-test-id=error-message]").shouldHave(text(expectedText));
        $("[data-test-id=error-message]").shouldBe(visible);
    }
}
