package baseconfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class BaseSetup {
	public static Properties prop;
	private static StringBuilder builder = new StringBuilder();
	
	@BeforeClass
	public static void getProperties() throws IOException {
		prop = new Properties();
		FileInputStream ins = new FileInputStream(".//src//test//resources//cred.properties");
		prop.load(ins);
	}
	
	@AfterClass
    public static void afterClass() throws IOException {
        PrintWriter logFile = new PrintWriter(".//logging.txt", "UTF-8");
        logFile.write(builder.toString());
        logFile.close();
    }
    
    @Rule
    public TestWatcher watchman = new TestWatcher() {

        @Override
        protected void failed(Throwable e, Description description) {
            if (description != null) {
                builder.append(description);
            }
            if (e != null) {
                builder.append(' ');
                builder.append(e);
            }
            builder.append(" FAIL\n");
        }

        @Override
        protected void succeeded(Description description) {
            if (description != null) {
                builder.append(description);
            }
            builder.append(" OK\n");
        }
    };
}
