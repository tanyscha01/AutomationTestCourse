package pages;

import data.ApplicationContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl;
    protected ApplicationContext appContext;

    public Page(ApplicationContext apContext) {
        appContext = apContext;

        this.driver = appContext.getDriver();
        wait = new WebDriverWait(driver, 10);
        this.baseUrl = appContext.getBaseUrl();
    }
}
