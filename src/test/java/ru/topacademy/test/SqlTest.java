package ru.topacademy.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.topacademy.data.DataHelper;
import ru.topacademy.data.DataMySql;
import ru.topacademy.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.topacademy.data.DataMySql.cleanDatabase;

public class SqlTest {
    @AfterAll
    public static void tearDown() {
        cleanDatabase();
    }

    @Test
    public void successLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = DataMySql.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }

    @Test
    public void randomLoginError() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfoRandom();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisible();
    }

    @Test
    public void randomCodeError() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        verificationPage.verifyButtonClick("");
        verificationPage.errorNotificationVisible();
    }
}