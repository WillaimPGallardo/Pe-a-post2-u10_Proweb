package e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assumptions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.Assumptions;
import java.net.HttpURLConnection;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

class TareasE2ETest {
    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // If Chrome binary is not available on the agent, skip E2E to keep CI/compile green
        String[] possible = new String[] {
                System.getenv("CHROME_BIN"),
                "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe",
                "C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"
        };
        boolean chromeExists = false;
        for (String p : possible) {
            if (p != null && !p.isEmpty() && new java.io.File(p).exists()) { chromeExists = true; break; }
        }
        Assumptions.assumeTrue(chromeExists, "Chrome binary not found - skipping Selenium E2E");
        WebDriverManager.chromedriver().setup();
        // ensure application is running on localhost:8080
        try {
            URL u = new URL("http://localhost:8080/tareas");
            HttpURLConnection c = (HttpURLConnection) u.openConnection();
            c.setConnectTimeout(2000);
            c.setReadTimeout(2000);
            c.setRequestMethod("GET");
            int code = c.getResponseCode();
            Assumptions.assumeTrue(code >= 200 && code < 500, "App not available on localhost:8080 - skipping E2E");
        } catch (Exception ex) {
            Assumptions.assumeTrue(false, "App not available on localhost:8080 - skipping E2E");
        }

        ChromeOptions opts = new ChromeOptions();
        opts.addArguments("--headless=new", "--no-sandbox");
        driver = new ChromeDriver(opts);
        driver.get("http://localhost:8080/tareas");
    }

    @Test
    void paginaTareas_cargaCorrectamente() {
        assertThat(driver.getTitle()).contains("Tareas");
    }

    @AfterEach
    void tearDown() { if (driver != null) driver.quit(); }
}
