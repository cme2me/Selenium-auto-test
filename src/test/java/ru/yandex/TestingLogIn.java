package ru.yandex;


import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestingLogIn {
    public static WebDriver driver;
    WebDriverWait wait = new WebDriverWait(driver, 10);

    @BeforeClass
    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\cme2me\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    @Test
    @Description(value = "Тест будет проверять правильность набора логина и пароля при вхождении на страницу почты")
    public void loginTest(){
        driver.get("https://mail.yandex.ru/");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Яндекс.Почта — бесплатная и надежная электронная почта"));
        WebElement signIn = driver.findElement(By.cssSelector("[href=\"https://passport.yandex.ru/auth?from=mail&origin=hostroot_homer_auth_ru&retpath=https%3A%2F%2Fmail.yandex.ru%2F&backpath=https%3A%2F%2Fmail.yandex.ru%3Fnoretpath%3D1\"]"));
        signIn.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp:exp-register")));
        driver.findElement(By.id("passp-field-login")).sendKeys("cme2me@yandex.ru");
        driver.findElement(By.id("passp:sign-in")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));
        driver.findElement(By.id("password-toggle")).click();
        driver.findElement(By.id("passp-field-passwd")).sendKeys("testing123123");
        driver.findElement(By.id("passp:sign-in")).click();
    }
}
