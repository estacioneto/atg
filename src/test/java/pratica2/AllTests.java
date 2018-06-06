package pratica2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ReadGraphTest.class,
	ReadWeightedGraphTest.class,
	GraphRepresentationTest.class,
	MeanEdgeTest.class
})
public class AllTests {

}
