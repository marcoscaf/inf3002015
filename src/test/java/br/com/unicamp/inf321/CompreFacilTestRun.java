package br.com.unicamp.inf321;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.assertj.core.api.Assertions;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.event.Observer;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.Result;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import br.com.unicamp.inf321.helper.GraphWalkerTestBuilder;
import br.com.unicamp.inf321.helper.Helper;
import br.com.unicamp.inf321.models.comprefacil.AdicionarProdutoCarrinhoTest;
import br.com.unicamp.inf321.models.comprefacil.BuscarProdutoTest;
import br.com.unicamp.inf321.observers.GraphStreamObserver;


public class CompreFacilTestRun {

	public final static Path MODEL_PATH_1 = Paths.get("/br/com/unicamp/inf321/BuscarProdutoSHARED.graphml");
	public final static Path MODEL_PATH_2 = Paths.get("/br/com/unicamp/inf321/AdicionarProdutoCarrinhoSHARED.graphml");
	public final static Path MODEL_PATH_3 = Paths.get("/br/com/unicamp/inf321/FinalizarCompra.graphml");
	
	@Rule
	public TestName testName = new TestName();
	private Graph graph;
	private Observer observer;
	
	@Before
	public void beforeTest() {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		graph = new SingleGraph("GraphWalker");
		graph.display(true);
		observer = new GraphStreamObserver(graph);
	}

	@AfterClass
	public static void afterClass() {
		Helper.getInstance().close();
	}

	@Test
	public void runFunctionalTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1, new BuscarProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.addModel(MODEL_PATH_2, new AdicionarProdutoCarrinhoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
//				.addModel(MODEL_PATH_3, new Veterinariens().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
//				.addModel(MODEL_PATH_4, new OwnerInformation().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
//				.addModel(MODEL_PATH_5, new NewOwner().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}
	
	//@Test
	public void runTests() {

		Result result = new GraphWalkerTestBuilder()
//		.addModel(MODEL_PATH_1, new BuscarProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
//		.addModel(MODEL_PATH_2, new AdicionarProdutoCarrinhoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
//		.addModel(MODEL_PATH_3, new FinalizarCompraTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
		
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
