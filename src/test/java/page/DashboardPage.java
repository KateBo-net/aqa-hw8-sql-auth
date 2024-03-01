package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement dashboardHeader = $("[data-test-id=dashboard]");
    private final String textMsg = "Личный кабинет";

    public DashboardPage() {
        dashboardHeader
                .shouldBe(visible)
                .shouldHave(text(textMsg));
    }
}
