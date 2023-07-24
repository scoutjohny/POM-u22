package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;

import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BasePage {
    WebDriver driver;
    WebDriverWait webDriverWait;
    int maxRetries = 3;
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "[data-label='Naslovna']")
    WebElement homeButton;

    //ako imamo selektor koji vraća više elemenata a nama treba neki od njih (recimo drugi)
//    @FindBy(css = "[data1-label='Naslovna']")
//    List<WebElement> elementi; //lista sadrži sve veb elemente
//
//    public void selectSpecific(){
//        elementi.get(1).click(); //.get(redni broj elementa) vraća baš taj element - broji od nule!!!
//    }
    //bez @FindBy-a
//    public void selectSpecific(){
//        driver.findElements(By.cssSelector("[data1-label='Naslovna']")).get(1).click(); //.get(redni broj elementa) vraća baš taj element - broji od nule!!!
//    }
    public void goToHomePage(){
        homeButton.click();
    }
//    public void goToHomePage(){
//        driver.findElement(By.cssSelector("[data-label='Naslovna']")).click();
//    }

    public void click (WebElement element){
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();

        element.click();
    }
    BaseTest baseTest = new BaseTest();
    public void click (WebElement element, String log) throws Exception {
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(15));
        webDriverWait.until(ExpectedConditions.visibilityOf(element));
        webDriverWait.until(ExpectedConditions.elementToBeClickable(element));

        int retryCount = 0;
        while (retryCount<maxRetries){
            try{
                Actions actions = new Actions(driver);
                actions.moveToElement(element).build().perform();

                element.click();
                System.out.println(getCurrentTimeDate()+" Clicked: " + log);
                break;
            }catch (Exception e){
                retryCount++;
                System.out.println("Retry: "+retryCount+ " to click on the " + log);
                if(retryCount==maxRetries){
                    baseTest.reportScreenshot("failedClick","Failed to click");
                    throw new Exception(getCurrentTimeDate()+" Failed to click element: "+log);
                }
            }
        }
    }

    public String getCurrentTimeDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
