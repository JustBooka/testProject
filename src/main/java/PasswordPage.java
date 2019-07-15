import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class PasswordPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    PasswordPage(WebDriver driver){
        PasswordPage.driver = driver;
    }

    private static By headlineTitle = By.xpath(".//*[@id='headingText']");
    private static By profileEmail = By.xpath(".//*[@id='profileIdentifier']");
    private static By passwordField = By.xpath(".//*[@type='password']");
    private static By passwordNextButton = By.xpath(".//*[@id='passwordNext']");
    private static By passwordError = By.xpath(".//*[@class='xgOPLd']");
    private By passwordPageLocator = By.xpath(".//*[contains(text(),'Добро пожаловать!')]");


    void check(){
        wait = (new WebDriverWait(driver, 5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordPageLocator));
    }

    void errorWait(){
        wait = (new WebDriverWait(driver, 5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError));
    }

    private void typePassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }

    private void clickNextButton(){
        driver.findElement(passwordNextButton).click();
        new InboxPage(driver);
    }

    private void clickNextButtonIncorrect(){
        driver.findElement(passwordNextButton).click();
    }

    InboxPage typePasswordGoNext(String password){
        this.typePassword(password);
        this.clickNextButton();
        return new InboxPage(driver);
    }

    void goNextWithInvaildPassword(String email){
        this.typePassword(email);
        this.clickNextButtonIncorrect();
        new LoginPage(driver);
    }

    String getPasswordErrorText(){
        return driver.findElement(passwordError).getText();
    }

    String getHeadLineTitle(){
        return driver.findElement(headlineTitle).getText();
    }

    String getProfileEmail(){
        return driver.findElement(profileEmail).getText();
    }
}
