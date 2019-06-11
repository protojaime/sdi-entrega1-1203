package com.uniovi.tests;


import org.junit.FixMethodOrder;

import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.tests.Sdi1203LabSpringApplicationTests;


//Ordenamos las pruebas por el nombre del método
@FixMethodOrder( MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
public class Sdi1203LabSpringApplicationTestsRemote extends Sdi1203LabSpringApplicationTests {
	static String URL = "http://localhost:8090";
}

