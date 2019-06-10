package com.uniovi.tests.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.uniovi.tests.util.SeleniumUtils;
public class PO_PrivateView extends PO_NavView{
static public void fillFormAddProduct(WebDriver driver,  String descriptionp, 
String detalles, int costp)
{
 
//Esperamos 5 segundo a que carge el DOM porque en algunos equipos falla
 
SeleniumUtils.esperarSegundos(driver, 5);
 
//Rellenemos el campo de descripción
 
WebElement description = driver.findElement(By.name("name"));
description.click();
description.clear();
description.sendKeys(descriptionp);
WebElement score = driver.findElement(By.name("description"));
score.click();
score.clear();
score.sendKeys(detalles);

WebElement cost = driver.findElement(By.name("price"));
cost.click();
cost.clear();
cost.sendKeys(Integer.toString(costp));
By boton = By.className("btn");
driver.findElement(boton).click();
}
}
