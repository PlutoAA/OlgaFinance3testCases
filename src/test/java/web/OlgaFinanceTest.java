package web;

import com.google.common.io.Resources;
import jdk.jfr.Description;
import org.automation.TestListener;
import org.automation.page.LoginPage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

@DisplayName("OlgaFinance Тест-кейсы")
@ExtendWith(TestListener.class)
public class OlgaFinanceTest {

    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ChromeOptions options;

    private static final String email = "admin@gmail.com";
    private static final String badEmail = "notvalid@gmail.com";

    private static final String password = "olga_finance";
    private static final String badPassword = "notvalid";
    private static final String name = "Admin11";
    private static final String memberName = "memberName";
    private static final String memberEmail = "memberEmail";


    @BeforeAll
    public static void setChromeDefaults(){
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver.exe").getPath());
        System.setProperty("webdriver.chrome.whitelistedIps", "");

        options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--start-maximized");
    }

    @BeforeEach
    public void createDriver(){

        driver.set(new ChromeDriver(options));
    }

//    @AfterEach
//    public void disposeDriver(){
//        if (driver != null){
//            driver.get().quit();
//        }
//    }

    @Test
    @DisplayName("Авторизация с неверными данными")
    @Description("Проверяет будет ли автризован пользователь с неверными данными, если пользователь не проходит авторизацию, тест считается выполненным")
    public void badLogin(){
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .typeEmail(badEmail)
                        .typePassword(badPassword)
                        .clickLogin()
                        .isButtonHere()
        );
    }

    @Test
    @DisplayName("Создание мембера")
    @Description("Добавляет пользователя и ищет его в списке всех пользователей, если пользователь найден, тест считается выполненным")
    public void createMember(){
                new LoginPage(driver.get())
                        .typeEmail(email)
                        .typePassword(password)
                        .clickLogin()
                        .openTeamPage()
                        .openAddMemberMenu()
                        .typeFullName(memberName)
                        .typeEmail(memberEmail)
                        .selectRole()
                        .memberInviteClick();

                        given().header("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VySWQiOiI4YzE3MTA5My1kMDUyLTRkNzktYjg0ZS04NDVhZDBiNWM1ODIiLCJkZXZpY2VJZCI6Ijg4NzIyNWU5LTdlY2EtNDgyNC1iZmRhLTZmYjZhNWQzYWQ5MCIsImV4cGlyZXMiOiIyMDIzLTA1LTEyVDE4OjQyOjE0LjE1NloifQ.MLOFItQZMpr4p6iEVAlQDj-VmeEXrOFm1SPMJj_pPEo")
                                .when()
                                .get("https://api.olga-finance.effective.band/profile/all")
                                .then()
                                .statusCode(200)
                                .assertThat().body("data.name", hasItems(memberName));
    }

    @Test
    @DisplayName("Пагинация на странице проектов")
    @Description("Переключает режим пагинации на странице проектов, если существует последний элемент указанной пагинации, тест считается выполненным")
    public void projectPagination() {
        Assertions.assertTrue(
            new LoginPage(driver.get())
                    .typeEmail(email)
                    .typePassword(password)
                    .clickLogin()
                    .openProjectPage()
                    .clickPaginationBtn()
                    .clickPagination15()
                    .isPagination15Displayed()
        );
    }
}
