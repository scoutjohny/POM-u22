package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends  BasePage{
    WebDriver driver;
    By brands = By.cssSelector("[title=' Sve marke']");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectBrands (String brand) {
        driver.findElement(this.brands).sendKeys("Alfa Romeo");
    }

}
