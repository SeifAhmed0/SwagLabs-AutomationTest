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

public class CartPage {
    WebDriver driver;
    private final By cartLoc = By.className("shopping_cart_link");
    private final By yourCartLoc = By.cssSelector("span[data-test=\"title\"]");


    public CartPage(WebDriver driver){
        this.driver = driver;
    }

    public void cartClick() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cartLoc)).click();
    }

    public boolean yourCartDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(yourCartLoc)).isDisplayed();
    }

    public boolean isEmptyCart(){
        List<WebElement> products = driver.findElements(By.cssSelector("div[data-test=\"inventory-item-name\"]"));
        if (products.size() == 0){
            return true;
        }
        return false;
    }

    public boolean addSpecificProducts(){
        List<WebElement> specificproducts = driver.findElements(By.cssSelector("div[data-test=\"inventory-item-name\"]"));
        JsonNode data = DataDriven.jsonReader();
        String backpackproduct = data.get("cartProducts").get(0).asText();
        String boltproduct = data.get("cartProducts").get(1).asText();
        String onesieproduct = data.get("cartProducts").get(2).asText();

        if(specificproducts.get(0).getText().equalsIgnoreCase(backpackproduct) && specificproducts.get(1).getText().equalsIgnoreCase(boltproduct) && specificproducts.get(2).getText().equalsIgnoreCase(onesieproduct)){
            return true;
        }else {
            return false;
        }
    }
}
