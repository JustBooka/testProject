import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private MainPage mainPage;
    private LoginPage loginPage;
    private PasswordPage passwordPage;
    private InboxPage inboxPage;
    private static WebDriver driver;

    private String userMail = "playingsognem";
    private String mainPageTile = "Успевайте больше с почтой Gmail";
    private String loginPageTile = "Вход";
    private String userName = "Добро пожаловать!";
    private String userPassword = "awedxzs123";
    private String profileEmail = "playingsognem@gmail.com";
    private String unsortedText = "Несортированные";
    private String wrongLoginError = "Не удалось найти аккаунт Google";
    private String emptyLoginError = "Введите адрес электронной почты или номер телефона.";
    private String wrongPasswordError = "Неверный пароль. Повторите попытку или нажмите на ссылку \"Забыли пароль?\", чтобы сбросить его.";
    private String emptyPasswordError = "Введите пароль";

    @Before
    public void SetUp(){
        System.setProperty("webdriver.chrome.driver", "//Users/alexey.bukin/Downloads/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://www.google.com/intl/ru/gmail/about/#");
        mainPage = new MainPage(driver);

    }


    @Test
    public void loginTest(){
        mainPage.check();
        Assert.assertEquals("Заголовок главной страницы не совпадает", mainPage.getHeadLineTitleText(), mainPageTile);
        mainPage.clickSignInButton();
        switchWindow();
        loginPage = new LoginPage(driver);
        loginPage.check();
        Assert.assertEquals("Заголовок страницы логина отображается некорректно ", loginPage.getHeadlineTitle(), loginPageTile);
        passwordPage = loginPage.typeMailGoNext(userMail);
        passwordPage = new PasswordPage(driver);
        passwordPage.check();
        Assert.assertEquals("Имя пользвоателя не совпадает с указаны", passwordPage.getHeadLineTitle(), userName);
        Assert.assertEquals("Email пользователя не совпадает с указанным", passwordPage.getProfileEmail(), profileEmail);
        inboxPage = passwordPage.typePasswordGoNext(userPassword);
        inboxPage.check();
        Assert.assertEquals("неправыильно отображается неправильно отображается название таба 'Несортированные'", inboxPage.getUnsortedTabText(), unsortedText);
    }


    @Test
    public void wrongLoginTest() {
        Assert.assertEquals("Заголовок главной страницы не совпадает", mainPage.getHeadLineTitleText(), mainPageTile);
        mainPage.clickSignInButton();
        switchWindow();
        loginPage = new LoginPage(driver);
        loginPage.check();
        Assert.assertEquals("Заголовок страницы логина отображается некорректно ", loginPage.getHeadlineTitle(), loginPageTile);
        loginPage.loginWithInvaildLogin("fail");
        Assert.assertEquals("Отображается неправильная ошибка при указании неверного логина", loginPage.getLoginErrorText(), wrongLoginError);
    }

    @Test
    public void emptyLoginTest() {
        Assert.assertEquals("Заголовок главной страницы не совпадает", mainPage.getHeadLineTitleText(), mainPageTile);
        mainPage.clickSignInButton();
        switchWindow();
        loginPage = new LoginPage(driver);
        loginPage.check();
        Assert.assertEquals("Заголовок страницы логина отображается некорректно ", loginPage.getHeadlineTitle(), loginPageTile);
        loginPage.loginWithInvaildLogin("");
        Assert.assertEquals("Имя  пользвоателя не совпадает с указаны", loginPage.getLoginErrorText(), emptyLoginError);
    }

    @Test
    public void loginWithWrongPasswordTest(){
        Assert.assertEquals("Заголовок главной страницы не совпадает", mainPage.getHeadLineTitleText(), mainPageTile);
        mainPage.clickSignInButton();
        switchWindow();
        loginPage = new LoginPage(driver);
        loginPage.check();
        Assert.assertEquals("Заголовок страницы логина отображается некорректно ", loginPage.getHeadlineTitle(), loginPageTile);
        passwordPage = loginPage.typeMailGoNext(userMail);
        passwordPage = new PasswordPage(driver);
        passwordPage.check();
        Assert.assertEquals("Имя пользвоателя не совпадает с указаны", passwordPage.getHeadLineTitle(), userName);
        passwordPage.goNextWithInvaildPassword("password");
        passwordPage.errorWait();
        Assert.assertEquals("Отображается неправильная ошибка", passwordPage.getPasswordErrorText(), wrongPasswordError);
    }

    @Test
    public void loginWithEmptyPasswordTest(){
        Assert.assertEquals("Заголовок главной страницы не совпадает", mainPage.getHeadLineTitleText(), mainPageTile);
        mainPage.clickSignInButton();
        switchWindow();
        loginPage = new LoginPage(driver);
        loginPage.check();
        Assert.assertEquals("Заголовок страницы логина отображается некорректно ", loginPage.getHeadlineTitle(), loginPageTile);
        passwordPage = loginPage.typeMailGoNext(userMail);
        passwordPage = new PasswordPage(driver);
        passwordPage.check();
        Assert.assertEquals("Имя пользвоателя не совпадает с указаны", passwordPage.getHeadLineTitle(), userName);
        passwordPage.goNextWithInvaildPassword("");
        passwordPage.errorWait();
        Assert.assertEquals("Отображается неправильная ошибка", passwordPage.getPasswordErrorText(), emptyPasswordError);
    }


    private void switchWindow(){
        for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }
    }

    @After
    public void TearDown(){
        driver.quit();
    }
}
