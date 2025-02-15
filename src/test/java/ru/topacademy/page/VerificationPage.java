package ru.topacademy.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private SelenideElement codeField = $("[data-test-id=code] input");
    private SelenideElement verifyButton = $("[data-test-id=action-verify]");
    private SelenideElement errorNotification = $("[data-test-id=code] .input__sub");

    public void verifyVerificationPageVisible() { codeField.shouldBe(visible); }
    public void errorNotificationVisible() { errorNotification.shouldBe(visible); }

    public DashboardPage validVerify(String verificationCode) {
        verifyButtonClick(verificationCode);
        return new DashboardPage();
    }

    public void verifyButtonClick(String verificationCode) {
        codeField.setValue(verificationCode);
        verifyButton.click();
    }
}