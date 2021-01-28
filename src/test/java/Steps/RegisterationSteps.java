package Steps;

import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import io.cucumber.junit.Cucumber;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

@RunWith(Cucumber.class)
public class RegisterationSteps  {

    WebDriver driver;

    public RegisterationSteps() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }
    protected String HomePage = "https://moneygaming.qa.gameaccount.com/";
    protected String homePageJoin  = "a.newUser";
    protected String title  = "title";
    protected String forename  = "forename";
    protected String lastname  = "[name*='map(lastName']";
    protected String termsCheckbox  = "[name*='map(terms)']";
    protected String joinus  = "form";
    protected String errorDob  = "[for*='dob']";
    protected String errorFieldMessage  = "This field is required";
    protected String passwordElement  = "[name*='map(password)']";
    protected String errorPassword  = "[for*='password']";
    protected String passwordConfirmElement  = "[name*='map(passwordConfirm)']";
    protected String errorPasswordConfirm  = "[for*='passwordConfirm']";
    protected String passwordErrorMessageAtleast6Chars  = "The minimum length is 6 characters";
    protected String RegisterationPage  = "signupForm";
    protected String errorpassword = "Both fields must match";

    @Given("I navigate to moneygaming website")
    public void i_navigate_to_moneygaming_website() {

        driver.navigate().to(HomePage);

    }

    @When("I click JoinNow button")
    public void i_click_join_now_button() {
        driver.findElement(By.cssSelector(homePageJoin)).click();
    }

    @Given("I see the registration page")
    public void i_see_the_registration_page() {
        driver.findElement(By.id(RegisterationPage)).click();

    }

    @Given("I select {string} from the title selectbox")
    public void i_select_from_the_title_selectbox(String titleValue) {

        new Select(driver.findElement(By.id(title))).selectByValue(titleValue);
    }

    @Then("I enter firstname {string} and surname {string} in the form")
    public void i_enter_firstname_and_surname_in_the_form(String foreNameValue, String lastNameValue) {

        driver.findElement(By.id(forename)).sendKeys(foreNameValue);
        driver.findElement(By.cssSelector(lastname)).sendKeys(lastNameValue);
    }

    @Given("I click terms and condition checkbox")
    public void i_click_terms_and_condition_checkbox() {

        driver.findElement(By.cssSelector(termsCheckbox)).click();
    }

    @Then("I should be able to see error message below dob selectbox as {string}")
    public void i_should_be_able_to_see_error_message_below_dob_selectbox_as(String string) {

       driver.findElement(By.id(joinus)).click();
       String errorDobText = driver.findElement(By.cssSelector(errorDob)).getText();
       Assert.assertTrue("Error message for DOB is not correct "+errorFieldMessage, errorFieldMessage.equals(errorDobText));

    }

    @Given("I enter password {string}")
    public void i_enter_password(String passwordValue) {

        driver.findElement(By.cssSelector(passwordElement)).sendKeys(passwordValue);
        driver.findElement(By.cssSelector(passwordConfirmElement)).sendKeys(passwordValue);
    }

    @Then("I should be able to see error message in the password field be atleast six characters")
    public void i_should_be_able_to_see_error_message_in_the_password_field_be_atleast_six_characters() {

        String errorPasswordText = driver.findElement(By.cssSelector(errorPasswordConfirm)).getText();
        Assert.assertTrue("Error message password not having 6 chars ", passwordErrorMessageAtleast6Chars.equals(errorPasswordText));

    }

    @Then("I should be able to see error message in the password field must contain atleast one number")
    public void i_should_be_able_to_see_error_message_in_the_password_field_must_contain_atleast_one_number() {

        driver.findElement(By.id(joinus)).click();
        String errorPasswordText = driver.findElement(By.cssSelector(errorPasswordConfirm)).getText();
        Assert.assertTrue("Error message password not having one number  ", errorpassword.equals(errorPasswordText));
    }

    @Then("I should be able to see error message in the password field must contain atleast one special character")
    public void i_should_be_able_to_see_error_message_in_the_password_field_must_contain_atleast_one_special_character() {

        driver.findElement(By.id(joinus)).click();
        String errorPasswordText = driver.findElement(By.cssSelector(errorPasswordConfirm)).getText();
        Assert.assertTrue("Error message password not having one special character ", errorpassword.equals(errorPasswordText));

    }

    @After
    public void afterScenario(Scenario scenario) {

        if(driver != null){
            driver.close();
            driver.quit();
            driver = null;
        }

        System.out.println(scenario.getStatus());
    }
}
