package pages;

import data.ApplicationContext;
import data.Product;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class AdminProductPage extends Page {

    @FindBy(xpath = "//*[text() = ' Add New Product']")
    private WebElement addNewProduct;

    @FindBy(xpath = "//*[text() = ' Enabled']")
    private WebElement enabledButton;

    @FindBy(name = "date_valid_from")
    private WebElement dateValidFrom;

    @FindBy(name = "date_valid_to")
    private WebElement dateValidTo;

    @FindBy(name = "name[en]")
    private WebElement productName;

    @FindBy(xpath = "//*[text() = 'Attributes']")
    private WebElement attributesTab;

    @FindBy(name = "new_images[]")
    private WebElement image;

    @FindBy(name = "new_attribute[group_id]")
    private WebElement groupAttribute;

    @FindBy(name = "new_attribute[value_id]")
    private WebElement valueAttribute;

    @FindBy(xpath = "//*[text() = 'Prices']")
    private WebElement pricesTab;

    @FindBy(name = "purchase_price")
    private WebElement price;

    @FindBy(css = "select[name='new_attribute[value_id]'] option")
    private WebElement valueAttributes;

    @FindBy(name = "add")
    private WebElement addAttribute;

    @FindBy(name = "save")
    private WebElement saveButton;

    @FindBy(xpath = "//*[text() = 'Stock']")
    private WebElement stockTab;

    @FindBy(name = "delivery_status_id")
    private WebElement deliveryStatus;

    @FindBy(name = "quantity")
    private WebElement quantity;

    @FindBy(name = "purchase_price_currency_code")
    private WebElement priceCode;

    public AdminProductPage(ApplicationContext apContext) {
        super(apContext);
        PageFactory.initElements(driver, this);
    }

    public AdminProductPage catalogListOpen() {
        driver.get(baseUrl + "/admin/?app=catalog&doc=catalog");
        return this;
    }

    public void catalogOpen(String catalog) {
        WebElement productCatalog = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(String.format("//*[text()='%s']", catalog))));
        productCatalog.click();
    }

    public void addNewProduct(Product product) {

        catalogOpen(product.getCatalog());
        addNewProduct.click();
        addGeneralInformation(product);
        addAttribute(product);
        addPrice(product);
        addStock(product);
        saveProduct();
        checkProductAdded(product);
    }

    private void addGeneralInformation(Product product){

        wait.until(ExpectedConditions.elementToBeClickable(enabledButton));
        enabledButton.click();
        dateValidFrom.sendKeys(product.getDateValidFrom());
        dateValidTo.sendKeys(product.getDateValidTo());
        productName.sendKeys(product.getName());

        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(product.getImageResource()).getFile());
        image.sendKeys(file.getAbsolutePath());
    }

    private void addAttribute(Product product){

        attributesTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(groupAttribute));
        new Select (groupAttribute).selectByIndex(product.getAttributeGroup());
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("select[name='new_attribute[value_id]'] option"), 5));
        wait.until(ExpectedConditions.elementToBeClickable(valueAttribute));
        new Select(valueAttribute).selectByIndex(product.getAttributeValue());
        addAttribute.click();
    }

    private void addPrice(Product product) {

        wait.until(ExpectedConditions.elementToBeClickable(pricesTab));
        pricesTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(price));
        price.sendKeys(product.getPrice());
        new Select(priceCode).selectByValue(product.getCurrencyCode());
    }

    private void addStock(Product product) {

        wait.until(ExpectedConditions.elementToBeClickable(stockTab));
        stockTab.click();
        wait.until(ExpectedConditions.elementToBeClickable(deliveryStatus));
        new Select (deliveryStatus).selectByValue(product.getDeliveryStatus());
        wait.until(ExpectedConditions.visibilityOf(quantity));
        quantity.sendKeys(product.getQuantity());
    }

    public void saveProduct() {
        wait.until(ExpectedConditions.elementToBeClickable(saveButton));
        saveButton.click();
    }

    public void checkProductAdded(Product product) {
        catalogOpen(product.getCatalog());
        Assert.assertNotNull("New product was not added to catalog", driver.findElement(By.xpath(String.format("//*[text()='%s']", product.getName()))));
    }

    public void logout() {
        //simulate logout by delete cookies
        driver.manage().deleteCookieNamed("LCSESSID");
        driver.navigate().refresh();
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name=login]")));
    }
}
