package data;

import java.util.HashMap;
import java.util.Map;

public class Product {

    private Boolean enabled;
    private String dateValidFrom;
    private String dateValidTo;
    private String name;
    private String price;
    private String currencyCode;
    private String deliveryStatus;
    private String quantity;
    private String imageResource;
    private String catalog;
    private Integer attributeGroup;
    private Integer attributeValue;
    private Map<String, Integer> attributesGroup;
    private Map<String, Integer> attributesValue;

    public static Builder newEntity() {
        return new Product().new Builder();
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getDateValidFrom() {
        return dateValidFrom;
    }

    public String getDateValidTo() {
        return dateValidTo;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImageResource() {
        return imageResource;
    }

    public Integer getAttributeGroup() {
        return attributeGroup;
    }

    public Integer getAttributeValue() {
        return attributeValue;
    }

    public String getCatalog() {
        return catalog;
    }

    private void setAttributesGroup() {
        this.attributesGroup = new HashMap<String, Integer>() {{
            put("Color", 1);
            put("Size", 2);
        }};
    }

    private void setAttributesValue() {
        this.attributesValue = new HashMap<String, Integer>() {{
            put("Yellow", 1);
            put("Green", 2);
            put("Red", 3);
            put("Blue", 4);
        }};
    }

    public class Builder {
        private Builder() {
            setAttributesGroup();
            setAttributesValue();
        }

        public Builder withEnabled(Boolean enabled) {
            Product.this.enabled = enabled;
            return this;
        }

        public Builder withDateValidFrom(String dateValidFrom) {
            Product.this.dateValidFrom = dateValidFrom;
            return this;
        }

        public Builder withDateValidTo(String dateValidTo) {
            Product.this.dateValidTo = dateValidTo;
            return this;
        }

        public Builder withName(String name) {
            Product.this.name = name;
            return this;
        }

        public Builder withPrice(String price) {
            Product.this.price = price;
            return this;
        }

        public Builder withCurrencyCode(String currencyCode) {
            Product.this.currencyCode = currencyCode;
            return this;
        }

        public Builder withdeliveryStatus(String deliveryStatus) {
            Product.this.deliveryStatus = deliveryStatus;
            return this;
        }

        public Builder withQuantity(String quantity) {
            Product.this.quantity = quantity;
            return this;
        }

        public Builder withImageResource(String imageResource) {
            Product.this.imageResource = imageResource;
            return this;
        }

        public Builder withAttributeGroup(String attribute) {
            Integer attributeGroup = attributesGroup.get(attribute);
            Product.this.attributeGroup = attributeGroup;
            return this;
        }

        public Builder withAttributeValue(String attribute) {
            Integer attributeValue = attributesValue.get(attribute);
            Product.this.attributeValue = attributeValue;
            return this;
        }

        public Builder withCatalog(String catalog) {
            Product.this.catalog = catalog;
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }
}
