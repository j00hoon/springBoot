package com.springProj.pma.controller;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class HttpRequestTest 
{
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	// home page(index page)에 써있는 application의 version을 확인하고, 
	// application.properties에 설정되어 있는 값과 맞는지 확인하는 것이 이 test의 목적
	@Test
	public void homePageReturnsVersionNumberCorrectly_thenSuccess()
	{
		String renderedHtml = this.restTemplate.getForObject("http://localhost:" + port + "/", String.class);
		
		assertEquals(renderedHtml.contains("1.0.0"), true);
	}
}
