package ru.yandex;

import io.qameta.allure.Description;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestingLogIn {
    public static EmailPage emailPage;
    public static WebDriver driver;
    public static LogIn logIn;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
        driver.get("https://passport.yandex.ru/auth/welcome?from=mail&origin=hostroot_homer_auth_ru&retpath=https%3A%2F%2Fmail.yandex.ru%2F%3Fnoretpath%3D1&backpath=https%3A%2F%2Fmail.yandex.ru%3Fnoretpath%3D1");
        emailPage = new EmailPage(driver);
        logIn = new LogIn(driver);
    }

    @Test
    @Description(value = "Тест будет проверять правильность набора логина и пароля при вхождении на страницу почты")
    public void loginTest(){
        logIn.inputLogin("cme2me@yandex.ru");
        logIn.clickLoginBtn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
        logIn.inputPasswd("testing123123");
        logIn.clickLoginBtn();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[href=\"https://mail360.yandex.ru/?from=mail_promobutton\"]")));
        emailPage.writeEmail("max.zhakov@gmail.com", "Simbirsoft Тестовое задание.<<Жаков>>", String.valueOf(emailPage.getMailsNum()));
    }

    @AfterAll
    public static void Close(){
        driver.quit();
    }
}

