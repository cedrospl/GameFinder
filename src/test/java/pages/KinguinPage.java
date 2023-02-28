package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class KinguinPage {

    private static WebDriver driver;

    public KinguinPage(WebDriver driver) {
        KinguinPage.driver = driver;
        PageFactory.initElements(KinguinPage.driver, this);
    }

    public List<String> getFinalListGamePricesWithLinks() {
        return finalListGamePricesWithLinks;
    }

    public void setFinalListGamePricesWithLinks(List<String> finalListGamePricesWithLinks) {
        this.finalListGamePricesWithLinks = finalListGamePricesWithLinks;
    }

    public List<String> finalListGamePricesWithLinks;

    @FindBy(xpath = "//div[@class=\"col-lg-8\"]/div/*[1]")
    private WebElement siteHeader;

    @FindBy(xpath = "//div[@class=\"sc-h0oox2-4 izlGMf\"]")
    private WebElement siteMenu;
    @FindBy(xpath = "//input[@aria-label=\"Search phrase\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//h2[@class=\"sc-1j92ujr-1 iMhqqC\"]/*[2]")
    private WebElement searchHeader;

    @FindBy(xpath = "//input[@id='min']")
    private WebElement searchFilterMin;

    @FindBy(xpath = "//input[@id='max']")
    private WebElement searchFilterMax;

    @FindBy(xpath = "//button[@class=\"sc-13kaj8j-1 kEFhfP c-product__btn\"]/div/*[3]")
    private List<WebElement> gamePrices;

    @FindBy(xpath = "//div[@class=\"sc-1oomi3j-4 hNPzBM\"]/*[1]/*[2]")
    private List<WebElement> gameLinks;

    public String getKinguinWebsite() {
        System.out.println("Opening kinguin.pl");
        driver.get("https://www.kinguin.net");
        return siteHeader.getText();
    }

    public String getKinguinGamePricesAndLinks(String searchedGame, String minPrice, String maxPrice) {
        System.out.println("KINGUIN.NET Searching for " + searchedGame);
        searchBar.sendKeys(searchedGame);
        searchBar.sendKeys(Keys.ENTER);
        System.out.println("KINGUIN.NET Filtering found list - minimum price " + minPrice);
        searchFilterMin.sendKeys(minPrice);
        System.out.println("KINGUIN.NET Filtering found list - maximum price " + maxPrice);
        searchFilterMax.sendKeys(maxPrice);
        System.out.println("KINGUIN.NET Gathering prices from found games list");
        List<WebElement> gamePricesElements = gamePrices;
        List<String> listGamePrices = new ArrayList<>();
        for (WebElement value : gamePricesElements) {
            listGamePrices.add(value.getText());
        }
        System.out.println("KINGUIN.NET Gathering links from found games list");
        List<WebElement> gameLinksElements = gameLinks;
        List<String> listGameLinks = new ArrayList<>();
        for (WebElement element : gameLinksElements) {
            String gameLink = element.getAttribute("href");
            listGameLinks.add(gameLink);
        }
        System.out.println("KINGUIN.NET Concatenating prices with links into one list");
        List<String> listGamePricesWithLinks = new ArrayList<>();
        for (int i = 0; i < listGamePrices.size() && i < listGameLinks.size(); i++) {
            String joined = "Price: " + listGamePrices.get(i) + "zł --> Link: " + listGameLinks.get(i);
            listGamePricesWithLinks.add(joined);
            setFinalListGamePricesWithLinks(listGamePricesWithLinks);
        }
        return searchHeader.getText();
    }
}
