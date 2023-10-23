package pageObjects;

import org.openqa.selenium.By;

public class SearchTestPage {
    public By getInputSearchbox() {
        return By.xpath(".//input[@name='as_word']");
    }

    public By getSubmitSearchButton() {
        return By.xpath(".//button[@type='submit']");
    }

    public By getFilterByBrand(String brandName) {
        return By.xpath(".//h3[contains(text(),'Marca')]//ancestor::div[1]//a[@aria-label='" + brandName + "']");
    }

    public By getFilterByMaterial(String materialName) {
        return By.xpath(".//h3[contains(text(),'Material')]//ancestor::div[1]//a[@aria-label='" + materialName + "']");
    }

    public By getInputMaxPrice() {
        return By.xpath(".//input[@data-testid='Maximum-price']");
    }

    public By getButtonSubmitPrice() {
        return By.xpath(".//button[@data-testid='submit-price']");
    }

    public By getTagFilteredByName(String filterName) {
        return By.xpath(".//div[contains(@class,'tag')]//div[contains(text(), '" + filterName + "')]");
    }

    public By getTagFilterMaxPrice() {
        return By.xpath(".//div[contains(@class,'tag')]//div[contains(text(), 'Hasta $')]");
    }

    public By getImgResults() {
        return By.xpath(".//ol[contains(@class,'search')]//a//img");
    }

    public By getDescriptionPdpContent() {
        return By.xpath(".//p[contains(@class,'pdp-description') and contains(@class,'content')]");
    }

    public String pathSearchsStrings() {
        return "src/test/java/utils/search-strings.json";
    }
}
