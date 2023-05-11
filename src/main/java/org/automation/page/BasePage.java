package org.automation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected static final String BASE_URL = "https://olga-finance.effective.band/";
    private static final Duration DEFAULT_TIMEOUT = Duration.ofSeconds(15);

    public static WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, DEFAULT_TIMEOUT);
    }

    protected void waitAndClick(By locator){
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected WebElement waitVisibility(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

//    public void switchToAnotherTab(){
//        driver.switchTo().window(
//                driver.getWindowHandles().stream()
//                        .filter(h -> !h.equals(driver.getWindowHandle()))
//                        .findFirst().get()
//                );
//    }
}
