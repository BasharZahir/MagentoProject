import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AddOneRandomItem {
	
	WebDriver driver = new ChromeDriver();
	String URL = "https://magento.softwaretestingboard.com/";
	
	String Password = "ASDasd123!@";
	
	
	@BeforeTest
	
	public void mySetup() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
	}
	
	@Test(invocationCount = 1, priority = 1, description = "this is 1st test")
	public void  addOneRandomItemToTheCart() throws InterruptedException {
		Random rand = new Random();
		driver.get(URL);

		
		WebElement Container = driver.findElement(By.className("product-items"));
		List<WebElement> myList =  Container.findElements(By.tagName("li"));
		int RandomIndex = rand.nextInt(myList.size());

		
			myList.get(RandomIndex).click();
			
			Thread.sleep(2000);
			if(driver.getCurrentUrl().contains("fusion") || driver.getCurrentUrl().contains("push")) {
				WebElement addtoCartButton = driver.findElement(By.id("product-addtocart-button")); 
			    addtoCartButton.click();

			}else {

				WebElement sizeContainer = driver.findElement(By.cssSelector("div[class='swatch-attribute size'] div[role='listbox']"));	
				List <WebElement> theListOfSizes = sizeContainer.findElements(By.tagName("div"));
				int RandomSize = rand.nextInt(theListOfSizes.size());
				
				theListOfSizes.get(RandomSize).click();
				
				
				WebElement colorsContainer = driver.findElement(By.xpath("//div[@class='swatch-attribute color']//div[@role='listbox']"));
				List<WebElement> theListOfColors = colorsContainer.findElements(By.tagName("div"));
				int RandomColor = rand.nextInt(theListOfColors.size());
				
				theListOfColors.get(RandomColor).click();
				
				WebElement addtoCartButton = driver.findElement(By.id("product-addtocart-button")); 
			    addtoCartButton.click();
				
			    Thread.sleep(3000);
			}
			
			WebElement Msg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
			
			boolean actual = Msg.getText().contains("You added");
			boolean expected = true;
			
			Assert.assertEquals(actual, expected);
	}
	
	
	@Test(enabled = false, priority = 2, description = "this is the second test which is checkout")
	
	public void checkoutProcess() throws InterruptedException {
		String CheckoutPage = "https://magento.softwaretestingboard.com/checkout/cart/";
		driver.get(CheckoutPage);
		
		Thread.sleep(1000);
		 
		WebElement procceedButton = driver.findElement(By.xpath("//button[@data-role='proceed-to-checkout']"));
		procceedButton.click();
		
	}
	
	
	@Test(enabled = false, priority = 3)
	
	public void SignupProcess() throws InterruptedException {
		
		String ExpectedMsg = "Thank you for registering with Main Website Store.";
				
		
		Thread.sleep(2000);
		WebElement email = driver.findElement(By.id("customer-email"));
		WebElement firstName = driver.findElement(By.name("firstname"));
		WebElement lastName = driver.findElement(By.name("lastname"));
		WebElement streetAdress = driver.findElement(By.name("street[0]"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("region_id"));
		WebElement postalCode = driver.findElement(By.name("postcode"));
		WebElement country = driver.findElement(By.name("country_id"));
		WebElement phoneNumber = driver.findElement(By.name("telephone"));
		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		
		email.sendKeys("basharghanem1944@gmail.com");
		
		firstName.sendKeys("bashar");
		
		lastName.sendKeys("zahir");
		
		streetAdress.sendKeys("irbid st");
		
		city.sendKeys("irbid");
		
		state.sendKeys("eastern district");
		
		postalCode.sendKeys("298989");
		
		country.sendKeys("Jordan");
		
		phoneNumber.sendKeys("962485975643");
				
		Select select = new Select(country);
		select.selectByVisibleText("Jordan");
		
//		select.selectByIndex(1);
//		select.selectByValue("CN");
		
		Thread.sleep(3000);
		nextButton.click();
		
		Thread.sleep(5000);
		
		WebElement placeOrderButton = driver.findElement(By.cssSelector(".action.primary.checkout"));
		placeOrderButton.click();
		
		Thread.sleep(5000);
		
		WebElement CreateAnAccountButton = driver.findElement(By.xpath("//a[@href='https://magento.softwaretestingboard.com/checkout/account/delegateCreate/']"));
		CreateAnAccountButton.click();
		
		Thread.sleep(5000);

		
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement confirmPassword = driver.findElement(By.id("password-confirmation"));
		
		PasswordInput.sendKeys(Password);
		confirmPassword.sendKeys(Password);
		
		WebElement CreateAnAccountFinal = driver.findElement(By.cssSelector(".action.submit.primary"));
		CreateAnAccountFinal.click();
		
		Thread.sleep(3000);

		WebElement succesfullMsg = driver.findElement(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));
		
		String ActualMsg = succesfullMsg.getText();
		
		Assert.assertEquals(ActualMsg, ExpectedMsg);
		
		
		
	}
	
	
	

}
