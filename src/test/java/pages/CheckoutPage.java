package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class CheckoutPage {

    public CheckoutPage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(tagName = "h2")
    public WebElement checkoutPageHeading;

    @FindBy(className = "stripe-button-el")
    public WebElement payWithCardButton;

    @FindBy(id="email")
    public WebElement emailInput;

    @FindBy(id="card_number")
    public WebElement cardNumberInput;

    @FindBy(id="cc-exp")
    public WebElement expDateInput;

    @FindBy(id="cc-csc")
    public WebElement cvcInput;

    @FindBy(id="billing-zip")
    public WebElement zipCode;

    @FindBy(id="submitButton")
    public WebElement payButton;

}
