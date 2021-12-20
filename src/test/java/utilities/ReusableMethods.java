package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ReusableMethods {

    public static String switchToNextWindowAndReturnWindowHandle() {
        WebDriver driver = Driver.getDriver();
        String currentPageWindowHandle = driver.getWindowHandle();
        Set<String> allWindowHandles = driver.getWindowHandles();
        for (String w : allWindowHandles) {
            if (!w.equals(currentPageWindowHandle)) {
                driver.switchTo().window(w);
            }
        }
        return driver.getWindowHandle();
    }

    /**
     * @param list of WebElement
     * @return the list of String which has been converted from WebElement text
     */
    public static List<String> convertWebElementListToStringList(List<WebElement> list){
        return list.stream().map(t -> t.getAttribute("innerText")).map(String::toLowerCase).collect(Collectors.toList());
    }

    /**
     * @param priceText is a price description as this 'Price: Rs. 128'
     * @return price as int which indicates only '128'
     */
    public static int convertPriceTextToInt(String priceText){
        String[] arr = priceText.split(" ");
        return Integer.parseInt(arr[arr.length-1]);
    }

}
