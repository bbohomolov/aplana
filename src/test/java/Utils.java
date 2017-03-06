import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Utils {
    //The point of this method is to make configuration of system more automatic
    public WebDriver getFirefoxDriver() {
        String os = System.getProperty("os.name").toLowerCase();
        String arch = System.getProperty("os.arch").toLowerCase();
        String path = "";

        if (os.contains("linux")) path = "src/test/resources/linux_" + arch;
        else if (os.contains("windows")) path = "src/test/resources/windows_x86_x64.exe";
        else path = "src/test/resources/mac";

        System.setProperty("webdriver.gecko.driver", path);
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }
}
