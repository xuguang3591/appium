import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.net.URL;

public class HelloWorld {
    private AppiumDriver<WebElement> driver;

    @BeforeMethod
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File app = new File(classpathRoot, "apk/wangyixinwen_846.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName","Android Emulator");
        capabilities.setCapability("app", app.getAbsolutePath());
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("appActivity", "com.netease.nr.biz.ad.AdActivity");
        capabilities.setCapability("unicodeKeyboard", true);
        capabilities.setCapability("resetKeyboard", true);
        driver = new AndroidDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @Test
    public void testName() throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.netease.newsreader.activity:id/c52")));
        driver.findElement(By.id("com.netease.newsreader.activity:id/c52")).click();
        WebElement searchET = driver.findElement(By.id("com.netease.newsreader.activity:id/akw"));
        searchET.sendKeys("helloworld你好!");
        Thread.sleep(2000);
        searchET.clear();
//        Thread.sleep(10000);
//        WebElement element = driver.findElement(By.id("com.netease.newsreader.activity:id/c4e"));
//        WebElement element = driver.findElement(By.xpath("//android.widget.TextView[@text='娱乐']"));
//        WebElement element = driver.findElements(By.className("android.widget.ImageView")).get(4);
//        element.click();
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
