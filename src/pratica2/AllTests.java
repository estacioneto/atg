package pratica2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	ReadGraphTest.class,
	ReadWeightedGraphTest.class,
	GraphRepresentationTest.class,
	OtherTest.class,
	SearchTest.class
})
public class AllTests {

}
