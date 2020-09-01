import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
public class Project112 {
    WebDriver driver = null;
        @BeforeMethod
        public void setUp(){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
            driver.manage().window().maximize();

            driver.get("https://orangehrm-demo-6x.orangehrmlive.com/auth/login");
            driver.findElement(By.xpath("//*[@id=\"txtUsername\"]")).click();
            driver.findElement(By.xpath("//*[@id=\"btnLogin\"]")).click();
          //  Thread.sleep(1000);
            driver.findElement(By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/a/span[2]")).click();
            driver.findElement(By.xpath("//*[@id=\"menu_news_Announcements\"]/a")).click();
            driver.findElement(By.xpath("//*[@id=\"menu_news_viewNewsList\"]/span[2]")).click();

            driver.switchTo().frame("noncoreIframe");
            Map<String , List<String>> map = new LinkedHashMap<>();
            List<String> listMap = new ArrayList<>();
            List<WebElement>topic = driver.findElements(By.xpath("//a[@class='newsTopic']"));
            List<WebElement>date = driver.findElements(By.xpath("//td[@style ='width:16%']"));
            List<WebElement>role = driver.findElements(By.xpath("//td[@style='width: 15%']"));
            List<WebElement> attachment = driver.findElements(By.xpath("//tr[@class = 'dataraw']//td/i"));

            for(int i = 0; i < date.size(); i ++) {

                listMap.add(date.get(i).getText() + " | " + role.get(i).getText() + " | " + attachment.get(i));
                map.put(topic.get(i).getText(), listMap);
            }
            int number = 0 ;
            int number2 = 1 ;
            for(Map.Entry< String, List <String>>title : map.entrySet()) {

                System.out.println((number2 + "." + title.getKey() + "|" + title.getValue().get(number)));
                number2++;

            }
//create new item
            driver.findElement(By.xpath("//i[@class=\"large material-icons\"]"));
           // Thread.sleep(3000);
            driver.findElement(By.id("news_topic")).sendKeys("Congratulations  Alfa");
            String today = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
            driver.switchTo().frame("news_description_ifr");
            driver.findElement(By.xpath("//*[@id=\"news_description_ifr\"]")).sendKeys("Promotion was awarded to Team Alfa");
            driver.switchTo().parentFrame();
            driver.findElement(By.xpath("//*[@id=\"nextBtn\"]")).click();
//driver.findElement(By.xpath());

        }
}




