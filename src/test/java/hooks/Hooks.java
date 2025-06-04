package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import steps.DriverManager;

public class Hooks {

    @Before(order = 0)
    public void setUp() {
        System.out.println("Hooks @Before - creando driver...");
        DriverManager.createDriver();
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.manage().window().maximize();
        } else {
            throw new RuntimeException("WebDriver is null in @Before hook");
        }
    }

    @After
    public void tearDown() {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}