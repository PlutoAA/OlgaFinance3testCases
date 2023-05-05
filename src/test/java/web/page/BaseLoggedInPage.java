package web.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class BaseLoggedInPage extends BasePage{
    private By loggedInName = By.className("AvatarMenu_name__goGiL");
    private By profileAvatar = By.className("MemberAvatar_avatar__b6hCn");
    private By profileBox = By.className("AvatarMenu_paper__9-d3u");

    public BaseLoggedInPage(WebDriver driver) {
        super(driver);
    }
    @Step("Открытие окна профиля")
    public void OpenProfileBox(){
        waitAndClick(profileAvatar);
        waitVisibility(profileBox);
    }

    @Step("Проверка авторизации")
    public boolean isLoggedIn(String name){
        try {
            OpenProfileBox();
            return driver.findElement(loggedInName).getText().equals(name);
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
