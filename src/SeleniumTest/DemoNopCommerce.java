package SeleniumTest;

import Utility.BaseDriver;

import Utility.MyFunc;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.Test;
import java.time.Duration;
import org.junit.Assert;

public class DemoNopCommerce extends BaseDriver {



/* 1-https://demo.nopcommerce.com/ sitesine gidiniz
   2- Login e tıklayın
   3-adminTechno@gmail.com email adresini giriniz     ->daha önce register yaptığımız bilgileri giriyoruz email ve password
   4-Admin123 password olarak girin
   5-Login butonuna tıklayın
   6-Search kısmına "Beats Pill " girin
   7-Search butonuna tıklayın
   8-Sayfada gözüken ürünün Başlığının "Beats Pill" yazısını içerdiğini doğrulayın
   9-Ürüne tıklayın
   10-ADD TO CART butonuna tıklayın
   11-Ürünün başarılı bir şekilde sepete eklendiğini doğrulayın
   */

    @Test
    public void NegatifTestCase(){
        driver.get("https://demo.nopcommerce.com/");
        WebDriverWait bekle=new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement login=driver.findElement(By.xpath("//a[contains(@href ,'login')]"));// a hrefinde login olan
        bekle.until(ExpectedConditions.elementToBeClickable(login));
        login.click();


        WebElement email=driver.findElement(By.xpath("(//div[@class='inputs'])//input[1]"));//div class çocuklarından input olan 1.si
        // yada //input[starts-with(@id,Email)])[3] ->id si email ile başlayan input
        email.sendKeys("iimmeefe@outlook.com");


        WebElement password=driver.findElement(By.xpath("//input[@name='Password']"));
        password.sendKeys("229231");

        WebElement loginbutton=driver.findElement(By.xpath("//button[text()='Log in']"));
        bekle.until(ExpectedConditions.elementToBeClickable(loginbutton));
        loginbutton.click();



        WebElement searchbox=driver.findElement(By.xpath("//*[@id='small-searchterms']"));
        searchbox.sendKeys("Beats Pill");

        WebElement searchclick=driver.findElement(By.cssSelector("[class='button-1 search-box-button']"));
        searchclick.click();

        WebElement title=driver.findElement(By.xpath("//h2[@class='product-title']/a"));
        System.out.println(title.getText());
        Assert.assertTrue(title.getText().toLowerCase().contains("beats pill"));

        WebElement addToCardClick=driver.findElement(By.xpath("//*[text()='Add to cart']"));
        addToCardClick.click();


        WebElement firstPrice=driver.findElement(By.xpath("//div[@class='prices']/span"));// burada ilk gördüğüm fiyatı kaydettik,sepetteki fiyatla karşılaştırcaz
        String doubleStrprice =firstPrice.getText().replaceAll("[^\\d+\\.\\d+]","");//price ın html kodu olarak önünde bulunan yazıyı sildirdik ->regex digits ile
        Double doublePrice =Double.parseDouble(doubleStrprice);// burdada double a çevirdik

        WebElement shoppingCard=driver.findElement(By.xpath("//a[contains(@href,'/cart')][1]"));
        shoppingCard.click();

        WebElement nameIntoCard=driver.findElement(By.xpath("(//*[text()='Beats Pill 2.0 Wireless Speaker'])[2]"));//ürünün sepetteki adını aldık karşılaştırmak için

        WebElement priceIntoCard=driver.findElement(By.xpath("//td[@class='unit-price']/span")); //ürünün sepetteki fiyatının locatini aldık
        String cardPrice=priceIntoCard.getText().replaceAll("[^\\d+\\.\\d+]",""); // String cinsinden fiyatı yazdırdık
        Double cardPriceDouble=Double.parseDouble(cardPrice);  // burada double çevirdik

        Assert.assertTrue(nameIntoCard.getText().toLowerCase().contains("beats pill") && doublePrice.equals(cardPriceDouble));// sepetteki ürünün adını ve
        // ürünün ilk fiyatı ile sepetteki fiyatını doğruladık ( sepetteki fiyat farklı olduğu için hata bekliyoruz)

        MyFunc.BekleKapat(2);


    }

}
