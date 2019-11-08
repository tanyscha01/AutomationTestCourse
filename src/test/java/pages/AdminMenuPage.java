package pages;

import data.ApplicationContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class AdminMenuPage extends Page {

    public AdminMenuPage(ApplicationContext apContext) {
        super(apContext);
        PageFactory.initElements(driver, this);
    }

    public void checkMenuItems(){

        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".name")));

        for (int i = 0; i < getMenuItems().size(); i++) {
            getMenuItems().get(i).click();
            Assert.assertTrue("Header is absent", isHeaderPresent());
            if (getSubMenuItems().size() > 0) {
                for (int j = 0; j < getSubMenuItems().size(); j++) {
                    getSubMenuItems().get(j).click();
                    Assert.assertTrue("Header is absent", isHeaderPresent());
                }
            }
        }
    }

    private boolean isHeaderPresent() {
        return driver.findElements((By.cssSelector(".panel-heading"))).size() > 0;
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.cssSelector("li.app"));
    }

    private List<WebElement> getSubMenuItems() {
        return driver.findElements(By.cssSelector("li.doc"));
    }
}
