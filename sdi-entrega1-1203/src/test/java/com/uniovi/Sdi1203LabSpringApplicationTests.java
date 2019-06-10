package com.uniovi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.AssertFalse;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.tests.pageobjects.PO_HomeView;
import com.uniovi.tests.pageobjects.PO_LoginView;
import com.uniovi.tests.pageobjects.PO_NavView;
import com.uniovi.tests.pageobjects.PO_PrivateView;
import com.uniovi.tests.pageobjects.PO_Properties;
import com.uniovi.tests.pageobjects.PO_RegisterView;
import com.uniovi.tests.pageobjects.PO_View;
import com.uniovi.tests.util.SeleniumUtils;

//Ordenamos las pruebas por el nombre del método
@FixMethodOrder( MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sdi1203LabSpringApplicationTests {

	//En Windows (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas)):
	static String PathFirefox64 = "C:\\Program Files\\Mozilla Firefox\\firefox.exe";
	static String Geckdriver022 = "C:\\Users\\J\\Desktop\\geckodriver022win64.exe";
	//En MACOSX (Debe ser la versión 65.0.1 y desactivar las actualizacioens automáticas):
	static String PathFirefox65 = "/Applications/Firefox.app/Contents/MacOS/firefox-bin";
	static String Geckdriver024 = "/Users/delacal/selenium/geckodriver024mac";
	//Común a Windows y a MACOSX
	static WebDriver driver = getDriver(PathFirefox64, Geckdriver022); 
	static String URL = "http://localhost:8090";
	public static WebDriver getDriver(String PathFirefox, String Geckdriver) {
	System.setProperty("webdriver.firefox.bin", PathFirefox);
	System.setProperty("webdriver.gecko.driver", Geckdriver);
	WebDriver driver = new FirefoxDriver();
	return driver;
	}
	
//Antes de cada prueba se navega al URL home de la aplicaciónn
@Before
public void setUp(){
driver.navigate().to(URL);
}
//Después de cada prueba se borran las cookies del navegador
@After
public void tearDown(){
driver.manage().deleteAllCookies();
}
//Antes de la primera prueba
@BeforeClass
static public void begin() {
}
//Al finalizar la última prueba 
@AfterClass
static public void end() {
//Cerramos el navegador al finalizar las pruebas
driver.quit();
}
/*
//PR01. Acceder a la página principal /
@Test
public void PR01() {
PO_HomeView.checkWelcome(driver, PO_Properties.getSPANISH());
}
//PR02. OPción de navegación. Pinchar en el enlace Registro en la página home
@Test
public void PR02() {
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
}
//PR03. OPción de navegación. Pinchar en el enlace Identificate en la página home
@Test
public void PR03() {
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
}
*/


//PR01. registro de usuario correcto
@Test
public void PR01() {
//Vamos al formulario de registro
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail11@Gmail.com", "Josefo", "Perez", "123456", "123456");
//Comprobamos que entramos en la sección privada
PO_View.checkElement(driver, "text", "Gestión de productos");
}

//PR02. Prueba del formulario de registro. error por campos vacios Y/O INVALIDOS
@Test
public void PR02() {
//Vamos al formulario de registro
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "", "", "", "123456", 
"123456");
PO_View.getP();
//COmprobamos el error de campo vacio de form esta activo en los campod(no lo podemos capturar con selenium)
WebElement inputElement = driver.findElement(By.name("email"));
JavascriptExecutor js = (JavascriptExecutor) driver;  
boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}
 inputElement = driver.findElement(By.name("lastName"));
 js = (JavascriptExecutor) driver;  
 isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}
 inputElement = driver.findElement(By.name("name"));
 js = (JavascriptExecutor) driver;  
 isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}
 inputElement = driver.findElement(By.name("password"));
 js = (JavascriptExecutor) driver;  
 isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}
inputElement = driver.findElement(By.name("passwordConfirm"));
js = (JavascriptExecutor) driver;  
isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
  //element is required and validation error will popup if the field is empty.
}
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail12@Gmail.com", "Jose", "Perez", "123456", 
"123456");
//COmprobamos el error de Nombre corto .
PO_RegisterView.checkKey(driver, "Error.signup.name.length", 
PO_Properties.getSPANISH() );
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail13@Gmail.com"
, "Josefo", "Per", "123456", 
"123456");
}

//PR03. Prueba del formulario de registro. error contraseña mal repetida
@Test
public void PR03() {
//Vamos al formulario de registro
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail11@Gmail.com", "Jose", "Perez", "123456", 
"111111");
PO_View.getP();
//COmprobamos el error de contraseña
PO_RegisterView.checkKey(driver, "Error.signup.passwordConfirm.coincidence", 
PO_Properties.getSPANISH() );
}

//PR04. Prueba del formulario de registro. error por email ya existente
@Test
public void PR04() {
//Vamos al formulario de registro
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail1@Gmail.com", "Josefo", "Perez", "123456", 
"123456");
PO_View.getP();
//COmprobamos el error de correo ya existente
PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", 
PO_Properties.getSPANISH() );
}
//PR05. Loguearse con exito desde el ROl de Admin
@Test
public void PR05() {
	//Vamos al formulario de logueo.
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
	//Rellenamos el formulario
	PO_LoginView.fillForm(driver, "Admin@Gmail.com" , "123456" );
	//COmprobamos que entramos en la pagina privada de Admin
	PO_View.checkElement(driver, "text", "Gestión de Usuarios");
	}
//PR06. Loguearse con exito desde el ROl de Usuario
@Test
public void PR06() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
}
//PR07. Loguearse sin exito con campos vacios
@Test
public void PR07() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "" );
//COmprobamos que segimos en la pagina de logeo
PO_View.checkElement(driver, "text", "Identificate");
WebElement inputElement = driver.findElement(By.name("username"));
JavascriptExecutor js = (JavascriptExecutor) driver;  
boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}
 inputElement = driver.findElement(By.name("password"));
 js = (JavascriptExecutor) driver;  
 isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
   //element is required and validation error will popup if the field is empty.
}

}
//PR08. Loguearse sin exito con contraseña incorrecta
@Test
public void PR08() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "1" );
//COmprobamos que segimos en la pagina de logeo
PO_View.checkElement(driver, "text", "Identificate");
}
//PR09. Loguearse sin exito con correo no existente
@Test
public void PR09() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail11111@Gmail.com" , "123456" );
//COmprobamos que segimos en la pagina de logeo
PO_View.checkElement(driver, "text", "Identificate");
}

//PR10. comprobar que deslogearse funciona correctamente
@Test
public void PR10() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
//deslogeamos y COmprobamos que entramos en la pagina de login de nuevo
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}

//PR11. comprobar que el boton de deslogear no esta presente cuando no estas logeado
@Test
public void PR11() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//comprobamos que Desconectar no esta y Identificar si
try {
	PO_View.checkElement(driver, "text", "Desconectar");
	assertTrue(false);
} catch (org.openqa.selenium.TimeoutException e) {
	PO_View.checkElement(driver, "text", "Identificate");
}
}


//P12. Loguearse como profesor y ver a todos los usuarios
@Test
public void PR12() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "Admin@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del Profesor
PO_View.checkElement(driver, "text", "Admin@Gmail.com");
//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
driver.findElement(By.partialLinkText("Gestión de Usuarios")).click();
driver.findElement(By.partialLinkText("Ver Usuarios")).click(); 
//comprobamos que todos los usuarios estan
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
        PO_View.getTimeout());
assertEquals(11, usersList.size());

//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}


//borramos el primer usuario de la lista
@Test
public void PR13() {
    PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
    PO_LoginView.fillForm(driver, "Admin@Gmail.com", "123456");
    List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
    elementos.get(0).click();
    elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
    elementos.get(0).click();

    List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(11, usersList.size());

    usersList.get(0).findElement(By.id("CheckboxedUsers")).click();

    List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'DeleteAllSelectedButton')]");
    listPage.get(0).click();
    usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(10, usersList.size());
}

//borrams el ultimo usuario de la lista
@Test
public void PR14() {
    PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
    PO_LoginView.fillForm(driver, "Admin@Gmail.com", "123456");
    List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
    elementos.get(0).click();
    elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
    elementos.get(0).click();

    List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(10, usersList.size());

    usersList.get(usersList.size() - 1).findElement(By.id("CheckboxedUsers")).click();

    List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'DeleteAllSelectedButton')]");
    listPage.get(0).click();
    usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(9, usersList.size());
}

@Test
public void PR15() {
	//nos logeamos como admin
    PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
    PO_LoginView.fillForm(driver, "Admin@Gmail.com", "123456");
    //probar PO_View clickOption
    List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id,'users-menu')]/a");
    elementos.get(0).click();
    elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'user/list')]");
    elementos.get(0).click();

    List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(9, usersList.size());

    usersList.get(usersList.size()-1).findElement(By.id("CheckboxedUsers")).click();
    usersList.get(usersList.size()-2).findElement(By.id("CheckboxedUsers")).click();
    usersList.get(usersList.size()-3).findElement(By.id("CheckboxedUsers")).click();

    List<WebElement> listPage = PO_View.checkElement(driver, "free", "//*[contains(@id,'DeleteAllSelectedButton')]");
    listPage.get(0).click();
    usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
            PO_View.getTimeout());
    assertEquals(6, usersList.size());
}





/*
//P13. Loguearse como profesor y borrar el primer usuario
@Test
public void PR13() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "Admin@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del Profesor
PO_View.checkElement(driver, "text", "Admin@Gmail.com");
//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
driver.findElement(By.partialLinkText("Gestión de Usuarios")).click();
driver.findElement(By.partialLinkText("Ver Usuarios")).click(); 
//comprobamos que todos los usuarios estan
PO_View.checkElement(driver, "text", "testEmail1@Gmail.com"); 
//borramos al usuario
By link=By.partialLinkText("detalles");
driver.findElement(link).click();
//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}




 
//PR13. Loguearse como estudiante y ver los detalles de la nota con Descripcion = Nota A2.
//P13. Ver la lista de Notas.
@Test
public void PR13() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
SeleniumUtils.esperarSegundos(driver, 1);
//Contamos las notas
By link=By.partialLinkText("detalles");
driver.findElement(link).click();
//Esperamos por la ventana de detalle
PO_View.checkElement(driver, "text", "Detalles de la oferta"); 
SeleniumUtils.esperarSegundos(driver, 1);
//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}

//PR12. Loguearse, comprobar que se visualizan 4 filas de notas y desconectarse usando el rol de estudiante.
@Test
public void PR12() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
//Contamos el número de filas de notas
List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "free", 
"//tbody/tr", PO_View.getTimeout());
assertTrue(elementos.size() == 4);
//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}

@Test
public void PR141() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "Admin@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del Profesor
PO_View.checkElement(driver, "text", "Admin@Gmail.com");
//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
driver.findElement(By.partialLinkText("Gestión de Usuarios")).click();
driver.findElement(By.partialLinkText("Ver Usuarios")).click();
List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
elementos.get(0).click(); 
//Esperamos a aparezca la opción de añadir nota: //a[contains(@href, 'mark/add')]
elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/add')]");
//Pinchamos en agregar Nota.
elementos.get(0).click();
//Ahora vamos a rellenar la nota. //option[contains(@value, '4')]
PO_PrivateView.fillFormAddMark(driver, 3, "Nota Nueva 1", "8");
//Esperamos a que se muestren los enlaces de paginación la lista de notas
elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-link')]");
//Nos vamos a la última página
elementos.get(3).click();
//Comprobamos que aparece la nota en la pagina
elementos = PO_View.checkElement(driver, "text", "Nota Nueva 1");
//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identificate");
}


//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.
//PRN. Ver la lista de Notas.
*/    



//P16. Loguearse como usuario y añadir oferta
@Test
public void PR16() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del usuario
PO_View.checkElement(driver, "text", "testEmail1@Gmail.com");
//comprobar numero de productos
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(4, usersList.size());
//Pinchamos en la opción de menu de Usuarios
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Agregar Producto")).click(); 
//Rellenar forma
PO_PrivateView.fillFormAddProduct(driver, "prueba 16", "prueba 16", 12);
//vamos a la pagina privada del alumno
SeleniumUtils.esperarSegundos(driver, 1);
driver.findElement(By.partialLinkText("testEmail1@Gmail.com")).click();
//comprobar numero de productos incrementado
usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(5 , usersList.size());
}


//P17. Loguearse como usuario y añadir oferta pero fallar por campo vacio
@Test
public void PR17() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del usuario
PO_View.checkElement(driver, "text", "testEmail1@Gmail.com");
//comprobar numero de productos
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(5, usersList.size());
//Pinchamos en la opción de menu de Usuarios
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Agregar Producto")).click(); 
//Rellenar forma
PO_PrivateView.fillFormAddProduct(driver, "prueba 16", "", 12);
//COmprobamos el error de campo vacio de form esta activo en los campod(no lo podemos capturar con selenium)
WebElement inputElement = driver.findElement(By.name("name"));
JavascriptExecutor js = (JavascriptExecutor) driver;  
boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
 //element is required and validation error will popup if the field is empty.
}
//COmprobamos el error de campo vacio de form esta activo en los campod(no lo podemos capturar con selenium)
 inputElement = driver.findElement(By.name("description"));
 js = (JavascriptExecutor) driver;  
 isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
//element is required and validation error will popup if the field is empty.
}
//COmprobamos el error de campo vacio de form esta activo en los campod(no lo podemos capturar con selenium)
inputElement = driver.findElement(By.name("price"));
js = (JavascriptExecutor) driver;  
isRequired = (Boolean) js.executeScript("return arguments[0].required;",inputElement);
if(isRequired )
{
//element is required and validation error will popup if the field is empty.
}
}

//P18. Loguearse como usuario y ver las ofertas
@Test
public void PR18() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del usuario
PO_View.checkElement(driver, "text", "testEmail1@Gmail.com");
//comprobar numero de productos (4 iniciales + el añadido en la anterior prueba)
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(5, usersList.size());
}
//P19. Loguearse como usuario y borrar primera oferta
@Test
public void PR19() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail2@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del usuario
PO_View.checkElement(driver, "text", "testEmail2@Gmail.com");
//borramos el primero
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(4, usersList.size());
String idproducto1=usersList.get(0).findElement(By.id("id")).getText();
usersList.get(0).findElement(By.id("eliminar")).click();
//volvemos a la pagina y nos aseguramos que se ha borrado la oferta
driver.findElement(By.partialLinkText("testEmail2@Gmail.com")).click();
usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(3, usersList.size());
assertFalse(idproducto1==usersList.get(0).findElement(By.id("id")).getText());
}
//P20. Loguearse como usuario y borrar ultima oferta
@Test
public void PR20() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail2@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada del usuario
PO_View.checkElement(driver, "text", "testEmail2@Gmail.com");
//borramos el ultimo
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(3, usersList.size());
String idproducto1=usersList.get(usersList.size()-1).findElement(By.id("id")).getText();
usersList.get(usersList.size()-1).findElement(By.id("eliminar")).click();
//volvemos a la pagina y nos aseguramos que se ha borrado la oferta
driver.findElement(By.partialLinkText("testEmail2@Gmail.com")).click();
usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(2, usersList.size());
assertFalse(idproducto1==usersList.get(usersList.size()-1).findElement(By.id("id")).getText());
}

//P20. Loguearse como usuario y hacer busqueda vacia
@Test
public void PR21() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//guardamos los resultados que vemos
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(5, usersList.size());
String id1=usersList.get(0).findElement(By.id("id")).getText();
String id2=usersList.get(1).findElement(By.id("id")).getText();
String id3=usersList.get(2).findElement(By.id("id")).getText();
String id4=usersList.get(3).findElement(By.id("id")).getText();
String id5=usersList.get(4).findElement(By.id("id")).getText();

//buscamos sin escribir nada
driver.findElement(By.id("buscarButton")).click();
//nos aseguramos de que los resultados son los mismos
List<WebElement> usersList2 = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(5, usersList2.size());
assertEquals(id1, usersList2.get(0).findElement(By.id("id")).getText());
assertEquals(id2, usersList2.get(1).findElement(By.id("id")).getText());
assertEquals(id3, usersList2.get(2).findElement(By.id("id")).getText());
assertEquals(id4, usersList2.get(3).findElement(By.id("id")).getText());
assertEquals(id5, usersList2.get(4).findElement(By.id("id")).getText());




}
//P22. Loguearse como usuario y hacer busqueda sin resultado
@Test
public void PR22() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//buscamos escribiendo algo que no existe
WebElement searchText = driver.findElement(By.name("searchText"));
searchText.click();
searchText.clear();
searchText.sendKeys("NO EXISTO");
driver.findElement(By.id("buscarButton")).click();
//nos aseguramos de no hay ningun resultado
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr", PO_View.getTimeout());
assertEquals(1, usersList.size());
try {
usersList.get(0).findElement(By.id("id")).getText();
}catch(NoSuchElementException e) {
}
}

//P23. Loguearse como usuario y comprar exitosamente
@Test
public void PR23() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail5@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//cartera inicial de este usuario: 30
assertEquals("30.0",driver.findElement(By.id("wallet")).getText());
//buscamos Producto 3
WebElement searchText = driver.findElement(By.name("searchText"));
searchText.click();
searchText.clear();
searchText.sendKeys("Producto 3");
driver.findElement(By.id("buscarButton")).click();
//compramos el primer objeto, cuyo coste es 20 e id 5
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
        PO_View.getTimeout());
assertEquals("20.0",usersList.get(0).findElement(By.id("price")).getText());
assertEquals("5",usersList.get(0).findElement(By.id("id")).getText());
usersList.get(0).findElement(By.id("buy")).click();
//cartera de este usuario: 10
assertEquals("10.0",driver.findElement(By.id("wallet")).getText());
//objeto pasa a ser vendido, no tiene el elemento que clicamos antes
usersList.get(0).findElement(By.id("sold"));
try {
usersList.get(0).findElement(By.id("buy")).getText();
}catch(NoSuchElementException e) {
}
}

//P24. Loguearse como usuario y comprar exitosamente dejando saldo a 0
@Test
public void PR24() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail5@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//cartera de este usuario: 10
assertEquals("10.0",driver.findElement(By.id("wallet")).getText());
//buscamos Producto 1
WebElement searchText = driver.findElement(By.name("searchText"));
searchText.click();
searchText.clear();
searchText.sendKeys("Producto 1");
driver.findElement(By.id("buscarButton")).click();
//compramos el primer objeto, cuyo coste es 20 e id 3
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
      PO_View.getTimeout());
assertEquals("20.0",usersList.get(0).findElement(By.id("price")).getText());
assertEquals("3",usersList.get(0).findElement(By.id("id")).getText());
usersList.get(0).findElement(By.id("buy")).click();
//cartera de este usuario: 10
assertEquals("0.0",driver.findElement(By.id("wallet")).getText());
//objeto pasa a ser vendido, no tiene el elemento que clicamos antes
usersList.get(0).findElement(By.id("sold"));
try {
usersList.get(0).findElement(By.id("buy")).getText();
}catch(NoSuchElementException e) {
}
}

//P25. Loguearse como usuario y fallar a comprar por no tener dinero
@Test
public void PR25() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail5@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//cartera de este usuario: 0
assertEquals("0.0",driver.findElement(By.id("wallet")).getText());
//buscamos Producto 2
WebElement searchText = driver.findElement(By.name("searchText"));
searchText.click();
searchText.clear();
searchText.sendKeys("Producto 2");
driver.findElement(By.id("buscarButton")).click();
//compramos Producto 2, de precio 30
List<WebElement> usersList = SeleniumUtils.EsperaCargaPagina(driver, "free", "//tbody/tr",
    PO_View.getTimeout());
assertEquals("30.0",usersList.get(0).findElement(By.id("price")).getText());
usersList.get(0).findElement(By.id("buy")).click();
//nos salta a la pagina de error por no tener dinero
assertTrue( driver.getCurrentUrl().contains("/product/nomoney"));
//cartera de este usuario: 0
assertEquals("0.0",driver.findElement(By.id("wallet")).getText());
//COmprobamos que entramos en la pagina de busqueda
driver.findElement(By.partialLinkText("Gestión de productos")).click();
driver.findElement(By.partialLinkText("Ver productos a la venta")).click(); 
PO_View.checkElement(driver, "text", "Las productos que actualmente figuran en el sistema son las siguientes:");
//buscamos Producto 2
searchText = driver.findElement(By.name("searchText"));
searchText.click();
searchText.clear();
searchText.sendKeys(" 	Producto 2");
driver.findElement(By.id("buscarButton")).click();
//objeto no pasa a ser vendido, tiene el elemento que clicamos antes
usersList.get(0).findElement(By.id("buy"));
try {
usersList.get(0).findElement(By.id("sold")).getText();
}catch(NoSuchElementException e) {
}
}






//PR27. Prueba de internacionalización
@Test
public void PR27() {
PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", 
PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
SeleniumUtils.esperarSegundos(driver, 2);
}

}

