import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YandexSearhPage {

    protected WebDriver driver;

    @FindBy(xpath = "//span[text()='Производитель']")
    private WebElement manufacturerList;

    @FindBy(xpath = "//span/input[@id='glf-pricefrom-var']")
    private WebElement fromPriceInput;

    @FindBy(xpath = "//span[text()='Показать подходящие']")
    private WebElement showResults;

    public YandexSearhPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        driver.switchTo().defaultContent();
    }

    public void insertToFromPriceInputField(String text) {
        fromPriceInput.sendKeys(text);
        Assert.assertEquals(text, fromPriceInput.getAttribute("value"));
    }

    public void selectManufacturer(List<String> manufacturers) {
        try {
            for (String manufacturer : manufacturers) {
                driver.findElement(By.xpath("//label[text()='" + manufacturer + "']")).click();
            }
        } catch (NoSuchElementException | TimeoutException e1) {
            manufacturerList.click();
            for (String manufacturer : manufacturers) {
                driver.findElement(By.xpath("//label[text()='" + manufacturer + "']")).click();
            }
        }
    }

    public YandexPage showResultsForSelectedSearch() {
        showResults.click();
        return new YandexPage(driver);
    }
}
