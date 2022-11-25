package baseconfig;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import org.junit.BeforeClass;

public class BaseSetup {
	public static Properties prop;
		
	@BeforeClass
	public static void getProperties() throws IOException {
		prop = new Properties();
		FileInputStream ins = new FileInputStream(".//src//test//resources//cred.properties");
		prop.load(ins);
	}
}
