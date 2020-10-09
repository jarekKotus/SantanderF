package utils;

import org.openqa.selenium.WebElement;

public class Actions {
    public void clickElement(WebElement element){
        element.click();
    }
    public void sendKeysToElement(WebElement element, String inputText){
        element.clear();
        element.sendKeys(inputText);
    }
}
