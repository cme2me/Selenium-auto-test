package ru.yandex;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class EmailPage {

        @FindBy(xpath = "[mail-ComposeButton js-main-action-compose)]")
        private WebElement writeButton;
        @FindBy(xpath = "[contains(@class, 'composeYabbles')]")
        private WebElement addressBoard;
        @FindBy(xpath = "[contains(@class, 'composeTextField ComposeSubject-TextField')]")
        private WebElement subjBoard;
        @FindBy(xpath = "[contains(@class, 'cke_wysiwyg_div cke_reset')]")
        private WebElement contentBoard;
        @FindBy(xpath = "[contains(@class, 'ComposeControlPanelButton-Button_action')]")
        private WebElement sendBnt;
        public WebDriver driver;

        public EmailPage(WebDriver driver) {
            PageFactory.initElements(driver, this);
            this.driver = driver;
        }

        public int getMailsNum(){
            WebDriverWait wait = new WebDriverWait(driver,3);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(), '<<Simbirsoft Тестовое задание>>')]")));

            List<WebElement> Emails = driver.findElements(By.xpath("//*[contains(text(), '<<Simbirsoft Тестовое задание>>')]"));
            return Emails.size();
        }

        @Step("Нажимаем кнопку 'Написать'")
        public void writeBtn(){
            writeButton.click();
        }
        @Step("Нажимаем кнопку'Отправить")
        public void sentBtn(){
            sendBnt.click();
        }
        @Step("Вставляем адрес {addressBoard}")
        public void inputAdress(String mailAdress){
            addressBoard.sendKeys(mailAdress);
        }
        @Step("Вставляем тему письма {subjBoard}")
        public void inputSubj(String subject){
            subjBoard.sendKeys(subject);
        }
        @Step("Вставка содержимого")
        public void inputContent(String content){
            contentBoard.sendKeys(content);
        }

        public void writeEmail(String mailAdress, String subject, String content) {
            writeBtn();
            inputAdress(mailAdress);
            inputSubj(subject);
            inputContent(content);
            sentBtn();
        }
}
