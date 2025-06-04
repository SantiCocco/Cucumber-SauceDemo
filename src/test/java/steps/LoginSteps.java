package steps;

import io.cucumber.java.en.*;
import io.cucumber.java.Before;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginSteps {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before(order = 1)
    public void setUp() {
        this.driver = DriverManager.getDriver();
        System.out.println("Driver en LoginSteps @Before: " + this.driver);
        this.loginPage = new LoginPage(driver);
    }

    @Given("The user opens the login page")
    public void the_user_opens_the_login_page() {
        loginPage = new LoginPage(driver);
        loginPage.openLoginPage();
    }

    @When("They enter username {string} and password {string}")
    public void they_enter_username_and_password(String username, String password) {
        loginPage.enterCredentials(username, password);
    }

    @And("They click the login button")
    public void they_click_the_login_button() {
        loginPage.clickLoginButton();
    }

    @Then("They should be redirected to the inventory page")
    public void they_should_be_redirected_to_the_inventory_page() {
        Assert.assertTrue(loginPage.isOnInventoryPage());
    }

    @And("They should see the title {string}")
    public void they_should_see_the_title(String expectedTitle) {
        Assert.assertEquals(expectedTitle, loginPage.getPageTitle());
    }

    @Then("They should see an error message")
    public void they_should_see_an_error_message() {
        Assert.assertTrue(loginPage.isErrorMessageDisplayed());
    }

    @Then("The login result should be {string}")
    public void the_login_result_should_be(String result) {
        switch (result.toLowerCase()) {
            case "success":
                Assert.assertTrue(loginPage.isOnInventoryPage());
                break;
            case "error":
                Assert.assertTrue(loginPage.isErrorMessageDisplayed());
                break;
            default:
                Assert.fail("Unknown expected result: " + result);
        }
    }
}
