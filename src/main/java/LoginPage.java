import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By headlineTitle = By.xpath(".//*[@id='headingText']");
    private By inputFiled = By.xpath(".//*[@id='identifierId']");
    private By nextButton = By.xpath(".//*[@id='identifierNext']");
    private By loginError = By.xpath(".//*[@class='GQ8Pzc']");
    private By loginPageLocator = By.xpath(".//*[contains(text(),'Перейти в Gmail')]");

    void check(){
        wait = (new WebDriverWait(driver, 10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginPageLocator));
    }

    private void typeUserMail(String userMail) {
        driver.findElement(inputFiled).sendKeys(userMail);
    }

    private void clickNextButton(){
        driver.findElement(nextButton).click();
    }



    PasswordPage typeMailGoNext(String userMail) {
        this.typeUserMail(userMail);
        this.clickNextButton();
        return new PasswordPage(driver);
    }

    void loginWithInvaildLogin(String email){
        this.typeUserMail(email);
        driver.findElement(nextButton).click();
        new LoginPage(driver);
    }

    String getHeadlineTitle() {

        return driver.findElement(headlineTitle).getText();
    }

    String getLoginErrorText(){
        return driver.findElement(loginError).getText();
    }


}
