package application;

import data.AdminUser;
import data.ApplicationContext;
import data.Product;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class Application {

    private ApplicationContext applicationContext;

    private AdminLoginPage adminLoginPage;
    private AdminProductPage adminProductPage;
    private AdminMenuPage adminMenuPage;
    private UserMainPage userMainPage;
    private CountriesPage countriesPage;

    public Application() {

        WebDriverManager.chromedriver().setup();
        applicationContext = new ApplicationContext()
                .setDriver(new ChromeDriver())
                .setBaseUrl("http://localhost/litecart")
                .setAdminUser(new AdminUser("admin","admin"));

        adminLoginPage = new AdminLoginPage(applicationContext);
        adminMenuPage = new AdminMenuPage(applicationContext);
        adminProductPage = new AdminProductPage(applicationContext);
        userMainPage = new UserMainPage(applicationContext);
        countriesPage = new CountriesPage(applicationContext);
    }

    public void createNewProduct(Product product) {
        adminProductPage.catalogListOpen();
        adminProductPage.addNewProduct(product);
    }

    public void checkMenuItems() {
        adminMenuPage.checkMenuItems();
    }

    public void checkProductOpen() {
        userMainPage.productOpen();
    }

    public void checkCountryLinks() {
        countriesPage.countriesPageOpen();
        countriesPage.checkCountryLinks();
    }

    public void productAddToCart() {
        userMainPage.productAddToCart();
    }


    public void productRemoveFromCart() {
        userMainPage.productRemoveFromCart();
    }

    public void login() {
        if (adminLoginPage.open().isOnThisPage()) {
            adminLoginPage
                    .enterUsername(applicationContext.getAdmin().getLogin())
                    .enterPassword(applicationContext.getAdmin().getPassword())
                    .submitLogin();
        }
    }

    public void logout() {
        adminProductPage.logout();
    }

    public void quit() {
        applicationContext.getDriver().quit();
    }

    public void openMainApp() {
        applicationContext.getDriver().get(applicationContext.getBaseUrl());
    }

    public void openAdminPane() {
        adminLoginPage.open();
    }
}
