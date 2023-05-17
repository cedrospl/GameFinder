package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class CeneoPage {

    private static WebDriver driver;

    public CeneoPage(WebDriver driver) {
        CeneoPage.driver = driver;
        PageFactory.initElements(CeneoPage.driver, this);
    }

    public List<String> getFinalListGamePricesWithLinks() {
        return finalListGamePricesWithLinks;
    }

    public void setListGamePricesWithLinks(List<String> listGamePricesWithLinks) {
        this.finalListGamePricesWithLinks = listGamePricesWithLinks;
    }

    public List<String> finalListGamePricesWithLinks;

    @FindBy(xpath = "//span[@class='header-search__button__text']")
    private WebElement searchButton;

    @FindBy(xpath = "//input[@name='search-query']")
    private WebElement searchBar;

    @FindBy(xpath = "//span[@class='cat-name__title']/*[1]")
    private WebElement searchResults;

    @FindBy(xpath = "//input[@name='price-min']")
    private WebElement searchFilterMin;

    @FindBy(xpath = "//input[@name='price-max']")
    private WebElement searchFilterMax;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement searchFilterSubmit;

    @FindBy(xpath = "//div[@class=\"cat-prod-row__price\"]//span[@class='value']")
    private List<WebElement> gameValues;

    @FindBy(xpath = "//div[@class=\"cat-prod-row__price\"]//span[@class='penny']")
    private List<WebElement> gamePennies;

    @FindBy(xpath = "//a[@class=\"js_seoUrl js_clickHash go-to-product\"]")
    private List<WebElement> gameLinks;

    public String getCeneoWebsite() {
        System.out.println("OPENING ceneo.pl");
        driver.get("https://ceneo.pl/gry");
        return searchButton.getText();
    }

    public String getCeneoGamePricesAndLinks(String searchedGame, String minPrice, String maxPrice) {
        System.out.println("CENEO.PL Searching for " + searchedGame + " version");
        searchBar.sendKeys(searchedGame);
        searchBar.sendKeys(Keys.ENTER);
        System.out.println("CENEO.PL Filtering found list - minimum price " + minPrice);
        searchFilterMin.sendKeys(minPrice);
        System.out.println("CENEO.PL Filtering found list - maximum price " + maxPrice);
        searchFilterMax.sendKeys(maxPrice);
        System.out.println("CENEO.PL Accepting filters used");
        searchFilterSubmit.click();
        System.out.println("CENEO.PL Gathering values from found games list");
        List<String> listGameValues = new ArrayList<>();
        for (WebElement value : gameValues) {
            listGameValues.add(value.getText());
        }
        System.out.println("CENEO.PL Gathering pennies from found games list");
        List<String> listGamePennies = new ArrayList<>();
        for (WebElement penny : gamePennies) {
            listGamePennies.add(penny.getText());
        }
        System.out.println("CENEO.PL Concatenating values with pennies from found games list");
        List<String> listGamePrices = IntStream.range(0, Math.min(listGameValues.size(), listGamePennies.size()))
                .mapToObj(i -> listGameValues.get(i) + listGamePennies.get(i))
                .toList();
        System.out.println("CENEO.PL Gathering links from found games list");
        List<String> listGameLinks = new ArrayList<>();
        for (int i = 0; i < gameLinks.size(); i += 2) {
            String gameLink = gameLinks.get(i).getAttribute("href");
            listGameLinks.add(gameLink);
        }
        System.out.println("CENEO.PL Concatenating prices with links into one list");
        List<String> listGamePricesWithLinks = new ArrayList<>();
        for (int i = 0; i < listGamePrices.size() && i < listGameLinks.size(); i++) {
            String joined = "Price: " + listGamePrices.get(i) + " zł --> Link: " + listGameLinks.get(i);
            listGamePricesWithLinks.add(joined);
            setListGamePricesWithLinks(listGamePricesWithLinks);
        }
        return searchResults.getText();
    }
}