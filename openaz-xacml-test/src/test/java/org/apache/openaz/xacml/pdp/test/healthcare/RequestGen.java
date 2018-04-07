package org.apache.openaz.xacml.pdp.test.healthcare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.openaz.xacml.api.Request;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.ATTRIBUTE;
import org.apache.openaz.xacml.std.annotations.RequestParser;
import org.apache.openaz.xacml.std.json.JSONRequest;

public class RequestGen {
	public enum Result {
		Permit,
		Deny,
		In,
		NA
	}
	public static void main(String[] args) throws Exception {
		try (BufferedReader br = new BufferedReader(new FileReader("src/test/resources/testsets/healthcare/requests.csv"))) {
			boolean firstDone = false;
			for (String line; (line = br.readLine()) != null;) {
				if (!firstDone) {
					firstDone = true;
					continue;
				}
				
				String[] items = line.split(",");
				Map<Enum<?>, Object> map = new HashMap<Enum<?>, Object>();
				int index = 0;
				int stt = Integer.valueOf(items[index++]);
				Result res = Result.valueOf(items[index++]);
				LocalDateTime now = null;
				for (; index-2 < ATTRIBUTE.values().length; index++) {
					if (index >= items.length) {
						break;
					} else if (items[index].isEmpty()) {
						continue;
					}

					ATTRIBUTE att = ATTRIBUTE.values()[index-2];
					if (att == ATTRIBUTE.ENVIRONMENT_CURRENT_TIME) {
						if (now == null) {
							now = LocalDateTime.now();
						}
						map.put(att, Double.valueOf(toLong(now)));
					} else if (att == ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_START_TIME
							|| att == ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_END_TIME) {
						if (now == null) {
							now = LocalDateTime.now();
						}
						map.put(att, Double.valueOf(toLong(now.plusHours(Integer.valueOf(items[index])))));
					} else {
						map.put(att, items[index]);
					}
				}
				Request request = RequestParser.parseRequest(new HealthCareRequest(map));
				FileUtils.write(new File("E:\\Workspace\\Thesis\\openaz-work\\openaz-xacml-test\\src\\test\\resources\\testsets\\healthcare\\requests\\Request." + stt + "."+ res.name() + ".json"), JSONRequest.toString(request));
			}
		}
		
//		Object req = req(
//				new Object[] {
//				ATTRIBUTE.SUBJECT_USER_ID, "Nurse-Patient",
//				ATTRIBUTE.SUBJECT_ROLE, "Nurse",
//				
//				ATTRIBUTE.RESOURCE_RESOURCE_TYPE, "Patient.Record",
//				ATTRIBUTE.RESOURCE_PATIENT_RECORD_ASSIGNED_NURSE_ID, "Nurse-Patient",
//				ATTRIBUTE.RESOURCE_PATIENT_RECORD_DEPARTMENT, "Heart",
//				
//				ATTRIBUTE.ACTION_ACTION_TYPE, "Read",
//				ATTRIBUTE.ENVIRONMENT_LOCATION, "Heart"}
//				);
//		Request request = RequestParser.parseRequest(req);
//		System.out.println(JSONRequest.toString(request));
//		FileUtils.write(new File("E:\\Workspace\\Thesis\\openaz-work\\openaz-xacml-test\\src\\test\\resources\\testsets\\healthcare\\requests\\Request." + 1 + ".Permit.json"), JSONRequest.toString(request));
	}

	private static long toLong(LocalDateTime now) {
		return now.atZone(ZoneId.systemDefault())
			    	      .toInstant().toEpochMilli();
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
