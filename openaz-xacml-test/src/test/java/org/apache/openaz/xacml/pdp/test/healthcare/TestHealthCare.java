package org.apache.openaz.xacml.pdp.test.healthcare;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.ACTION;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.ENVIRONMENT;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.RESOURCE;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.SUBJECT;
import org.apache.openaz.xacml.std.annotations.RequestParser;
import org.apache.openaz.xacml.std.json.JSONRequest;

public class TestHealthCare {
	public static void main(String[] args) throws Exception {
		Object req = req(
				new Object[] {SUBJECT.USER_ID, "Alice",
				SUBJECT.ROLE, "Nurse",
				RESOURCE.RESOURCE_TYPE, "Patient.Record",
				RESOURCE.PATIENT_RECORD_ASSIGNED_NURSE_ID, "Alice",
				RESOURCE.PATIENT_RECORD_DEPARTMENT, "Heart",
				ACTION.ACTION_TYPE, "Read",
				ENVIRONMENT.LOCATION, "Heart"}
				);
		System.out.println(JSONRequest.toString(RequestParser.parseRequest(req)));
		FileUtils.write(new File("E:\\Workspace\\Thesis\\openaz-work\\openaz-xacml-test\\src\\test\\resources\\testsets\\healthcare\\request." + 1 + ".Permit.json"), JSONRequest.toString(RequestParser.parseRequest(req)));
	}
	
	public static Object req(Object[] input) {
		return new HealthCareRequest(map(input));
	}
	
	public static Map<Enum<?>, Object> map(Object[] input) {
		Map<Enum<?>, Object> ret = new HashMap<>();
		for (int idx = 0; idx < input.length - 1; idx+= 2) {
			ret.put((Enum<?>)input[idx], input[idx+1]);
		}
		return ret;
	}
}
