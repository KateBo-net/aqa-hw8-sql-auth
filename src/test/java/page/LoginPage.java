package page;

import com.codeborne.selenide.SelenideElement;
import data.AuthInfo;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement loginField = $("[data-test-id=login] input");
    private final SelenideElement passwordField = $("[data-test-id=password] input");
    private final SelenideElement sendButton = $("[data-test-id=action-login]");
    private final SelenideElement errorMsg = $("[data-test-id=error-notification]");

    private void login(AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        sendButton.click();

    }

    public VerificationPage validLogin(AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void invalidLogin(AuthInfo info, String textMsg) {
        login(info);
        errorMsg
                .shouldBe(visible)
                .shouldHave(text(textMsg));
    }

}
