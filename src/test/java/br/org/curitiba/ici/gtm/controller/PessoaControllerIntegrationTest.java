package br.org.curitiba.ici.gtm.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import br.org.curitiba.ici.gtm.controller.response.PessoaResponse;

// https://marco.dev/spring-boot-test-controller

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PessoaControllerIntegrationTest {
	@LocalServerPort 
	private int port;

	@Autowired 
	private TestRestTemplate restTemplate; 

	@Test 
	void getTest() { 
		@SuppressWarnings("unchecked")
		List<PessoaResponse> pessoas = restTemplate 
		.getForObject("http://localhost:" + port + "/pessoas", List.class);
		assertThat(pessoas).isNotEmpty();

	} 

}
