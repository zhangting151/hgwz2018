package homeWork.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;

public class loginPage {

    @FindBy(id = "user_login")
    WebElement inputUserName;

    @FindBy(id = "user_password")
    WebElement inputPassword;

    @FindBy(css = "input[name='commit']")
    WebElement buttonCommit;

    @FindBy(css = "div.alert>a.close")
    public WebElement failLogin;

    public loginPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public void inputUserName(String username){
        inputUserName.clear();
        inputUserName.sendKeys(username);
        Reporter.log("登录页面，输入用户名：" + username, true);
    }

    public void inputPassword(String password){
        inputPassword.clear();
        inputPassword.sendKeys(password);
        Reporter.log("登录页面，输入密码：" + password, true);
    }

    public void buttonCommit(){
        buttonCommit.click();
        Reporter.log("登录页面，点击提交按钮", true);
    }

    public void loginFlow(String username, String password){
        inputUserName(username);
        inputPassword(password);
        buttonCommit();
    }
}
