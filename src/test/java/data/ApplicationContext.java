package data;

import org.openqa.selenium.WebDriver;

public class ApplicationContext {

    private WebDriver driver;
    private String baseUrl;
    private AdminUser admin;

    public ApplicationContext(WebDriver driver) {
        this.driver = driver;
    }

    public ApplicationContext() {
    }

    public WebDriver getDriver() {
        return driver;
    }

    public ApplicationContext setDriver(WebDriver driver) {
        this.driver = driver;
        return this;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public ApplicationContext setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        return this;
    }

    public ApplicationContext setAdminUser(AdminUser user) {
        admin = user;
        return this;
    }

    public AdminUser getAdmin() {
        return admin;
    }
}
