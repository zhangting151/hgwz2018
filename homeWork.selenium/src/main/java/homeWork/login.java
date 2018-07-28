package homeWork;

import homeWork.pages.homePage;
import homeWork.pages.loginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.*;

import java.util.Iterator;
import static homeWork.utils.dataOption.readDataFromCSV;

public class login {

    private  WebDriver driver;

    @BeforeClass
    public void beforeClass(){
        driver = new ChromeDriver();
    }

    @AfterClass
    public void afterMethod(){driver.quit();}

    @Test(dataProvider = "getDataLoginPass", priority = 1, groups = "smoke")
    public void loginPass(String detail, String username, String password, String expect){
        Reporter.log("登录成功用例，验证：" + detail,true);
        //在浏览器键入网址
        driver.get("https://testerhome.com/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //点击登录链接
        homePage homePage = new homePage(driver);
        homePage.clickButtonLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
        //输入用户名、密码，点击登录
        loginPage logInPage = new loginPage(driver);
        logInPage.loginFlow(username, password);
        //登录成功验证
        WebElement loginPass1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.dropdown>a.dropdown-toggle")));
        loginPass1.click();
        String act = homePage.loginPass.getText();
        Assert.assertEquals(act, expect, "登录失败");
        Reporter.log("登录成功",true);

    }

    @Test(dataProvider = "getDataLoginFail", priority = 0, groups = "function")
    public void loginFail(String detail, String username, String password, String expect){
        Reporter.log("登录失败用例，验证: " + detail,true);
        //在浏览器键入网址
        driver.get("https://testerhome.com/");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        //点击登录链接
        homePage homePage = new homePage(driver);
        homePage.clickButtonLogin();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("user_login")));
        //输入用户名、密码，点击登录
        loginPage logInPage = new loginPage(driver);
        logInPage.loginFlow(username, password);
        //登录验证
        WebElement loginFail1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-warning")));
        String act = loginFail1.getText();
        Assert.assertEquals(act, expect, "未找到登录失败的标志～");
        Reporter.log("登录失败验证成功",true);

    }

    @DataProvider
    public Iterator<Object[]> getDataLoginPass(){
       // return readDataFromCSV("/Users/zz/Desktop/java/selenium/src/main/resources/dataLoginPass.csv");
        return readDataFromCSV(this.getClass().getResource("/dataLoginPass.csv").getPath());
    }
    @DataProvider
    public Iterator<Object[]> getDataLoginFail(){
        return readDataFromCSV(this.getClass().getResource("/dataLoginFail.csv").getPath());
    }

}
