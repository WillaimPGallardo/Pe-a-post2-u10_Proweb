package e2e;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NuevaTareaPage {
    private final WebDriver driver;
    private final By inputDesc = By.id("desc");
    private final By btnGuardar = By.id("guardar");

    public NuevaTareaPage(WebDriver d) { this.driver = d; }

    public void escribirDescripcion(String texto) {
        driver.findElement(inputDesc).sendKeys(texto);
    }

    public void guardar() { driver.findElement(btnGuardar).click(); }
}
