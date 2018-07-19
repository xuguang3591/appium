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
import java.util.Set;

public class WebViewOperation {
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
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.netease.newsreader.activity:id/c2s")));
        WebElement newsItem = driver.findElements(By.id("com.netease.newsreader.activity:id/fw")).get(0);
        newsItem.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.netease.newsreader.activity:id/auj")));
        Set<String> handles = driver.getContextHandles();
        for (String handle:handles){
            System.out.println("============"+handle);
        }
        String last = handles.toArray()[handles.toArray().length-2].toString();
        System.out.println(last);
        if (last.contains("WEBVIEW")){
            driver.context(last);
        }
        WebElement title = driver.findElement(By.xpath("//*[@id=\"article\"]/h2/b"));
        System.out.println(title.getText().toString());
        Thread.sleep(2000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
