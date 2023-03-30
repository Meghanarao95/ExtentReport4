package TestCases;

import org.testng.AssertJUnit;
import org.testng.SkipException;
import org.testng.annotations.Test;

public class TestCase2 {

	@Test
	public void doLogin() {

		System.out.println("Executing the Login Test");

	}

	@Test
	public void UserReg() {
		AssertJUnit.fail("Failing the UserReg test");

	}

	@Test
	public void isSkip() {

		throw new SkipException("Skipping the Test Case");

	}

}
