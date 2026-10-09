package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InventoryPage {
    WebDriver driver;
    private final By cartLoc = By.className("shopping_cart_link");
    private final By productLoc = By.className("inventory_item");

    public InventoryPage(WebDriver driver){
        this.driver = driver;
    }

    public boolean pageTitle(){
        return driver.getTitle().contains("Swag Labs");
    }

    public boolean cartIcon(){
        return driver.findElement(cartLoc).isDisplayed();
    }

    public int productsNum(){
        return driver.findElements(productLoc).size();
    }
}
