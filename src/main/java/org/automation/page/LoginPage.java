package org.automation.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By loginButton = By.className("Login_submitBtn__kyXUv");

    public LoginPage(WebDriver driver) {
        super(driver);
        driver.get(BASE_URL);
    }

    @Step("Ввод email")
    public LoginPage typeEmail(String email){
        driver.findElement(emailInput).sendKeys(email);
        return this;
    }
    @Step("Ввод пароля")
    public LoginPage typePassword(String password){
        driver.findElement(passwordInput).sendKeys(password);
        return this;
    }
    @Step("Нажать на кнопку Lets go")
    public MainPage clickLogin(){
        driver.findElement(loginButton).click();
        return new MainPage(driver);
    }

}
