package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement codeField = $("[data-test-id=code] input");
    private final SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private final SelenideElement errorMsg = $("[data-test-id=error-notification]");
    private final String textMsg = "Неверно указан код! Попробуйте ещё раз";
    private final String limitMsg = "Ошибка!";

    public VerificationPage() {
        codeField.shouldBe(visible);
    }

    private void verify(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }

    public DashboardPage validVerify(String verificationCode) {
        verify(verificationCode);
        return new DashboardPage();
    }

    public void invalidVerify(String verificationCode) {
        verify(verificationCode);
        errorMsg
                .shouldBe(visible)
                .shouldHave(text(textMsg));
    }

    public void overLimitVerify(String verificationCode) {
        verify(verificationCode);
        errorMsg
                .shouldBe(visible)
                .shouldHave(text(limitMsg));
    }
}
