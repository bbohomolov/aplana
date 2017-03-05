import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class YandexTest {

    protected YandexPage yandexPage;
    protected YandexSearhPage searhPage;
    protected Utils ut = new Utils();
    private List<String> TV_MANUFACTURERS = new ArrayList<String>();
    private List<String> HEADPHONES_MANUFACTURERS = new ArrayList<String>();

    @Before
    public void openYandexPage() {
        yandexPage = new YandexPage(ut.getFirefoxDriver());
        TV_MANUFACTURERS.add("Samsung");
        TV_MANUFACTURERS.add("LG");
        HEADPHONES_MANUFACTURERS.add("Beats");
    }

    @Test
    public void firstTestCase() {
        yandexPage.openBrowserAndExpandWindow();
        yandexPage.goToYandex();
        yandexPage.goToYandexMarket();
        yandexPage.goToElectronikCatalog();
        yandexPage.goToTVCatalog();
        searhPage = yandexPage.goToYandexSearchPage();
        searhPage.insertToFromPriceInputField("20000");
        searhPage.selectManufacturer(TV_MANUFACTURERS);
        yandexPage = searhPage.showResultsForSelectedSearch();
        yandexPage.checkTheCountOfProductsAtPage(10);
        yandexPage.rememberFirstElement();
        yandexPage.insertTheFirstElementToSearchInput();
        yandexPage.searchAndCheckResults();
    }

    @Test
    public void secondTestCase() {
        yandexPage.openBrowserAndExpandWindow();
        yandexPage.goToYandex();
        yandexPage.goToYandexMarket();
        yandexPage.goToElectronikCatalog();
        yandexPage.goToHeadphonesCatalog();
        searhPage = yandexPage.goToYandexSearchPage();
        searhPage.insertToFromPriceInputField("5000");
        searhPage.selectManufacturer(HEADPHONES_MANUFACTURERS);
        yandexPage = searhPage.showResultsForSelectedSearch();
        yandexPage.checkTheCountOfProductsAtPage(10);
        yandexPage.rememberFirstElement();
        yandexPage.insertTheFirstElementToSearchInput();
        yandexPage.searchAndCheckResults();
    }

    @After
    public void closeBrowser() {
        yandexPage.close();
    }
}
