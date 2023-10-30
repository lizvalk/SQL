package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanAuthCodes;
import static ru.netology.data.SQLHelper.cleanDataBase;

public class LoginTest {
    LoginPage loginPage;
    @AfterEach
    void tearDown() {
        cleanAuthCodes();
    }
    @AfterAll
    static void tearDownAll() {
        cleanDataBase();
    }
    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
    }
    @Test
    void shouldSuccessfulLogin() {
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        verificationPage.verifyVerificationPageVisible();
        var verificationCode = SQLHelper.getVerificationCode();
        verificationPage.validVerify(verificationCode.getCode());
    }
    @Test
    void shouldErrorNotificationIfUsedRandomVerificationCode() {

    }

}
