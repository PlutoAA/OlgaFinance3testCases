package org.automation.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.NoSuchElementException;

public class MainPage extends BaseLoggedInPage{
    private By teamPageLink = By.xpath("//p[text()='Team']");
    private By projectPageLink = By.xpath("//p[text()='Projects']");
    private By mainPageTitle = By.className("Planner_pageHeader__CpiXE");
    private By loginButton = By.className("Login_submitBtn__kyXUv");

    public MainPage(WebDriver driver) {
        super(driver);
    }
    @Step("Проверка кнопки логина на странице")
    public boolean isButtonHere() {
        try {
            return driver.findElement(loginButton).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @Step("Переход на страницу Team")
    public TeamPage openTeamPage(){
        waitAndClick(teamPageLink);
        return new TeamPage(driver);
    }
    @Step("Переход на страницу Projects")
    public ProjectPage openProjectPage(){
        waitAndClick(projectPageLink);
        return new ProjectPage(driver);
    }
}
