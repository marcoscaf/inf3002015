package br.com.unicamp.inf321;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.event.Observer;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.Result;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.com.unicamp.inf321.helper.GraphWalkerTestBuilder;
import br.com.unicamp.inf321.helper.Helper;
import br.com.unicamp.inf321.models.comprefacil.BuscarProdutoTest;
import br.com.unicamp.inf321.observers.GraphStreamObserver;

public class CompreFacilTestRun {

	public final static Path MODEL_PATH = Paths
			.get("/br/com/unicamp/inf321/BuscarProduto.graphml");

	@Before
	public void beforeTest() {

	}

	@AfterClass
	public static void afterClass() {
		Helper.getInstance().close();
	}

	@Test
	public void runTests() {

		Result result = new GraphWalkerTestBuilder().addModel(
				MODEL_PATH,
				new BuscarProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
		// .addModel(
		// MODEL_PATH_2,
		// new BuscarProdutoTest().setPathGenerator(new AStarPath(
		// new ReachedVertex("v_PesquisarProduto"))))

				// .addModel(
				// MODEL_PATH_3,
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
