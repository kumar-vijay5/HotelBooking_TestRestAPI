package testRun;
import java.io.IOException;

import org.testng.annotations.Test;
import testscenario.CreateBooking;

public class TestRunner extends CreateBooking{

	public TestRunner() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String args[]) throws IOException {
		CreateBooking cb = new CreateBooking();
		cb.getResponse_Postauthorization();
	}

}