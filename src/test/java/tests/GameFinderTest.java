package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CeneoPage;
import pages.GGPage;
import pages.KinguinPage;
import utils.ListConcatenator;


public class GameFinderTest extends BaseTest {
    //TODO Field to search a game | Input game's full English name
    private final String searchedGame = "Hogwarts Legacy";

    //TODO Field to input minimum price
    private final String minPrice = "20";

    //TODO Field to input maximum price
    private final String maxPrice = "300";

    @Test
    public void testGameFinder() {
        CeneoPage ceneoPage = new CeneoPage(driver);
        KinguinPage kinguinPage = new KinguinPage(driver);
        GGPage ggPage = new GGPage(driver);
        ListConcatenator listConcatenator = new ListConcatenator(driver);

        Assert.assertEquals(ceneoPage.getCeneoWebsite(), "SZUKAJ");
        Assert.assertEquals(ceneoPage.getCeneoGamePricesAndLinks(searchedGame, minPrice, maxPrice), searchedGame.toLowerCase() + " - Gry");
        Assert.assertEquals(kinguinPage.getKinguinWebsite(searchedGame), "Platforma");
        Assert.assertEquals(kinguinPage.getKinguinGamePricesAndLinks(minPrice, maxPrice), "znalezionych wyników");
        Assert.assertEquals(ggPage.getGGWebsite(), "Deals");
        Assert.assertEquals(ggPage.getGGGamePricesAndLinks(searchedGame), "Buy " + searchedGame + " PC");

        listConcatenator.getAllPricesAndLinks(ceneoPage.getFinalListGamePricesWithLinks(),
                kinguinPage.getFinalListGamePricesWithLinks(), ggPage.getFinalListGamePricesWithLinks());
    }
}