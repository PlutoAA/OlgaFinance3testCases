package org.automation.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class TeamPage extends BaseLoggedInPage{

    private By teamPageTitle = By.className("TitleManagementPages_title__SH+NM");
    private By addMembersBtn = By.xpath("//span[text()='Add members']");
    private By addMembersMenu = By.className("ProfileLayout_paper__YBRAI");
    private By fullNameInput = By.xpath("//*[@id=\":rd:\"]");
    private By emailInput = By.xpath("//*[@id=\":re:\"]");
    private By roleInput = By.xpath("/html/body/div[2]/div[3]/div/div/div[4]/div[2]/div/label[2]/span/input");
    private By inviteBtn = By.className("AddMemberModal_inviteButton__4DUa8");

    public TeamPage(WebDriver driver) {
        super(driver);
    }

    public boolean isTeamPageOpened() {
        try {
            return waitVisibility(teamPageTitle).getText().equals("Team");
        } catch (NoSuchElementException e) {
            return false;
        }
    }
    @Step("Открытие окна добавление человека")
    public TeamPage openAddMemberMenu() {
        waitAndClick(addMembersBtn);
        waitVisibility(addMembersMenu);
        return this;
    }
    @Step("Ввод имени")
    public TeamPage typeFullName(String name) {
        waitAndClick(fullNameInput);
        driver.findElement(fullNameInput).sendKeys(name);
        return this;
    }
    @Step("Ввод email")
    public TeamPage typeEmail(String email) {
        waitAndClick(emailInput);
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }
    @Step("Выбор роли")
    public TeamPage selectRole(){
        driver.findElement(roleInput).click();
        return this;
    }
    @Step("Клик по кнопке invite")
    public TeamPage memberInviteClick() {
        driver.findElement(inviteBtn).click();
        return this;
    }
    @Step("Добавление человека")
    public TeamPage createMember(String name, String email) {
        openAddMemberMenu();
        typeFullName(name);
        typeEmail(email);
        selectRole();
        return memberInviteClick();
    }
}
