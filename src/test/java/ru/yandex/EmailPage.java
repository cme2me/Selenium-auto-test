package ru.yandex;

import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class EmailPage {
    @FindBy(xpath = "//*[contains(@class, 'mail-ComposeButton')]")
    private WebElement writeButton;
    @FindBy(xpath = "//*[contains(@class, 'composeYabbles')]")
    private WebElement addressBoard;
    @FindBy(xpath = "//*[contains(@class, 'composeTextField ComposeSubject-TextField')]")
    private WebElement subjBoard;
    @FindBy(xpath = "//*[contains(@class, 'cke_wysiwyg_div cke_reset')]")
    private WebElement contentBoard;
    @FindBy(xpath = "//*[contains(@class, 'Button2 Button2_pin_circle-circle Button2_view_default Button2_size_l')]")
    private WebElement sendBnt;
    public WebDriver driver;

    public EmailPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        }

    public int getMailsNum(){
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@class, 'mail-MessageSnippet-FromText')]")));
        List<WebElement> Emails = driver.findElements(By.xpath("//*[contains(@title, 'max.zhakov@gmail.com')]"));
        return Emails.size();
    }

    @Attachment("Нажимаем кнопку 'Написать'")
    public void writeBtn(){
            writeButton.click();
        }
    @Attachment("Нажимаем кнопку 'Отправить")
    public void sentBtn(){
            sendBnt.click();
        }
    @Attachment("Вставляем адрес {mailAdress}")
    public void inputAdress(String mailAdress){
        addressBoard.sendKeys(mailAdress);
    }
    @Attachment("Вставляем тему письма {subject}")
    public void inputSubj(String subject){
            subjBoard.sendKeys(subject);
        }
    @Attachment("Вставка содержимого")
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
