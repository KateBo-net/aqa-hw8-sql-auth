package test;

import data.*;
import org.junit.jupiter.api.*;
import page.*;

import static com.codeborne.selenide.Selenide.*;
import static data.DataHelper.*;
import static data.SQLHelper.*;

public class AuthTest {

    LoginPage loginPage;
    private final String incorrectAuthMsg = "Неверно указан логин или пароль";
    private final String incorrectCodeMsg = "Неверно указан код! Попробуйте ещё раз";
    private final String limitMsg = "Ошибка!";

    @BeforeEach
    public void setup() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }

    @AfterEach
    public void after() {
        cleanVerCodeTable();
    }

    @AfterAll
    public static void tearDownAll() {
        cleanDB();
    }

    @Test
    public void shouldLogin() {
        AuthInfo user = getValidAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(user);
        String verificationCode = getVerificationCode();
        verificationPage.validVerify(verificationCode);
    }

    @Test
    public void shouldGetErrorInvalidLogin() {
        loginPage.invalidLogin(generateRandomAuthInfo(), incorrectAuthMsg);
    }

    @Test
    public void shouldGetErrorInvalidCode() {
        AuthInfo user = getValidAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(user);
        String verificationCode = getRandomVerificationCode();
        verificationPage.invalidVerify(verificationCode, incorrectCodeMsg);
    }

    @Test
    public void shouldBlockedAuthorize() {
        AuthInfo user = getValidAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(user);
        back();
        loginPage.validLogin(user);
        back();
        loginPage.validLogin(user);
        back();
        loginPage.validLogin(user);
        String verificationCode = getVerificationCode();
        verificationPage.overLimitVerify(verificationCode, limitMsg);
    }
}
