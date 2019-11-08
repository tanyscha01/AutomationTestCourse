package pages;

import data.ApplicationContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AdminLoginPage extends Page {

    public AdminLoginPage(ApplicationContext apContext) {
        super(apContext);
    }

    public AdminLoginPage open() {
        driver.get(baseUrl + "/admin");

        if (isOnThisPage()) {
            this
                    .enterUsername(appContext.getAdmin().getLogin())
                    .enterPassword(appContext.getAdmin().getPassword())
                    .submitLogin();
        }

        return this;
    }

    public boolean isOnThisPage() {
        return driver.findElements(By.id("box-login")).size() > 0;
    }

    public AdminLoginPage enterUsername(String username) {
        driver.findElement(By.name("username")).sendKeys(username);
        return this;
    }

    public AdminLoginPage enterPassword(String username) {
        driver.findElement(By.name("password")).sendKeys(username);
        return this;
    }

    public void submitLogin() {
        wait.until(ExpectedConditions.elementToBeClickable(By.className("btn-default"))).click();
        wait.until((WebDriver d) -> d.findElement(By.id("box-apps-menu")));
    }
}