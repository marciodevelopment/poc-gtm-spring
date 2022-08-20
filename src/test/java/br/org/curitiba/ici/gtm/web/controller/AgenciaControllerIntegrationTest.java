package br.org.curitiba.ici.gtm.web.controller;



import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import br.org.curitiba.ici.gtm.web.controller.request.AtualizacaoAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.request.NovaAgenciaRequest;
import br.org.curitiba.ici.gtm.web.controller.response.AgenciaResponse;
import io.restassured.http.ContentType;



@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgenciaControllerIntegrationTest {
	private static Integer codPessoaCriada = 7;
	private String url; 
	
	@LocalServerPort 
	private int port;
	
	@BeforeEach
	void before() {
		url = "http://localhost:" + port + "/agencias";
	}
	
	@Order(1)
	@Test
	void deveSalvarUmaNovaAgencia() {
		NovaAgenciaRequest agenciRequest = new NovaAgenciaRequest(codPessoaCriada, 2, 998, "A", true);
		given()
		.contentType(ContentType.JSON)
        .body(agenciRequest)
          .when()
          .post(url)
          .then()
          .statusCode(HttpStatus.CREATED.value());
    }
	
	@Test
	void naoDeveSalvarNovaAgenciaParaPessoaExistente() {
        NovaAgenciaRequest agenciRequest = new NovaAgenciaRequest(1, 2, 998, "A", true);
        given()
        .contentType(ContentType.JSON)
        .body(agenciRequest)
          .when()
          .post(url)
          .then()
          .statusCode(HttpStatus.BAD_REQUEST.value());
    }
	
	
	@Test
	void deveAtualizarUmaAgencia() {
		AtualizacaoAgenciaRequest atualizacao = new AtualizacaoAgenciaRequest(1, 
				2, 
				"A", 
				false);
		 given()
		 	.contentType(ContentType.JSON)
	        .and()
	        .body(atualizacao)
	        .put(url + "/cod-pessoa/2")
	        .then()
	        .statusCode(HttpStatus.OK.value());
    }
    
	
	@Test
	void naoDeveAtualizarUmaAgenciaNaoExistente() {
		AtualizacaoAgenciaRequest atualizacao = new AtualizacaoAgenciaRequest(1, 
				2, 
				"A", 
				false);
		 given()
		 	.contentType(ContentType.JSON)
	        .and()
	        .body(atualizacao)
	        .put(url + "/cod-pessoa/-1")
	        .then()
	        .statusCode(HttpStatus.NOT_FOUND.value());
    }
	
	@Order(2)
	@Test
	void deveDeletarUmaAgencia() {
		given()
		.contentType(ContentType.JSON)
		.when()
        .delete(url + "/cod-pessoa/" + codPessoaCriada)
        .then()
        .statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	void naoDeveDeletarUmaAgenciaInexistente() {
		given()
		.contentType(ContentType.JSON)
		.when()
        .delete(url + "/cod-pessoa/-1")
        .then()
        .statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
	@Test
	void deveEncontrarUmaPessoaParaPrimeiraPaginaDeTamanho1() {
		@SuppressWarnings("unchecked")
		ArrayList<AgenciaResponse> agencias = given()
        .contentType(ContentType.JSON)
        .when()
        .queryParam("page", 0)
        .queryParam("pageSize", 1)
        .get(url)
        .then()
        .extract().response().as(new ArrayList<>().getClass());
		Assertions.assertEquals(1, agencias.size());
	}
	

}
