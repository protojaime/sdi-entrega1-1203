package com.uniovi.tests;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

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

public class NotaneitorTests {
	
	
	
	
	

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

//PR04. OPción de navegación. Cambio de idioma de Español a Ingles y vuelta a Español
@Test
public void PR04() {
PO_HomeView.checkChangeIdiom(driver, "btnSpanish", "btnEnglish", 
PO_Properties.getSPANISH(), PO_Properties.getENGLISH());
//SeleniumUtils.esperarSegundos(driver, 2);
}


//PR05. Prueba del formulario de registro. registro con datos correctos
@Test
public void PR05() {
//Vamos al formulario de registro
PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail11@Gmail.com", "Josefo", "Perez", "123456", 
"123456");
//Comprobamos que entramos en la sección privada
PO_View.checkElement(driver, "text", "Notas del usuario");
}



//PR06. Prueba del formulario de registro. DNI repetido en la BD, Nombre corto, .... pagination pagination-centered, Error.signup.dni.length
@Test
public void PR06() {
//Vamos al formulario de registro
	PO_NavView.clickOption(driver, "signup", "class", "btn btn-primary");
//Rellenamos el formulario.
PO_RegisterView.fillForm(driver, "testEmail11@Gmail.com", "Josefo", "Perez", "123456", 
"123456");
PO_View.getP();
//COmprobamos el error de DNI repetido.
PO_RegisterView.checkKey(driver, "Error.signup.email.duplicate", 
PO_Properties.getSPANISH() );
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
//PRN. Loguearse con exito desde el ROl de Usuario, 99999990D, 123456
@Test
public void PR07() {
//Vamos al formulario de logueo.
PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
}

@Test
public void PR08() {
//Vamos al formulario de logueo.
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
}



@Test
public void PR09() {
//Vamos al formulario de logueo.
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
}



@Test
public void PR10() {
//Vamos al formulario de logueo.
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario erroneamwente
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "fail" );
//COmprobamos que segimos en la pagina de logeo
PO_View.checkElement(driver, "text", "Identifícate");
}



@Test
public void PR11() {
//Vamos al formulario de logueo.
	PO_NavView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "testEmail1@Gmail.com" , "123456" );
//COmprobamos que entramos en la pagina privada de Alumno
PO_View.checkElement(driver, "text", "Gestión de productos");
//SALIMOS
PO_NavView.clickOption(driver, "logout", "class", "btn btn-primary");
//COmprobamos que entramos en la pagina de login de nuevo
PO_View.checkElement(driver, "text", "Identifícate");
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
PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
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
By enlace = By.xpath("//td[contains(text(), 'Nota A2')]/following￾-sibling::*[2]");
driver.findElement(enlace).click();
SeleniumUtils.esperarSegundos(driver, 1);
//Esperamos por la ventana de detalle
PO_View.checkElement(driver, "text", "Detalles de la nota"); 
SeleniumUtils.esperarSegundos(driver, 1);
//Ahora nos desconectamos
PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
}


//P14. Loguearse como profesor y Agregar Nota A2.
//P14. Esta prueba podría encapsularse mejor ... 
@Test
public void PR14() {
//Vamos al formulario de logueo.
PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
//Rellenamos el formulario
PO_LoginView.fillForm(driver, "99999993D" , "123456" );
//COmprobamos que entramos en la pagina privada del Profesor
PO_View.checkElement(driver, "text", "99999993D");
//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
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
PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
}
//PRN. Loguearse como profesor, vamos a la ultima página y Eliminamos la Nota Nueva 1.
//PRN. Ver la lista de Notas.
@Test
public void PR15() {
	//Vamos al formulario de logueo.
	PO_LoginView.clickOption(driver, "login", "class", "btn btn-primary");
	//Rellenamos el formulario
	PO_LoginView.fillForm(driver, "99999993D" , "123456" );
	//COmprobamos que entramos en la pagina privada del Profesor
	PO_View.checkElement(driver, "text", "99999993D");
	//Pinchamos en la opción de menu de Notas: //li[contains(@id, 'marks-menu')]/a
	List<WebElement> elementos = PO_View.checkElement(driver, "free", "//li[contains(@id, 'marks-menu')]/a");
	elementos.get(0).click();
	//Pinchamos en la opción de lista de notas.
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@href, 'mark/list')]");
	elementos.get(0).click();
	//Esperamos a que se muestren los enlaces de paginacion la lista de notas
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page-￾link')]");
	//Nos vamos a la última página
	elementos.get(3).click();
	//Esperamos a que aparezca la Nueva nota en la ultima pagina
	//Y Pinchamos en el enlace de borrado de la Nota "Nota Nueva 1"
	//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(text(), 'mark/delete')]"
	elementos = PO_View.checkElement(driver, "free", "//td[contains(text(), 'Nota Nueva 1')]/following-sibling::*/a[contains(@href, 'mark/delete')]");
	elementos.get(0).click();
	//Volvemos a la última pagina
	elementos = PO_View.checkElement(driver, "free", "//a[contains(@class, 'page￾-link')]");
	elementos.get(3).click();
	//Y esperamos a que NO aparezca la ultima "Nueva Nota 1"
	SeleniumUtils.EsperaCargaPaginaNoTexto(driver, "Nota Nueva 1",PO_View.getTimeout() );
	//Ahora nos desconectamos
	PO_PrivateView.clickOption(driver, "logout", "text", "Identifícate");
	}






}