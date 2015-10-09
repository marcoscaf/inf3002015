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
import org.graphwalker.core.condition.ReachedEdge;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.event.Observer;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.CombinedPath;
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
import br.com.unicamp.inf321.models.petclinic.FindOwners;
import br.com.unicamp.inf321.models.petclinic.NewOwner;
import br.com.unicamp.inf321.models.petclinic.OwnerInformation;
import br.com.unicamp.inf321.models.petclinic.PetClinic;
import br.com.unicamp.inf321.models.petclinic.Veterinariens;
import br.com.unicamp.inf321.observers.GraphStreamObserver;

public class PetClinicTest {
	public final static Path MODEL_PATH_1 = Paths.get("/br/com/unicamp/inf321/PetClinicSharedState.graphml");
	public final static Path MODEL_PATH_2 = Paths.get("/br/com/unicamp/inf321/FindOwnersSharedState.graphml");
	public final static Path MODEL_PATH_3 = Paths.get("/br/com/unicamp/inf321/VeterinariensSharedState.graphml");
	public final static Path MODEL_PATH_4 = Paths.get("/br/com/unicamp/inf321/OwnerInformationSharedState.graphml");
	public final static Path MODEL_PATH_5 = Paths.get("/br/com/unicamp/inf321/NewOwnerSharedState.graphml");
	private Observer observer;

	@Rule
	public TestName testName = new TestName();
	private Graph graph;

	@Before
	public void beforeTest() {
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		graph = new SingleGraph("GraphWalker");
		graph.display(true);
		observer = new GraphStreamObserver(graph);
	}

	@After
	public void afterTest() throws Exception {
		Helper.takeScreenshot("screenshots/" + PetClinicTest.class.getSimpleName() + "_webdriver_" + testName.getMethodName() + ".png");
		FileSinkImages pic = new FileSinkImages(OutputType.JPG, Resolutions.HD720);
		pic.setLayoutPolicy(LayoutPolicy.COMPUTED_FULLY_AT_NEW_IMAGE);
		pic.setRenderer(RendererType.BASIC);
		pic.writeAll(graph, "screenshots/" + PetClinicTest.class.getSimpleName() + "_graphstream_" + testName.getMethodName() + ".png");
	}

	@AfterClass
	public static void afterClass() {
		Helper.getInstance().close();
	}

	@Test
	public void runSmokeTest() {
		CombinedPath cpFindOwners = new CombinedPath();
		cpFindOwners.addPathGenerator(new AStarPath(new ReachedVertex("v_Owners")));
		cpFindOwners.addPathGenerator(new AStarPath(new ReachedVertex("v_NewOwner")));
		
		CombinedPath cpNewOwner = new CombinedPath();
		cpNewOwner.addPathGenerator(new AStarPath(new ReachedEdge("e_CorrectData")));
		cpNewOwner.addPathGenerator(new AStarPath(new ReachedVertex("v_OwnerInformation")));
		
		CombinedPath cpOwnerInformation = new CombinedPath();
		cpOwnerInformation.addPathGenerator(new AStarPath(new ReachedVertex("v_NewVisit")));
		cpOwnerInformation.addPathGenerator(new AStarPath(new ReachedVertex("v_NewPet")));
		cpOwnerInformation.addPathGenerator(new AStarPath(new ReachedVertex("v_Pet")));
		cpOwnerInformation.addPathGenerator(new AStarPath(new ReachedVertex("v_FindOwners")));
		
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1,
						new PetClinic().setPathGenerator(new AStarPath(new ReachedVertex("v_Veterinarians"))))
				.addModel(MODEL_PATH_3,
						new Veterinariens().setPathGenerator(new AStarPath(new ReachedVertex("v_FindOwners"))))
				.addModel(MODEL_PATH_2,
						new FindOwners().setPathGenerator(cpFindOwners))
				.addModel(MODEL_PATH_5,
						new NewOwner().setPathGenerator(cpNewOwner))
				.addModel(MODEL_PATH_4,
						new OwnerInformation().setPathGenerator(cpOwnerInformation))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

	@Test
	public void runStabilityTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1,
						new PetClinic().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_2,
						new FindOwners().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_3,
						new Veterinariens().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_4,
						new OwnerInformation().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.addModel(MODEL_PATH_5,
						new NewOwner().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

	@Test
	public void runFunctionalTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH_1, new PetClinic().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addModel(MODEL_PATH_2, new FindOwners().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addModel(MODEL_PATH_3, new Veterinariens().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addModel(MODEL_PATH_4, new OwnerInformation().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addModel(MODEL_PATH_5, new NewOwner().setPathGenerator(new RandomPath(new EdgeCoverage(1))))
				.addObserver(observer).execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

}
