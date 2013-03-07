import static org.junit.Assert.assertEquals;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.webjars.AssetLocator;
import org.webjars.WebJarAssetLocator;

public class WebJarsLocatorPT {

    @Rule
    public ContiPerfRule r = new ContiPerfRule();

    private static WebJarAssetLocator locator;

    @BeforeClass
    public static void setup() {
        locator = new WebJarAssetLocator();
    }

    @Test
    @PerfTest(invocations = 1000, rampUp = 100)
    public void shouldGetFullPathSingleThreadedWithOldApi() {
        final String fullPath = AssetLocator.getFullPath("angular.js");
        assertEquals("META-INF/resources/webjars/angularjs/1.1.3/angular.js",
                fullPath);
    }

    @Test
    @PerfTest(invocations = 1000, rampUp = 100)
    public void shouldGetFullPathSingleThreadedWithNewApi() {
        final String fullPath = locator.getFullPath("angular.js");
        assertEquals("META-INF/resources/webjars/angularjs/1.1.3/angular.js",
                fullPath);
    }

    @Test
    @PerfTest(invocations = 1000, rampUp = 100, threads = 10)
    public void shouldGetFullPathMultiThreadedWithOldApi() {
        final String fullPath = AssetLocator.getFullPath("angular.js");
        assertEquals("META-INF/resources/webjars/angularjs/1.1.3/angular.js",
                fullPath);
    }

    @Test
    @PerfTest(invocations = 1000, rampUp = 100, threads = 10)
    public void shouldGetFullPathMultiThreadedWithNewApi() {
        final String fullPath = locator.getFullPath("angular.js");
        assertEquals("META-INF/resources/webjars/angularjs/1.1.3/angular.js",
                fullPath);
    }

}
