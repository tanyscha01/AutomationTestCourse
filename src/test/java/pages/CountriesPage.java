package pages;

import data.ApplicationContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CountriesPage extends Page {

    @FindBy(css = ".panel-action .btn-default")
    private WebElement addNewCountryBtn;

    @FindBy(className = "form-group")
    private List<WebElement> formGroup;

    public CountriesPage(ApplicationContext apContext) {
        super(apContext);
        PageFactory.initElements(driver, this);
    }

    private List <String> getlinksNames() {
        List <String> linksNames = new ArrayList<>(9);
        linksNames.add("https://en.wikipedia.org/wiki/ISO_3166-1_numeric");
        linksNames.add("https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2");
        linksNames.add("https://en.wikipedia.org/wiki/ISO_3166-1_alpha-3");
        linksNames.add("https://en.wikipedia.org/wiki/Address");
        linksNames.add("https://en.wikipedia.org/wiki/Regular_expression");
        linksNames.add("https://en.wikipedia.org/wiki/Regular_expression");
        linksNames.add("https://en.wikipedia.org/wiki/List_of_ISO_639-1_codes");
        linksNames.add("https://en.wikipedia.org/wiki/List_of_countries_and_capitals_with_currency_and_language");
        linksNames.add("https://en.wikipedia.org/wiki/List_of_country_calling_codes");
        return linksNames;
    }

    public CountriesPage countriesPageOpen() {
        driver.get(baseUrl + "/admin/?app=countries&doc=countries");
        return this;
    }

    public void checkCountryLinks() {

        wait.until(ExpectedConditions.elementToBeClickable(addNewCountryBtn));
        addNewCountryBtn.click();

        List <WebElement> linkList = new ArrayList<>(9);

        for (int i = 0; i < formGroup.size(); i++) {
            try {
                linkList.add(formGroup.get(i).findElement(By.className("fa-external-link")));
            } catch (Exception e) {
            }
        }

        List <String> linksNames = getlinksNames();

        for (int i = 0; i < linkList.size(); i++) {

            String currentWindow = driver.getWindowHandle();

            linkList.get(i).click();

            boolean isChildWindowOpen = wait.until(ExpectedConditions.numberOfWindowsToBe(2));

            if(isChildWindowOpen){
                String newW = getWindowHandle(linksNames.get(i));
                driver.switchTo().window(newW);
                driver.close();
            }

            driver.switchTo().window(currentWindow);
        }
    }

    protected String getWindowHandle(String window) {
        String handleName = null;

        for (String handle : driver.getWindowHandles()) {
            driver = driver.switchTo().window(handle);
            if (driver.getTitle().equalsIgnoreCase(window)
                    || driver.getCurrentUrl().equalsIgnoreCase(window)
                    || driver.getCurrentUrl().contains("/" + window)) {
                handleName = handle;
            }
        }
        return handleName;
    }
}
