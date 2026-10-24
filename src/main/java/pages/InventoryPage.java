package pages;

import com.fasterxml.jackson.databind.JsonNode;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.DataDriven;

import java.time.Duration;
import java.util.List;

public class InventoryPage {
    WebDriver driver;
    private final By cartLoc = By.className("shopping_cart_link");
    private final By productLoc = By.className("inventory_item");
    private final By linkedInIcon = By.cssSelector("a[data-test=\"social-linkedin\"]");
    private final By facebookIcon = By.cssSelector("a[data-test=\"social-facebook\"]");
    private final By xIcon = By.cssSelector("a[data-test=\"social-x\"]");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean pageTitle(){
        return driver.getTitle().contains("Swag Labs");
    }

    public boolean cartIconDisplayed(){
        return driver.findElement(cartLoc).isDisplayed();
    }

    public int productsNum(){
        return driver.findElements(productLoc).size();
    }


    public boolean iconClick(String icon){
        String originalWindow = driver.getWindowHandle();
        WebDriverWait waitIcon = new WebDriverWait(driver, Duration.ofSeconds(10));

        switch (icon.toLowerCase()){
            case "linkedin":
                waitIcon.until(ExpectedConditions.elementToBeClickable(linkedInIcon)).click();
                break;
            case "facebook":
                waitIcon.until(ExpectedConditions.elementToBeClickable(facebookIcon)).click();
                break;
            case "x":
                waitIcon.until(ExpectedConditions.elementToBeClickable(xIcon)).click();
                break;
            default:
                throw new IllegalArgumentException("Unknown Icon: " + icon);
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(d -> d.getWindowHandles().size() > 1);

        for (String window : driver.getWindowHandles()) {
            if (!window.equals(originalWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }

        String actualURL = driver.getCurrentUrl().toLowerCase();

        boolean result;

        switch (icon.toLowerCase()) {
            case "linkedin":
                result = actualURL.contains("linkedin");
                break;

            case "facebook":
                result = actualURL.contains("facebook");
                break;

            case "x":
                result = actualURL.contains("x.com");
                break;

            default:
                throw new IllegalArgumentException("Unknown Icon: " + icon);
        }

        driver.close();
        driver.switchTo().window(originalWindow);

        return result;
    }

    public void addSpecificProducts(){
        List<WebElement> products = driver.findElements(By.cssSelector("div[data-test=\"inventory-item-name\"]"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        JsonNode data = DataDriven.jsonReader();
        String backpackproduct = data.get("cartProducts").get(0).asText();
        String boltproduct = data.get("cartProducts").get(1).asText();
        String onesieproduct = data.get("cartProducts").get(2).asText();

        for(int i = 0; i < products.size(); i++){
            String productName = products.get(i).getText();
            if(productName.equalsIgnoreCase(backpackproduct)){
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[data-test=\"add-to-cart-sauce-labs-backpack\"]")))).click();
            } else if (productName.equalsIgnoreCase(boltproduct)) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[data-test=\"add-to-cart-sauce-labs-bolt-t-shirt\"]")))).click();
            } else if (productName.equalsIgnoreCase(onesieproduct)) {
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector("button[data-test=\"add-to-cart-sauce-labs-onesie\"]")))).click();
            }
        }
    }
}
