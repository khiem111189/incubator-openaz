package org.apache.openaz.xacml.pdp.test.healthcare;

import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.cli.ParseException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.openaz.xacml.pdp.test.TestBase;
import org.apache.openaz.xacml.util.FactoryException;

public class TestHealthCare extends TestBase {
	private static final Log logger = LogFactory.getLog(TestHealthCare.class);

	public TestHealthCare(String[] args) throws ParseException,
			MalformedURLException, HelpException {
		super(args);
	}

	public static void main(String[] args) {
		try {
			String[] inputs = new String[] {
					"-dir",
					"E:\\Workspace\\Thesis\\openaz-work\\openaz-xacml-test\\src\\test\\resources\\testsets\\healthcare" };
			new TestHealthCare(inputs).run();
		} catch (ParseException | IOException | FactoryException e) {
			logger.error(e);
		} catch (HelpException e) {
			//
			// ignore this, its thrown just to exit the application
			// after dumping help to stdout.
			//
		}
	}

}
