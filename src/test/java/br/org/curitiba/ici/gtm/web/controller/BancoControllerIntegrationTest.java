package br.org.curitiba.ici.gtm.web.controller;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import br.org.curitiba.ici.gtm.web.controller.response.PessoaResponse;
import io.restassured.http.ContentType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BancoControllerIntegrationTest {
	@LocalServerPort 
	private int port;
	
	private String url; 


	@BeforeEach
	void before() {
		url = "http://localhost:" + port + "/bancos";
	}
	
	
	@Test 
	void getTest() { 
		List<PessoaResponse> bancos = given()
		        .contentType(ContentType.JSON)
		        .when()
		        .get(url)
		        .then()
		        .extract()
		        .jsonPath().get("_embedded.bancos");
		assertThat(bancos).isNotEmpty();

	} 

}
