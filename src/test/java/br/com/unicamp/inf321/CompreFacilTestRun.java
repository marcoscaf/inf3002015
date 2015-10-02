package br.com.unicamp.inf321;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.stream.file.FileSinkImages;
import org.graphstream.stream.file.FileSinkImages.LayoutPolicy;
import org.graphstream.stream.file.FileSinkImages.OutputType;
import org.graphstream.stream.file.FileSinkImages.RendererType;
import org.graphstream.stream.file.FileSinkImages.Resolutions;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.event.Observer;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.Result;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import br.com.unicamp.inf321.helper.GraphWalkerTestBuilder;
import br.com.unicamp.inf321.helper.Helper;
import br.com.unicamp.inf321.models.comprefacil.AdicionarProdutoCarrinhoTest;
import br.com.unicamp.inf321.models.comprefacil.BuscarProdutoTest;
import br.com.unicamp.inf321.models.comprefacil.EfetuarPagamentoTest;
import br.com.unicamp.inf321.models.comprefacil.FinalizarCompraTest;
import br.com.unicamp.inf321.models.comprefacil.RemoveProdutoTest;
import br.com.unicamp.inf321.observers.GraphStreamObserver;


public class CompreFacilTestRun {

	public final static Path MODEL_PATH_1 = Paths.get("/br/com/unicamp/inf321/BuscarProdutoSHARED.graphml");
	public final static Path MODEL_PATH_2 = Paths.get("/br/com/unicamp/inf321/AdicionarProdutoCarrinhoSHARED.graphml");
	public final static Path MODEL_PATH_3 = Paths.get("/br/com/unicamp/inf321/FinalizarCompraSHARED.graphml");
	public final static Path MODEL_PATH_4 = Paths.get("/br/com/unicamp/inf321/RemoverProdutoSHARED.graphml");
	public final static Path MODEL_PATH_5 = Paths.get("/br/com/unicamp/inf321/EfetuarPagamentoSHARED.graphml");

	
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
	
	@After
	public void afterTest() throws Exception {
		Helper.takeScreenshot("screenshots/" + CompreFacilTestRun.class.getSimpleName() + "_webdriver_" + testName.getMethodName() + ".png");
		FileSinkImages pic = new FileSinkImages(OutputType.JPG, Resolutions.HD720);
		pic.setLayoutPolicy(LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
		pic.setRenderer(RendererType.BASIC);
		pic.writeAll(graph, "screenshots/" + CompreFacilTestRun.class.getSimpleName() + "_graphstream_" + testName.getMethodName() + ".png");
	}

	@Test
	public void runFunctionalTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1, new BuscarProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.addModel(MODEL_PATH_2, new AdicionarProdutoCarrinhoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.addModel(MODEL_PATH_3, new FinalizarCompraTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.addModel(MODEL_PATH_4, new RemoveProdutoTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.addModel(MODEL_PATH_5, new EfetuarPagamentoTest().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}
	
	@Test
	public void runStabilityTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1,new BuscarProdutoTest().setPathGenerator(new RandomPath(new TimeDuration(5, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_2,new AdicionarProdutoCarrinhoTest().setPathGenerator(new RandomPath(new TimeDuration(5, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_3,new FinalizarCompraTest().setPathGenerator(new RandomPath(new TimeDuration(5, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_4,new RemoveProdutoTest().setPathGenerator(new RandomPath(new TimeDuration(5, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_5,new EfetuarPagamentoTest().setPathGenerator(new RandomPath(new TimeDuration(5, TimeUnit.SECONDS))))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}
	
}
