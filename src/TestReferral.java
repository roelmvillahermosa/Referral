/**
 * Created by Roel on 11/19/2016.
 */

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;


public class TestReferral {
    public String FrontendLogin = "";
    public String ReferralURL = "";
    public String CustomerRegistrationUrl = "";
    public String ReferralCode = "";
    public String ReferralInputTextId = "";
    public String AffiliateUserPass = "";
    public static String ChromeWebDriver = "webdriver.chrome.driver";
    public static String ChromeWebDriverPath =  "C:\\Users\\*****\\Downloads\\selenium-java-3.0.1\\chromedriver_win32\\chromedriver.exe";
    public String RecipientList = "";
    public String ReferralStatus = "";
    public String ReferralPageTitle = "";
    public String SimpleProduct = ""


    protected void SetUpData() {
        this.FrontendLogin = "";
        this.ReferralURL = "";
        this.CustomerRegistrationUrl = "";
        this.ReferralCode = "QYQ"; // Affiliate
        this.ReferralInputTextId = "rewards_referral";
        this.AffiliateUserPass = "";
        this.RecipientList = "";
        this.ReferralStatus = "Message Sent";
        this.ReferralPageTitle = "My Referrals";
        this.SimpleProduct = "";
    }

    public void TestReferralURL_ReferralCodeBox() {
        this.SetUpData();
        //Use Chrome Driver
        ChromeDriver driver = new ChromeDriver();
        driver.get(this.ReferralURL); //save referrer code inside session
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait for 5 seconds
        driver.get(this.CustomerRegistrationUrl); //go to registration account page
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait for 5 seconds
        String myFieldValue = driver.findElement(By.id(this.ReferralInputTextId)).getAttribute("value"); //get the value of referral code box
        Assert.assertEquals(myFieldValue, this.ReferralCode); //compare referral code box value if equal to referral code at Create Account Page

        this.ExitTestCase();
    }
    public void TestReferralPage_Title(){
        this.SetUpData();
        ChromeDriver driver = new ChromeDriver();
        driver.get(this.FrontendLogin); //go to log-in page
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(new CharSequence[]{this.AffiliateUserPass});
        driver.findElement(By.id("pass")).sendKeys(new CharSequence[]{this.AffiliateUserPass});
        driver.findElement(By.cssSelector("button[class=\'button\']")).click();
        driver.findElement(By.xpath("//a[@href='']")).click();
        Assert.assertEquals(driver.getTitle(), this.ReferralPageTitle); //Test Referral page title
        this.ExitTestCase();
    }

    public void TestReferralBinding_MessageSent(){
        this.SetUpData();
        ChromeDriver driver = new ChromeDriver();
        driver.get(this.FrontendLogin); //go to log-in page
        driver.manage().window().maximize();
        driver.findElement(By.id("email")).sendKeys(new CharSequence[]{this.AffiliateUserPass});
        driver.findElement(By.id("pass")).sendKeys(new CharSequence[]{this.AffiliateUserPass});
        driver.findElement(By.cssSelector("button[class=\'button\']")).click();
        driver.findElement(By.xpath("//a[@href='']")).click();
        driver.findElement(By.id("recipient_list")).sendKeys(new CharSequence[]{this.RecipientList});
        driver.findElement(By.cssSelector("button[class=\'button\']")).click();
        String myStatus = driver.findElement(By.xpath("//table[@id='referred-friends-table']/tbody/tr[@class='first last odd']/td[@class='a-left']")).getText();
        String myReferral = driver.findElement(By.xpath("//table[@id='referred-friends-table']/tbody/tr[@class='first last odd']/td[2]")).getText();
        Assert.assertEquals(myStatus, this.ReferralStatus);
        Assert.assertEquals(myReferral, this.RecipientList);
        this.ExitTestCase();
    }

    public void ExitTestCase(){
        this.ClearData();
        ChromeDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); //wait for 5 seconds
        driver.close();
    }

    /* Clearing prepared data */
    protected void ClearData() {
        this.FrontendLogin = "";
        this.ReferralURL = "";
        this.CustomerRegistrationUrl = "";
        this.ReferralCode = "";
        this.ReferralInputTextId = "";
        this.AffiliateUserPass = "";
        this.RecipientList = "";
        this.ReferralStatus = "";
        this.ReferralPageTitle = "";
        this.SimpleProduct = "";
    }

    public static void main(String[] args) {
        System.setProperty(ChromeWebDriver, ChromeWebDriverPath);
        TestReferral myTestReferral=new TestReferral();
        myTestReferral.TestReferralPage_Title();
        myTestReferral.TestReferralURL_ReferralCodeBox();
        myTestReferral.TestReferralBinding_MessageSent();
    }

}
