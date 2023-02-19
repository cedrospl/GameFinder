package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class GameFinderTest {

    @Test
    public void testGameFinderCeneo() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        System.out.println("Opening ceneo.pl");
        driver.get("https://ceneo.pl");
        WebElement searchButton = driver.findElement(By.xpath("//span[@class='header-search__button__text']"));
        Assert.assertEquals(searchButton.getText(), "SZUKAJ");
        System.out.println("CENEO.PL Searching for Hogwarts Legacy PC Digital version");
        driver.findElement(By.xpath("//input[@name='search-query']")).sendKeys("Hogwarts Legacy PC");
        driver.findElement(By.xpath("//input[@name='search-query']")).sendKeys(Keys.ENTER);
        WebElement foundPage = driver.findElement(By.xpath("//span[@class='cat-name__title']/*[1]"));
        Assert.assertEquals(foundPage.getText(), "hogwarts legacy pc " + "- Gry");
        System.out.println("CENEO.PL Filtering found list - minimum price 20");
        driver.findElement(By.xpath("//input[@name='price-min']")).sendKeys("20");
        System.out.println("CENEO.PL Filtering found list - maximum price 300");
        driver.findElement(By.xpath("//input[@name='price-max']")).sendKeys("300");
        System.out.println("CENEO.PL Accepting filters used");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        System.out.println("CENEO.PL Gathering values from found games list");
        List<WebElement> gameValues = driver.findElements(By.xpath("//div[@class=\"cat-prod-row__price\"]//span[@class='value']"));
        List<String> listGameValues = new ArrayList<>();
        for (WebElement value : gameValues) {
            listGameValues.add(value.getText());
        }
        List<String> listGamePennies = new ArrayList<>();
        List<WebElement> gamePennies = driver.findElements(By.xpath("//div[@class=\"cat-prod-row__price\"]//span[@class='penny']"));
        for (WebElement penny : gamePennies) {
            listGamePennies.add(penny.getText());
        }
        List<String> listGamePrices = new ArrayList<>();
        for (int i = 0; i < listGameValues.size() && i < listGamePennies.size(); i++) {
            String joined = listGameValues.get(i) + listGamePennies.get(i);
            listGamePrices.add(joined);
        }

        System.out.println("CENEO.PL Gathering links from found games list");
        List<WebElement> GameLinks = driver.findElements(By.xpath("//a[@class=\"js_seoUrl js_clickHash go-to-product\"]"));
        List<String> listGameLinks = new ArrayList<>();
        for(int i = 0; i < GameLinks.size(); i+= 2) {
            String gameLink = GameLinks.get(i).getAttribute("href");
            listGameLinks.add(gameLink);
        }

        System.out.println("CENEO.PL Concatenating prices with links into one list");
        List<String> listGamePricesWithLinks = new ArrayList<>();
        for (int i = 0; i < listGamePrices.size() && i < listGameLinks.size(); i++) {
            String joined = "Price: " + listGamePrices.get(i) + "zł --> Link: " + listGameLinks.get(i);
            listGamePricesWithLinks.add(joined);
        }

        System.out.println("Opening kinguin.net");
        driver.get("https://www.kinguin.net");
        System.out.println("KINGUIN.NET Searching for Hogwarts Legacy PC Digital version");
        System.out.println("KINGUIN.NET Searching for Hogwarts Legacy");
        driver.findElement(By.xpath("//input[@aria-label=\"Search phrase\"]")).sendKeys("Hogwarts Legacy");
        driver.findElement(By.xpath("//input[@aria-label=\"Search phrase\"]")).sendKeys(Keys.ENTER);
        WebElement searchHeader = driver.findElement(By.xpath("//h2[@class=\"sc-1j92ujr-1 iMhqqC\"]/*[2]"));
        Assert.assertEquals(searchHeader.getText(),"znalezionych wyników");
        System.out.println("KINGUIN.NET Filtering found list - minimum price 20");
        driver.findElement(By.xpath("//input[@id='min']")).sendKeys("20");
        System.out.println("KINGUIN.NET Filtering found list - maximum price 300");
        driver.findElement(By.xpath("//input[@id='max']")).sendKeys("300");
        System.out.println("KINGUIN.NET Gathering prices from found games list");
        List<WebElement> gamePrices = driver.findElements(By.xpath("//button[@class=\"sc-13kaj8j-1 kEFhfP c-product__btn\"]/div/*[3]"));
        List<String> listGamePrices2 = new ArrayList<>();
        for (WebElement value : gamePrices) {
            listGamePrices2.add(value.getText());
        }

        System.out.println("KINGUIN.NET Gathering links from found games list");
        List<WebElement> GameLinks2 = driver.findElements(By.xpath("//div[@class=\"sc-1oomi3j-4 hNPzBM\"]/*[1]/*[2]"));
        List<String> listGameLinks2 = new ArrayList<>();
        for (WebElement element : GameLinks2) {
            String gameLink = element.getAttribute("href");
            listGameLinks2.add(gameLink);
        }

        System.out.println("KINGUIN.NET Concatenating prices with links into one list");
        List<String> listGamePricesWithLinks2 = new ArrayList<>();
        for (int i = 0; i < listGamePrices2.size() && i < listGameLinks2.size(); i++) {
            String joined = "Price: " + listGamePrices2.get(i) + "zł --> Link: " + listGameLinks2.get(i);
            listGamePricesWithLinks2.add(joined);
        }

        System.out.println("Opening gg.deals");
        driver.get("https://gg.deals");

        WebElement sectionHeader = driver.findElement(By.xpath("//h2[@class=\"section-title\"]"));
        Assert.assertEquals(sectionHeader.getText(), "Deals");
        System.out.println("GG.DEALS Searching for Hogwarts Legacy PC Digital version");
        driver.findElement(By.xpath("//input[@id=\"global-search-input\"]")).sendKeys("Hogwarts Legacy");
        List<WebElement> clickFirstGame = driver.findElements(By.xpath("//div[@class=\"compact-image game-image\"]"));
        {
            int i = 0;
            while (i < clickFirstGame.size()) {
                clickFirstGame.get(0).click();
                break;
            }
        }
        System.out.println("GG.DEALS Gathering prices from found games list");
        List<WebElement> gamePrices2 = driver.findElements(By.xpath("//span[@class=\"price-inner game-price-current\"]"));
        List<String> listGamePrices3 = new ArrayList<>();
        for (WebElement value: gamePrices2) {
            listGamePrices3.add(value.getText());
        }
        System.out.println("GG.DEALS Gathering links from found games list");
        List<WebElement> GameLinks3 = driver.findElements(By.xpath("//a[@class=\"action-desktop-btn d-flex flex-align-center flex-justify-center action-btn cta-label-desktop with-arrows action-ext\"]"));
        List<String> listGameLinks3 = new ArrayList<>();
        for (WebElement webElement : GameLinks3) {
            String gameLink = webElement.getAttribute("href");
            listGameLinks3.add(gameLink);
        }
        System.out.println("GG.DEALS Concatenating prices with links into one list");
        List<String> listGamePricesWithLinks3 = new ArrayList<>();
        for (int i = 0; i < listGamePrices3.size() && i < listGameLinks3.size(); i++) {
            String joined = "Price: " + listGamePrices3.get(i) + "zł --> Link: " + listGameLinks3.get(i);
            listGamePricesWithLinks3.add(joined);
        }

        System.out.println("Concatenating prices with all links into one list");
        List<String> listGamePricesWithLinks4 = new ArrayList<>();
        listGamePricesWithLinks4.addAll(listGamePricesWithLinks);
        listGamePricesWithLinks4.addAll(listGamePricesWithLinks2);
        listGamePricesWithLinks4.addAll(listGamePricesWithLinks3);

        Collections.sort(listGamePricesWithLinks4);

        System.out.println("Found games with links");
        for(String gamePriceWithLink: listGamePricesWithLinks4){
            System.out.println(gamePriceWithLink);
        }
        driver.quit();
    }
}