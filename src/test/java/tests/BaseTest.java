package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.*;

public class BaseTest {

    protected WebDriver driver;

    //TODO Field to choose which web browser to run
    protected String selectDriver = "Chrome";

    @BeforeTest
    public void setUp() {
        String selectedDriver = selectDriver;
        System.out.println("Opening website with " + selectedDriver + " driver");
        switch (selectedDriver.toLowerCase()) {
            case "chrome" -> {
                chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            }
            case "firefox" -> {
                firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            }
            case "edge" -> {
                edgedriver().setup();
                driver = new EdgeDriver();

            }
            default -> System.out.println("Wrongly selected driver: Chrome, Firefox or Edge are correct values");
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterTest
    public void tearDown() {
        System.out.println("Driver shut down");
        driver.quit();
    }
}
