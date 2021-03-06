package StepDefinitionDataTable;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.maven.lifecycle.internal.LifecycleStarter;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class StepsDataTable {

    private WebDriver driver;

    private By searchBoxLocator = By.id("search_query_top");
    private By searchBtnLocator = By.name("submit_search");
    private By resultTextLocator = By.cssSelector("span.heading-counter");


    @Given("Abre el web browser Chrome y direcciona a la aplicacion$")
    public void open_the_web_browser() throws Throwable {

        System.setProperty("webdriver.chrome.driver", "E:\\Cursos\\2021\\SINERGIASS\\TestingBA\\herramientas\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://automationpractice.com/index.php");

    }

    @When("Ingreso el nombre del producto a buscar:$")
    public void nombre_del_producto_buscar_table(DataTable tabla) throws  Throwable
    {
        List<List<String>> rows = tabla.asLists(String.class);

        List<List<String>> rowsWithHead = rows.subList(1, rows.size());

        for (List<String> row : rowsWithHead)
        {

            driver.findElement(searchBoxLocator).clear();

            driver.findElement(searchBoxLocator).sendKeys(row.get(0));

            driver.findElement(searchBtnLocator).click();

        }


    }

    @Then("Visualizo resultado de la busqueda$")
    public void visualizo_resultados()throws Throwable
    {
        String resulttext = driver.findElement(resultTextLocator).getText();

        System.out.println("Resultado:" + resulttext);
    }

    @And("Cierro el Browser$")
    public void Quit() throws Throwable {

        driver.close();

    }


}
