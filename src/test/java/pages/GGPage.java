package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class GGPage {
    private static WebDriver driver;

    public GGPage(WebDriver driver) {
        GGPage.driver = driver;
        PageFactory.initElements(GGPage.driver, this);
    }

    public List<String> getFinalListGamePricesWithLinks() {
        return finalListGamePricesWithLinks;
    }

    public void setFinalListGamePricesWithLinks(List<String> finalListGamePricesWithLinks) {
        this.finalListGamePricesWithLinks = finalListGamePricesWithLinks;
    }

    public List<String> finalListGamePricesWithLinks;
    @FindBy(xpath = "//h2[@class=\"section-title\"]")
    private WebElement sectionHeader;

    @FindBy(xpath = "//a[@class=\"game-price-anchor-link\"]/*")
    private WebElement searchHeader;

    @FindBy(xpath = "//input[@id=\"global-search-input\"]")
    private WebElement searchBar;

    @FindBy(xpath = "//div[@class=\"compact-image game-image\"]")
    private List<WebElement> clickFirstGame;

    @FindBy(xpath = "//span[@class=\"price-inner game-price-current\"]")
    private List<WebElement> gamePrices;

    @FindBy(xpath = "//a[@class=\"action-desktop-btn d-flex flex-align-center flex-justify-center action-btn cta-label-desktop with-arrows action-ext\"]")
    private List<WebElement> gameLinks;

    public String getGGWebsite() {
        System.out.println("Opening gg.deals");
        driver.get("https://gg.deals");
        return sectionHeader.getText();
    }

    public String getGGGamePricesAndLinks(String searchedGame) {
        System.out.println("GG.DEALS Searching for " + searchedGame + " version");
        searchBar.sendKeys(searchedGame);
        List<WebElement> clickFirstFoundGame = clickFirstGame;
        {
            int i = 0;
            while (i < clickFirstGame.size()) {
                clickFirstGame.get(0).click();
                break;
            }
            System.out.println("GG.DEALS Gathering prices from found games list");
            List<WebElement> gamePricesElements = gamePrices;
            List<String> listGamePrices = new ArrayList<>();
            for (WebElement value : gamePricesElements) {
                listGamePrices.add(value.getText().replace("~", ""));
            }
            System.out.println(listGamePrices.size());
            System.out.println("GG.DEALS Gathering links from found games list");
            List<WebElement> gameLinksElements = gameLinks;
            List<String> listGameLinks = new ArrayList<>();
            for (WebElement webElement : gameLinksElements) {
                String gameLink = webElement.getAttribute("href");
                listGameLinks.add(gameLink);
            }
            System.out.println(listGameLinks.size());
            System.out.println("GG.DEALS Concatenating prices with links into one list");
            List<String> listGamePricesWithLinks = new ArrayList<>();
            for (int in = 0; in < listGamePrices.size() && in < listGameLinks.size(); in++) {
                String joined = "Price: " + listGamePrices.get(in) + "zł --> Link: " + listGameLinks.get(in);
                listGamePricesWithLinks.add(joined);
                setFinalListGamePricesWithLinks(listGamePricesWithLinks);
            }
            return searchHeader.getText();
        }
    }
}