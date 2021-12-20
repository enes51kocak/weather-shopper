package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class HomePage {

   public HomePage(){
       PageFactory.initElements(Driver.getDriver(), this);
   }

    @FindBy(id = "temperature")
    public WebElement temperature;

    @FindBy(xpath = "//button[contains(.,'moisturizers')]")
    public WebElement buyMoisturizersButton;

    @FindBy(xpath = "//button[contains(.,'sunscreens')]")
    public WebElement buySunscreensButton;

}