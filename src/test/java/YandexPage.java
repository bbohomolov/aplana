import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class YandexPage {

    protected WebDriver driver;
    private String YANDEX_URL = "https://yandex.ru/";
    private String PRODUCT;
    private List<WebElement> products;

    @FindBy(xpath = "//div[@class='home-arrow__tabs']//a[text()='Маркет']")
    private WebElement marketLink;

    @FindBy(xpath = "//noindex/ul[@class='topmenu__list']//a[text()='Электроника']")
    private WebElement electronicLink;

    @FindBy(xpath = "//div[@class='catalog-menu__list']/a[text()='Телевизоры']")
    private WebElement TVLink;

    @FindBy(xpath = "//div[@class='n-filter-panel-aside__show-more']/a")
    private WebElement expandedSearchLink;

    @FindBy(id = "header-search")
    private WebElement searchInputField;

    @FindBy(xpath = "//span[@class='search2__button']")
    private WebElement searchButton;

    @FindBy(xpath = "//div[@class='n-product-title']/div/h1")
    private WebElement foundedProductTitle;

    public YandexPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='catalog-menu__list']/a[contains(text(),'Наушники')]")
    private WebElement headPhonesLink;

    private void open(String url) {
        driver.get(url);
    }

    public void openBrowserAndExpandWindow() {
        driver.manage().window().maximize();
    }

    public void goToYandex() {
        open(YANDEX_URL);
    }

    public void goToYandexMarket() {
        marketLink.click();
    }

    public void goToElectronikCatalog() {
        try {
            electronicLink.click();
        } catch (NoSuchElementException | TimeoutException e1) {
            goToYandexMarket();
            electronicLink.click();
        }
    }

    public void goToTVCatalog() {
        try {
            TVLink.click();
        } catch (NoSuchElementException | TimeoutException e1) {
            goToElectronikCatalog();
            TVLink.click();
        }
    }

    public void goToHeadphonesCatalog() {
        try {
            headPhonesLink.click();
        } catch (NoSuchElementException | TimeoutException e1) {
            goToElectronikCatalog();
            headPhonesLink.click();
        }
    }

    public void checkTheCountOfProductsAtPage(int expected) {
        products = driver.findElements(By.xpath("//span[@class='snippet-card__header-text']"));
        Assert.assertEquals("Expected were " + expected + " elements, but found " + products.size(), expected, products.size());
    }

    public void rememberFirstElement() {
        PRODUCT = products.get(0).getText();
    }

    public void insertTheFirstElementToSearchInput() {
        searchInputField.sendKeys(PRODUCT);
        searchInputField.getText().equals(PRODUCT);
    }

    public void searchAndCheckResults() {
        searchButton.click();
        Assert.assertEquals("Expected was " + products.get(0).getText() + " product, but found " + foundedProductTitle.getText(), PRODUCT, foundedProductTitle.getText());
    }

    public YandexSearhPage goToYandexSearchPage() {
        expandedSearchLink.click();
        return new YandexSearhPage(driver);
    }

    public void close() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}

