package homeWork.pages;

        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.support.FindBy;
        import org.openqa.selenium.support.PageFactory;
        import org.testng.Reporter;

public class homePage {

    @FindBy(css = "a[href='/zuoanqingtian']")
    public WebElement loginPass;

    @FindBy(css = "a[href='/account/sign_in']")
    WebElement buttonLogin;

    public homePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void clickButtonLogin(){
        buttonLogin.click();
        Reporter.log("首页，点击登录按钮",true);
    }
}
