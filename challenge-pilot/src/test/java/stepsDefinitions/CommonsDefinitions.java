package stepsDefinitions;

import io.cucumber.java.es.Dado;
import pageMethods.CommonsMethods;

public class CommonsDefinitions {
    CommonsMethods commonsMethods = new CommonsMethods();
    @Dado("Que el usuario accede a la home de mercado libre")
    public void goToML() {
        commonsMethods.goToBaseUrl();
    }
}
