package data;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class ProductDataProvider {

    @DataProvider
    public static Object[][] validProducts() {
        return new Object[][]{
                {Product.newEntity()
                        .withEnabled(true)
                        .withName("NewToy" + System.currentTimeMillis())
                        .withPrice("5")
                        .withCurrencyCode("USD")
                        .withDateValidFrom("01/01/2019")
                        .withDateValidTo("01/01/2020")
                        .withdeliveryStatus("1")
                        .withQuantity("10")
                        .withCatalog("Rubber Ducks")
                        .withImageResource("frog.jpg")
                        .withAttributeGroup("Color")
                        .withAttributeValue("Green").build()}
        };
    }
}
