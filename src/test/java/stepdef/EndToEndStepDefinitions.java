package stepdef;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.*;
import pojos.UserCardGenerator;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EndToEndStepDefinitions {

    WebDriver driver = Driver.getDriver();
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    HomePage homePage = new HomePage();
    ProductsPageObjects commonObjects = new ProductsPageObjects();
    CheckoutPage checkoutPage = new CheckoutPage();

    int temperature;

    @Given("user is on the main page")
    public void userIsOnTheMainPage() {
        driver.get(ConfigReader.getProperty("main_page_url"));
    }

    @When("user checks the temperature and makes a choice accordingly")
    public void userChecksTheTemperatureAndMakesAChoiceAccordingly() {

        String temp = homePage.temperature.getText();
        temperature = Integer.parseInt(temp.split(" ")[0]);

        // navigate to following page according to the temperature
        if (temperature < 19) {
            homePage.buyMoisturizersButton.click();
        } else if (temperature > 34) {
            homePage.buySunscreensButton.click();
        } else {
            Driver.closeDriver();
        }

    }

    @And("user adds products considering the statements")
    public void userAddsProductsConsideringTheStatements() {

        List<WebElement> allProductsAddButtons = commonObjects.allProductsAddButtons;
        List<String> allProductsNames = ReusableMethods.convertWebElementListToStringList(commonObjects.allProductNames);
        List<String> allProductsPrices = ReusableMethods.convertWebElementListToStringList(commonObjects.allProductPrices);
        List<Integer> allPricesInt = new ArrayList<>();
        for (String w : allProductsPrices) {
            allPricesInt.add(ReusableMethods.convertPriceTextToInt(w));
        }

        if (temperature < 19) { // MOISTURIZERS

            // First, select the least expensive moisturizer that contains Aloe.
            List<Integer> aloePrices = new ArrayList<>();
            for (int i = 0; i < allProductsNames.size(); i++) {
                if (allProductsNames.get(i).contains("aloe")) {
                    aloePrices.add(allPricesInt.get(i));
                }
            }
            Collections.sort(aloePrices);
            int indexAloe = allPricesInt.indexOf(aloePrices.get(0));
            allProductsAddButtons.get(indexAloe).click();

            //For your second moisturizer, select the least expensive moisturizer that contains Almond
            List<Integer> almondPrices = new ArrayList<>();
            for (int i = 0; i < allProductsNames.size(); i++) {
                if (allProductsNames.get(i).contains("almond")) {
                    almondPrices.add(allPricesInt.get(i));
                }
            }
            Collections.sort(almondPrices);
            int indexAlmond = allPricesInt.indexOf(almondPrices.get(0));
            allProductsAddButtons.get(indexAlmond).click();

        } else if (temperature > 34) { // SUNSCREENS

            // First, select the least expensive sunscreen that is SPF-50.
            List<Integer> spf50Prices = new ArrayList<>();
            for (int i = 0; i < allProductsNames.size(); i++) {
                if (allProductsNames.get(i).contains("spf-50")) {
                    spf50Prices.add(allPricesInt.get(i));
                }
            }
            Collections.sort(spf50Prices);
            int indexSPF50 = allPricesInt.indexOf(spf50Prices.get(0));
            allProductsAddButtons.get(indexSPF50).click();

            // For your second sunscreen, select the least expensive sunscreen that is SPF-30
            List<Integer> spf30Prices = new ArrayList<>();
            for (int i = 0; i < allProductsNames.size(); i++) {
                if (allProductsNames.get(i).contains("spf-30")) {
                    spf30Prices.add(allPricesInt.get(i));
                }
            }
            Collections.sort(spf30Prices);
            int indexSPF30 = allPricesInt.indexOf(spf30Prices.get(0));
            allProductsAddButtons.get(indexSPF30).click();

        } else {
            System.err.println("An unexpected situation occurred!!!");
        }

    }

    @And("user navigates to cart and pays with credit card")
    public void userNavigatesToCartAndPaysWithCreditCard() {

        UserCardGenerator user = new UserCardGenerator();

        commonObjects.goToCartButton.click();
        checkoutPage.payWithCardButton.click();
        WebElement iframe = driver.findElement(By.name("stripe_checkout_app"));
        driver.switchTo().frame(iframe);
        checkoutPage.emailInput.sendKeys(user.getEmail());
        js.executeScript("document.getElementById('card_number').value='" + user.getCardNumber() + "'");
        js.executeScript("document.getElementById('cc-exp').value='" + user.getExpireDate() + "'");
        checkoutPage.cvcInput.sendKeys(user.getCvcNumber());

        try {
            checkoutPage.zipCode.sendKeys(user.getZipCode());
        } catch (Exception e) {
            System.out.println("ZIP Code section did not appear!!!");
        } finally {
            checkoutPage.payButton.click();
        }


    }

    @Then("user sees a success message is displayed")
    public void userSeesASuccessMessageIsDisplayed() {
        wait.until(ExpectedConditions.urlContains("confirmation"));
        driver.switchTo().defaultContent();
        Assert.assertEquals("PAYMENT SUCCESS", checkoutPage.checkoutPageHeading.getText());
        Driver.closeDriver();
    }
}
