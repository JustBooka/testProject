import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MainPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    MainPage(WebDriver driver){
        MainPage.driver = driver;
    }

    private static final By signInButton = By.xpath(".//*[@class='h-c-header__cta-list header__nav--ltr']//li[2]//a[1]");
    private static final By headLineTitle = By.xpath(".//*[@class='content__title']/h1");

    void check(){
        wait = (new WebDriverWait(driver, 5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(signInButton));
    }

    LoginPage clickSignInButton() {
        driver.findElement(signInButton).click();
        return new LoginPage(driver);
    }

    String getHeadLineTitleText(){
        return driver.findElement(headLineTitle).getText();
    }
}