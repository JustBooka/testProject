import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class InboxPage {

    private static WebDriver driver;
    private static WebDriverWait wait;

    InboxPage(WebDriver driver){
        InboxPage.driver = driver;
    }

    private static By searchField = By.xpath(".//*[@class='gb_mf gb_lf']");
    private static By leftMenu = By.xpath(".//*[@class='ajl aib aZ6 ajm']");
    private static By unsortedTitle = By.xpath(".//*[@id=':2a']");
    private By inboxTitleLocator = By.xpath(".//*[@title='Входящие']");

    void check(){
        wait = (new WebDriverWait(driver, 10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(inboxTitleLocator));
    }

    String getUnsortedTabText() {
        return driver.findElement(unsortedTitle).getText();
    }
}
