package web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProjectPage extends BaseLoggedInPage{

    private By paginationBtn = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[2]/div/div/div[2]");
    private By paginationMenu = By.xpath("//*[@id=\"menu-\"]/div[3]/ul");
    private By pagination15 = By.xpath("//*[@id=\"menu-\"]/div[3]/ul/li[2]");
    private By lastPaginationElement = By.xpath("//*[@id=\"root\"]/div/div[2]/div[2]/div[2]/div/div[1]/table/tbody/tr[15]/td[1]");

    public ProjectPage(WebDriver driver) {
        super(driver);
    }

    @Step("Клик по кнопке пагинации")
    public ProjectPage clickPaginationBtn() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
        waitAndClick(paginationBtn);
        waitVisibility(paginationMenu);
        return this;
    }
    @Step("Клик по кнопке выбора пагинации - 15")
    public ProjectPage clickPagination15() {
        driver.findElement(pagination15).click();
        return this;
    }
    @Step("Вывод пагинации")
    public boolean isPagination15Displayed() {
        try {
            return driver.findElement(lastPaginationElement).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
