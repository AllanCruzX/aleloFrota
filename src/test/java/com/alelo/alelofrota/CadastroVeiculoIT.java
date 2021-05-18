package com.alelo.alelofrota;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import com.alelo.alelofrota.domain.model.Fabricante;
import com.alelo.alelofrota.domain.model.Modelo;
import com.alelo.alelofrota.domain.model.Veiculo;
import com.alelo.alelofrota.domain.repository.FabricanteRepository;
import com.alelo.alelofrota.domain.repository.ModeloRepository;
import com.alelo.alelofrota.domain.repository.VeiculoRepository;
import com.alelo.alelofrota.util.DatabaseCleaner;
import com.alelo.alelofrota.util.ResourceUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource("/application-test.properties")
public class CadastroVeiculoIT {

	private static final int VEICULO_ID_INEXISTENTE = 100;

	@LocalServerPort
	private int port;

	@Autowired
	private DatabaseCleaner databaseCleaner;

	@Autowired
	private VeiculoRepository veiculoRepository;

	@Autowired
	private FabricanteRepository fabricanteRepository;

	@Autowired
	private ModeloRepository ModeloRepository;

	private Veiculo veiculoBmw;
	private String jsonCorretoVeiculoMercedes;

	@Before
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = port;
		RestAssured.basePath = "/veiculos";

		jsonCorretoVeiculoMercedes = ResourceUtils.getContentFromResource("/json/correto/veiculo-mercedes.json");

		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_QuandoConsultarVeiculos() {
		given().accept(ContentType.JSON).when().get().then().statusCode(HttpStatus.OK.value());
	}
	
	
	@Test
	public void deveRetornarStatus201_QuandoCadastrarVeiculo() {
		given()
			.body(jsonCorretoVeiculoMercedes)
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarRespostaEStatusCorretos_QuandoConsultarVeiculoExistente() {
		given()
			.pathParam("veiculoId", veiculoBmw.getId())
			.accept(ContentType.JSON)
		.when()
			.get("/{veiculoId}")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("placa", equalTo(veiculoBmw.getPlaca()));
	}
	
	@Test
	public void deveRetornarStatus404_QuandoConsultarVeiculoInexistente() {
		given()
			.pathParam("veiculoId", VEICULO_ID_INEXISTENTE)
			.accept(ContentType.JSON)
		.when()
			.get("/{veiculoId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}

	private void prepararDados() {
		Fabricante fabricanteMercedes = new Fabricante();
		fabricanteMercedes.setNome("Mercedes-Benz");

		Fabricante fabricanteBmw = new Fabricante();
		fabricanteBmw.setNome("Bmw");

		List<Fabricante> fabricantes = new ArrayList<Fabricante>();
		fabricantes = Arrays.asList(fabricanteMercedes ,fabricanteBmw );
		
		fabricanteRepository.saveAll(fabricantes);

		Modelo modeloC = new Modelo();
		modeloC.setNome("Class C 1.1 Avantgarde Turbo Flex");
		modeloC.setFabricante(fabricanteMercedes);
		
		Modelo modeloI8 = new Modelo();
		modeloI8.setNome("Bmw i8");
		modeloI8.setFabricante(fabricanteBmw);
		
		List<Modelo> modelos = new ArrayList<Modelo>();
		modelos = Arrays.asList(modeloC ,modeloI8 );
		ModeloRepository.saveAll(modelos);

		veiculoBmw = new Veiculo();
		veiculoBmw.setPlaca("ABC456");
		veiculoBmw.setStatus(true);
		veiculoBmw.setModelo(modeloI8);
		
		veiculoRepository.save(veiculoBmw);
		
	}

}
