package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static ru.yandex.TestingLogIn.driver;

public class LogIn {
    @FindBy(xpath = "//*[contains(@id, 'passp-field-login')]")
    private WebElement loginBox;
    @FindBy(xpath = "//*[contains(@class, 'passp-button passp-sign-in-button')]")
    private WebElement loginBtn;
    @FindBy(xpath = "//*[contains(@id, 'passp-field-passwd')]")
    private WebElement passwdField;
    public WebDriver driver;

    public LogIn(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }
    @Step("Вставляем логин {login}")
    public void inputLogin(String login) {
        System.out.println(loginBox.getText());
        loginBox.sendKeys(login);
    }
    @Step("Вставляем пароль {passwd}")
    public void inputPasswd(String passwd) {
        passwdField.sendKeys(passwd);
    }

    @Step("Нажимаем кнопку 'Ввести'")
    public void clickLoginBtn() {
        loginBtn.click();
    }
}
