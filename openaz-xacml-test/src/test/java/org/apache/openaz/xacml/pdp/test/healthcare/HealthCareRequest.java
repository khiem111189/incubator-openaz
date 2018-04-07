package org.apache.openaz.xacml.pdp.test.healthcare;

import java.util.Map;

import org.apache.openaz.xacml.std.annotations.XACMLAction;
import org.apache.openaz.xacml.std.annotations.XACMLEnvironment;
import org.apache.openaz.xacml.std.annotations.XACMLRequest;
import org.apache.openaz.xacml.std.annotations.XACMLResource;
import org.apache.openaz.xacml.std.annotations.XACMLSubject;

@XACMLRequest(ReturnPolicyIdList=true)
public class HealthCareRequest {
	@XACMLSubject(attributeId="user-id")
	public Object userId;
	@XACMLSubject(attributeId="role")
	public Object role;
	@XACMLSubject(attributeId="party-type")
	public Object partyType;
	public enum SUBJECT {
		USER_ID,
		ROLE,
		PARTY_TYPE
	}
	
	@XACMLResource(attributeId="resource-type")
	public Object resourceType;
	@XACMLResource(attributeId="patient:management-info:status")
	public Object patientStatus;
	@XACMLResource(attributeId="patient:management-info:relative-passport-id")
	public Object patientRelativePassportId;
	@XACMLResource(attributeId="patient:management-info:conscious-status")
	public Object patientConsciousStatus;
	@XACMLResource(attributeId="patient:record:department")
	public Object patientRecordDepartment;
	@XACMLResource(attributeId="patient:record:assigned-doctor-id")
	public Object patientRecordAssignedDoctorId;
	@XACMLResource(attributeId="patient:record:assigned-nurse-id")
	public Object patientRecordAssignedNurseId;
	@XACMLResource(attributeId="patient:emgergency-agreement:doctor")
	public Object patientEmergencyAgreementDoctor;
	@XACMLResource(attributeId="patient:emgergency-agreement:relative-passport-id")
	public Object patientEmergencyAgreementRelativePassportId;
	public enum RESOURCE {
		RESOURCE_TYPE,
		PATIENT_STATUS,
		PATIENT_RELATIVE_PASSPORT_ID,
		PATIENT_CONSCIOUS_STATUS,
		PATIENT_RECORD_DEPARTMENT,
		PATIENT_RECORD_ASSIGNED_DOCTOR_ID,
		PATIENT_RECORD_ASSIGNED_NURSE_ID,
		PATIENT_EMERGENCY_AGREEMENT_DOCTOR,
		PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID
	}
	
	@XACMLAction(attributeId="action-type")
	public Object actionType;
	public enum ACTION {
		ACTION_TYPE
	}
	
	@XACMLEnvironment(attributeId="location")
	public Object location;
	public enum ENVIRONMENT {
		LOCATION
	}
	
	public HealthCareRequest(Map<Enum<?>, Object> objects) {
		this.userId = objects.get(SUBJECT.USER_ID);
		this.role = objects.get(SUBJECT.ROLE);
		this.partyType = objects.get(SUBJECT.PARTY_TYPE);
		
		this.resourceType = objects.get(RESOURCE.RESOURCE_TYPE);
		this.patientStatus = objects.get(RESOURCE.PATIENT_STATUS);
		this.patientRelativePassportId = objects.get(RESOURCE.PATIENT_RELATIVE_PASSPORT_ID);
		this.patientConsciousStatus = objects.get(RESOURCE.PATIENT_CONSCIOUS_STATUS);
		this.patientRecordDepartment = objects.get(RESOURCE.PATIENT_RECORD_DEPARTMENT);
		this.patientRecordAssignedDoctorId = objects.get(RESOURCE.PATIENT_RECORD_ASSIGNED_DOCTOR_ID);
		this.patientRecordAssignedNurseId = objects.get(RESOURCE.PATIENT_RECORD_ASSIGNED_NURSE_ID);
		this.patientEmergencyAgreementDoctor = objects.get(RESOURCE.PATIENT_EMERGENCY_AGREEMENT_DOCTOR);
		this.patientEmergencyAgreementRelativePassportId = objects.get(RESOURCE.PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID);
		
		this.actionType = objects.get(ACTION.ACTION_TYPE);
		
		this.location = objects.get(ENVIRONMENT.LOCATION);
		 
	}
	
}
