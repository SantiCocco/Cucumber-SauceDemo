package steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.net.URL;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static void createDriver() {
        String execution = ConfigReader.get("execution");

        if (execution.equalsIgnoreCase("local")) {
            ChromeOptions options = new ChromeOptions();

            String headless = ConfigReader.get("headless");
            if (headless.equalsIgnoreCase("true")) {
                options.addArguments("--headless=new");
                options.addArguments("--disable-gpu");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
            }
            driver = new ChromeDriver(options);
        } else if (execution.equalsIgnoreCase("remote")) {
            try {
                DesiredCapabilities caps = new DesiredCapabilities();
                caps.setCapability("browserName", ConfigReader.get("browser"));
                caps.setCapability("browserVersion", ConfigReader.get("browser.version"));
                caps.setCapability("os", ConfigReader.get("os"));
                caps.setCapability("osVersion", ConfigReader.get("os.version"));
                caps.setCapability("name", "Cucumber Login Test");

                String username = ConfigReader.get("browserstack.username");
                String accessKey = ConfigReader.get("browserstack.accessKey");
                String url = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";

                driver = new RemoteWebDriver(new URL(url), caps);
            } catch (Exception e) {
                throw new RuntimeException("cannot create remote driver: " + e.getMessage());
            }
        } else {
            throw new RuntimeException("Execution type unknown: " + execution);
        }
    }
}