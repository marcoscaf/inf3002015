package br.com.unicamp.inf321;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.assertj.core.api.Assertions;
import org.graphwalker.core.condition.EdgeCoverage;
import org.graphwalker.core.condition.ReachedVertex;
import org.graphwalker.core.condition.TimeDuration;
import org.graphwalker.core.generator.AStarPath;
import org.graphwalker.core.generator.RandomPath;
import org.graphwalker.java.test.Result;
import org.junit.AfterClass;
import org.junit.Test;

import br.com.unicamp.inf321.helper.GraphWalkerTestBuilder;
import br.com.unicamp.inf321.helper.Helper;
import br.com.unicamp.inf321.models.SomeSmallTest;

public class SampleTest {
	
	public final static Path MODEL_PATH = Paths.get("/br/com/unicamp/inf321/SmallTest.graphml");
	
	@AfterClass
	public static void afterClass() {
		Helper.getInstance().close();
	}

	@Test
	public void runSmokeTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH,
						new SomeSmallTest().setPathGenerator(new AStarPath(new ReachedVertex("v_VerifySomeOtherAction"))))
				.execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

	@Test
	public void runStabilityTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH,
						new SomeSmallTest().setPathGenerator(new RandomPath(new TimeDuration(30, TimeUnit.SECONDS))))
				.execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

	@Test
	public void runFunctionalTest() {
		Result result = new GraphWalkerTestBuilder()
				.addModel(MODEL_PATH, new SomeSmallTest().setPathGenerator(new RandomPath(new EdgeCoverage(100))))
				.execute(true);
		Assertions.assertThat(result.getErrors()).as("Errors: [" + result.getErrors().toString() + "]").isNullOrEmpty();
	}

}
