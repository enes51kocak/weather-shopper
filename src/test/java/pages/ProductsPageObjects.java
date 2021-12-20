package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class ProductsPageObjects {

    public ProductsPageObjects(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//p[contains(@class, 'top-space-10')]")
    public List<WebElement> allProductNames;

    @FindBy(xpath = "//p[contains(text(), 'Price')]")
    public List<WebElement> allProductPrices;

    @FindBy(xpath = "//button[contains(@onclick, 'addToCart')]")
    public List<WebElement> allProductsAddButtons;

    @FindBy(xpath = "//button[contains(@onclick, 'goToCart')]")
    public WebElement goToCartButton;

}
