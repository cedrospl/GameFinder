package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListConcatenator {

    private static WebDriver driver;

    public ListConcatenator(WebDriver driver) {
        ListConcatenator.driver = driver;
        PageFactory.initElements(ListConcatenator.driver, this);
    }

    public List<String> getAllPricesAndLinks(List<String> ceneo, List<String> kinguin, List<String> gg) {
        System.out.println("Concatenating prices with all links into one list");
        List<String> listGamePricesWithLinks = new ArrayList<>();
        listGamePricesWithLinks.addAll(ceneo);
        listGamePricesWithLinks.addAll(kinguin);
        listGamePricesWithLinks.addAll(gg);

        Collections.sort(listGamePricesWithLinks);

        System.out.println("Found games with links");
        for (String gamePriceWithLink : listGamePricesWithLinks) {
            System.out.println(gamePriceWithLink);
        }
        return listGamePricesWithLinks;
    }
}
