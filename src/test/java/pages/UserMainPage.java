package pages;

import data.ApplicationContext;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class UserMainPage extends Page {

    @FindBy(css = ".product-column .link")
    private WebElement productLink;

    @FindBy(css = ".product-column .name")
    private WebElement productInfo;

    @FindBy(id = "box-product")
    private WebElement productBox;

    @FindBy(css = "#box-product .title")
    private WebElement productTitle;

    @FindBy(className = "regular-price")
    private WebElement regularPrice;

    @FindBy(className = "campaign-price")
    private WebElement campaignPrice;

    @FindBy(css = "select[name='options[Size]']")
    private WebElement groupAttribute;

    @FindBy(className = "quantity")
    private WebElement badgeQuantity;

    @FindBy(css = "button[name='add_cart_product']")
    private WebElement addToCart;

    @FindBy(css = "#cart")
    private WebElement cart;

    @FindBy(css = "button[name='remove_cart_item']")
    private WebElement removeCartItem;

    @FindBy(xpath = "//*[text() = 'There are no items in your cart.']")
    private WebElement noItemsInCart;

    public UserMainPage(ApplicationContext apContext) {
        super(apContext);
        PageFactory.initElements(driver, this);
    }

    public void productOpen() {

        Assert.assertEquals("Product name doesn't match with expected result on main page","Yellow Duck", productInfo.getText());
        Assert.assertEquals("Product regular price doesn't match with expected result on main page", "$20", regularPrice.getText());
        Assert.assertEquals("Product campaign price doesn't match with expected result on main page", "$18", campaignPrice.getText());
        Assert.assertEquals("Color of product campaign price doesn't match with expected result on main page", "rgba(204, 0, 0, 1)", campaignPrice.getCssValue("color"));
        Assert.assertEquals("Product font doesn't match with font-weight on main page", "700", campaignPrice.getCssValue("font-weight"));
        Assert.assertEquals("Product text doesn't match with crossed out text on main page", "del", regularPrice.getTagName());

        productLink.click();

        wait.until(ExpectedConditions.visibilityOf(productBox));

        Assert.assertEquals("Product name doesn't match with expected result on product page","Yellow Duck", productTitle.getText());
        Assert.assertEquals("Product regular price doesn't match with expected result on product page", "$20", regularPrice.getText());
        Assert.assertEquals("Product campaign price doesn't match with expected result on product page", "$18", campaignPrice.getText());
        Assert.assertEquals("Color of product campaign price doesn't match with expected result on product page", "rgba(204, 0, 0, 1)", campaignPrice.getCssValue("color"));
        Assert.assertEquals("Product font doesn't match with font-weight on product page", "700", campaignPrice.getCssValue("font-weight"));
        Assert.assertEquals("Product text doesn't match with crossed out text on product page", "del", regularPrice.getTagName());
    }

    public void productAddToCart() {

        productLink.click();

        if(getSelectItems().size() > 0){
            wait.until(ExpectedConditions.elementToBeClickable(groupAttribute));
            new Select(groupAttribute).selectByValue("Small");
        }

        int quantity;
        if ((badgeQuantity.getText()).isEmpty()) {
            quantity = 0;
        } else {
            quantity = Integer.parseInt(badgeQuantity.getText());
        }

        addToCart.click();

        wait.until(ExpectedConditions.attributeToBe(badgeQuantity, "innerText", String.valueOf(++quantity)));
        Assert.assertEquals("Product was not added to cart", badgeQuantity, quantity);
    }

    public void productRemoveFromCart() {

        cart.click();

        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("button[name='remove_cart_item']")));
        int itemsInCart = getCartItems().size();
        for (int i = 0; i < itemsInCart; i++) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".table-bordered")));
            getCartItems().get(i).click();
        }

        wait.until(ExpectedConditions.visibilityOf(noItemsInCart));

        Assert.assertEquals("Products were not removed from the cart", "There are no items in your cart.", noItemsInCart.getText());
    }


    private List<WebElement> getCartItems() {
        return driver.findElements(By.cssSelector("button[name='remove_cart_item']"));
    }

    private List<WebElement> getSelectItems() {
        return driver.findElements(By.cssSelector("select"));
    }
}
