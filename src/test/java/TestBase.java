import application.Application;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class TestBase {

    protected static Application app;

    @BeforeClass
    public static void startDriver() {
        app = new Application();
    }

    @AfterClass
    public static void stopDriver() {
        app.quit();
    }
}
