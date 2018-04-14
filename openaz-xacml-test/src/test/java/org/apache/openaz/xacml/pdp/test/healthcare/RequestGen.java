package org.apache.openaz.xacml.pdp.test.healthcare;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.openaz.xacml.api.Request;
import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.ATTRIBUTE;
import org.apache.openaz.xacml.std.annotations.RequestParser;
import org.apache.openaz.xacml.std.annotations.XACMLAction;
import org.apache.openaz.xacml.std.annotations.XACMLEnvironment;
import org.apache.openaz.xacml.std.annotations.XACMLResource;
import org.apache.openaz.xacml.std.annotations.XACMLSubject;
import org.apache.openaz.xacml.std.jaxp.JaxpRequest;
import org.apache.openaz.xacml.std.json.JSONRequest;

public class RequestGen {
	private static final String REQ_FOLDER_PATH = "src/test/resources/testsets/healthcare/requests";
	private static final String XML_REQ_FOLDER_PATH = "src/test/resources/testsets/healthcare/xml-requests";
	private static final String REQ_PATH = REQ_FOLDER_PATH + "/Request.";

	public enum Result {
		Permit("Permit"),
		Deny("Deny"),
		In("Indeterminate"),
		NA("NA");
		
		String fullname;
		private Result(String fullname) {
			this.fullname = fullname;
		}
	}
	public static void main(String[] args) throws Exception {
		FileUtils.cleanDirectory(new File(REQ_FOLDER_PATH));
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
				HealthCareRequest req = new HealthCareRequest(map);
				
				String smtReq = smtReqParse(res, req, false);
				System.out.println(smtReq);
				Request request = RequestParser.parseRequest(req);
				FileUtils.write(new File(REQ_PATH + stt + "."+ res.fullname + ".json"), JSONRequest.toString(request));
				FileUtils.write(new File(XML_REQ_FOLDER_PATH + "/Request." + stt + "."+ res.fullname + ".xml"), JaxpRequest.toXmlRequest(request));
			}
		}
		
		System.err.println(domains());

	}

	private static String domains() throws IllegalArgumentException, IllegalAccessException {
		Map<Enum<?>, Object> map = new HashMap<Enum<?>, Object>();
		for (ATTRIBUTE att : ATTRIBUTE.values()) {
			if (att.attValues.length > 0) {
				map.put(att, att.attValues);
			}
		}

		HealthCareRequest req = new HealthCareRequest(map);
		return smtReqParse(null, req, false);
	}

	private static String smtReqParse(Result res, HealthCareRequest req, boolean genNullValue) throws IllegalArgumentException, IllegalAccessException {
		StringBuilder sb = new StringBuilder();
		sb.append("( ");
		
		if (res != null) {
			switch (res) {
			case Permit:
				sb.append("P_1");
				break;
			case Deny:
				sb.append("D_1");
				break;
			case In:
				sb.append("IN_1");
				break;
			case NA:
				sb.append("NA_1");
			}
			sb.append(" \\\\WEDGE");
			sb.append(" ( ");
		}
		
		int idx = 0;
		for (Field field : req.getClass().getDeclaredFields()) {
			if (!genNullValue && field.get(req) == null) {
				continue;
			}
			String att = null;
			XACMLSubject subject = field.getAnnotation(XACMLSubject.class);
			if (subject != null) {
				att = subject.attributeId();
			}
			
			XACMLResource resource = field.getAnnotation(XACMLResource.class);
			if (resource != null) {
				att = resource.attributeId();
			}
			
			XACMLAction action = field.getAnnotation(XACMLAction.class);
			if (action != null) {
				att = action.attributeId();
			}
			
			XACMLEnvironment environment = field.getAnnotation(XACMLEnvironment.class);
			if (environment != null) {
				att = environment.attributeId();
			}
			
			AttributeNamespace attAnnotation = field.getAnnotation(AttributeNamespace.class);

			if (idx > 0) {
				sb.append(" \\\\WEDGE ");
			}

			if (field.get(req) == null) {
				sb.append(att);
				if (Double.class.equals(attAnnotation.namespace().clazz)) {
					sb.append(" = -1");
				} else {
					sb.append(" = \\\\EMPTYSET");
				}
			} else {
				Object obj = field.get(req);
				if (obj.getClass().isArray()) {
					if (((Object[]) obj).length > 1) {
						int index = 0;
						Object[] arr = (Object[]) obj;
						sb.append(" ( ");
						for (Object attVal : arr) {
							if (index > 0) {
								sb.append(" \\\\VEE ");
							}
							sb.append(att + " = " + attVal);
							index++;
						}
						sb.append(" )");
					} else {
						sb.append(att + " = " + ((Object[]) obj)[0]);
					}

				} else {
					sb.append(att);
					sb.append(" = " + obj);
				}
			}
			
			idx++;
		}
		if (res != null) {
			sb.append(" )");
		}

		sb.append(" )");
		return sb.toString();
	}

	private static long toLong(LocalDateTime now) {
		return now.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}
	
	public static Object req(Object[] input) throws IllegalArgumentException, IllegalAccessException {
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
