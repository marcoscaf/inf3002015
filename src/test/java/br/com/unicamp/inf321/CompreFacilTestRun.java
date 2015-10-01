package br.com.unicamp.inf321;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.assertj.core.api.Assertions;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.Result;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import br.com.unicamp.inf321.helper.GraphWalkerTestBuilder;
import br.com.unicamp.inf321.helper.Helper;
import br.com.unicamp.inf321.models.comprefacil.BuscarProdutoTest;

public class CompreFacilTestRun {

	public final static Path MODEL_PATH = Paths.get("/br/com/unicamp/inf321/BuscarProduto.graphml");
	public final static Path MODEL_PATH_2 = Paths.get("/br/com/unicamp/inf321/FinalizarCompra.graphml");
	@Before
	public void beforeTest() {

	}

	@AfterClass
	public static void afterClass() {
		Helper.getInstance().close();
	}

	@Test
	public void runTests() {

		Result result = new GraphWalkerTestBuilder()
		.addModel(MODEL_PATH,
				new BuscarProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
		.addModel(MODEL_PATH_2,
				new FinalizarCompraTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
		// .addModel(MODEL_PATH_3,
			// new BuscarProdutoTest().setPathGenerator(new AStarPath(
			// new ReachedVertex("v_PesquisarProduto"))))
		// .addModel(
			// MODEL_PATH_4,
			// new BuscarProdutoTest().setPathGenerator(new AStarPath(
			// new ReachedVertex("v_PesquisarProduto"))))
		// .addModel(
			// MODEL_PATH_5,
			// new BuscarProdutoTest().setPathGenerator(new AStarPath(
			// new ReachedVertex("v_PesquisarProduto"))))

				.execute(true);
		Assertions.assertThat(result.getErrors())
				.as("Errors: [" + result.getErrors().toString() + "]")
				.isNullOrEmpty();
	}

	// @Test
	// public void runStabilityTest() {
	// Result result = new GraphWalkerTestBuilder().addModel(
	// MODEL_PATH,
	// new BuscarProdutoTest().setPathGenerator(new RandomPath(
	// new TimeDuration(30, TimeUnit.SECONDS)))).execute(true);
	// Assertions.assertThat(result.getErrors())
	// .as("Errors: [" + result.getErrors().toString() + "]")
	// .isNullOrEmpty();
	// }

}
