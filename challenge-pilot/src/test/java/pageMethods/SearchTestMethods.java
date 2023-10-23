package pageMethods;

import io.qameta.allure.Allure;
import org.json.JSONObject;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import pageObjects.SearchTestPage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SearchTestMethods {
    SearchTestPage searchTestPage = new SearchTestPage();
    CommonsMethods commonsMethods = new CommonsMethods();
    public JSONObject stringSearch;

    public void searchProduct() throws IOException {
        String filePath = searchTestPage.pathSearchsStrings();
        String jsonContent = new String(Files.readAllBytes(Paths.get(filePath)));
        stringSearch = new JSONObject(jsonContent);

        commonsMethods.elementWaitDisplayed(searchTestPage.getInputSearchbox());
        commonsMethods.elementSendText(searchTestPage.getInputSearchbox(), stringSearch.getString("producto"));
        commonsMethods.elementWaitDisplayed(searchTestPage.getSubmitSearchButton());
        commonsMethods.elementClick(searchTestPage.getSubmitSearchButton());

        String brandFilter = stringSearch.getString("marca");

        commonsMethods.elementWaitForPresence(searchTestPage.getFilterByBrand(brandFilter));
        commonsMethods.scrollToElement(searchTestPage.getFilterByBrand(brandFilter));
        commonsMethods.elementWaitDisplayed(searchTestPage.getFilterByBrand(brandFilter));
        commonsMethods.elementClick(searchTestPage.getFilterByBrand(brandFilter));
        commonsMethods.elementWaitDisplayed(searchTestPage.getTagFilteredByName(brandFilter));


        String material = stringSearch.getString("material");

        commonsMethods.elementWaitForPresence(searchTestPage.getFilterByMaterial(material));
        commonsMethods.scrollToElement(searchTestPage.getFilterByMaterial(material));
        commonsMethods.elementWaitDisplayed(searchTestPage.getFilterByMaterial(material));
        commonsMethods.elementClick(searchTestPage.getFilterByMaterial(material));
        commonsMethods.elementWaitDisplayed(searchTestPage.getTagFilteredByName(material));

        String maxPrice = String.valueOf(stringSearch.getInt("precio-max"));

        commonsMethods.elementWaitForPresence(searchTestPage.getInputMaxPrice());
        commonsMethods.scrollToElement(searchTestPage.getInputMaxPrice());
        commonsMethods.elementWaitDisplayed(searchTestPage.getInputMaxPrice());
        commonsMethods.elementSendText(searchTestPage.getInputMaxPrice(), maxPrice);
        commonsMethods.elementWaitDisplayed(searchTestPage.getButtonSubmitPrice());
        commonsMethods.elementClick(searchTestPage.getButtonSubmitPrice());
        commonsMethods.elementWaitDisplayed(searchTestPage.getTagFilterMaxPrice());
    }

    public void viewThirdResult() {
        commonsMethods.elementWaitDisplayed(searchTestPage.getImgResults());
        commonsMethods.scrollToElement(commonsMethods.castToList(searchTestPage.getImgResults()).get(2));
        commonsMethods.elementClick(commonsMethods.castToList(searchTestPage.getImgResults()).get(2));

        commonsMethods.elementWaitDisplayed(searchTestPage.getDescriptionPdpContent());
        commonsMethods.scrollToElement(searchTestPage.getDescriptionPdpContent());

        Assert.assertNotNull(commonsMethods.getElementText(searchTestPage.getDescriptionPdpContent()));
        Allure.addAttachment("La descripcion del producto es: ", commonsMethods.getElementText(searchTestPage.getDescriptionPdpContent()));
        commonsMethods.takeScreenshots("Se visualiza la descripcion correctamente");
    }
}
