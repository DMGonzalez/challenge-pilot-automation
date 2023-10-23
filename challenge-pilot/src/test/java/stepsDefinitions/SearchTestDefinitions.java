package stepsDefinitions;

import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import pageMethods.SearchTestMethods;

import java.io.IOException;

public class SearchTestDefinitions {
    SearchTestMethods searchTestMethods = new SearchTestMethods();

    @Cuando("Realiza una búsqueda con filtros")
    public void searchProduct() throws IOException {
        searchTestMethods.searchProduct();
    }

    @Entonces("Visualiza el tercer resultado obtenido en la búsqueda")
    public void viewThirdResult() {
        searchTestMethods.viewThirdResult();
    }
}
