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
	public enum ATTRIBUTE {
		SUBJECT_USER_ID,
		SUBJECT_ROLE,
		SUBJECT_PARTY_TYPE,
		
		RESOURCE_RESOURCE_TYPE,
		RESOURCE_PATIENT_STATUS,
		RESOURCE_PATIENT_RELATIVE_PASSPORT_ID,
		RESOURCE_PATIENT_CONSCIOUS_STATUS,
		RESOURCE_PATIENT_RECORD_DEPARTMENT,
		RESOURCE_PATIENT_RECORD_ASSIGNED_DOCTOR_ID,
		RESOURCE_PATIENT_RECORD_ASSIGNED_NURSE_ID,
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_DOCTOR,
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID,
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_START_TIME,
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_END_TIME,
		
		ACTION_ACTION_TYPE,
		
		ENVIRONMENT_LOCATION,
		ENVIRONMENT_CURRENT_TIME
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
	@XACMLResource(attributeId="patient:emgergency-agreement:start-time")
	public Object patientEmergencyAgreementStartTime;
	@XACMLResource(attributeId="patient:emgergency-agreement:end-time")
	public Object patientEmergencyAgreementEndTime;
	
	@XACMLAction(attributeId="action-type")
	public Object actionType;
	
	@XACMLEnvironment(attributeId="location")
	public Object location;
	@XACMLEnvironment(attributeId="current-time")
	public Object currentTime;
	
	public HealthCareRequest(Map<Enum<?>, Object> objects) {
		this.userId = objects.get(ATTRIBUTE.SUBJECT_USER_ID);
		this.role = objects.get(ATTRIBUTE.SUBJECT_ROLE);
		this.partyType = objects.get(ATTRIBUTE.SUBJECT_PARTY_TYPE);
		
		this.resourceType = objects.get(ATTRIBUTE.RESOURCE_RESOURCE_TYPE);
		this.patientStatus = objects.get(ATTRIBUTE.RESOURCE_PATIENT_STATUS);
		this.patientRelativePassportId = objects.get(ATTRIBUTE.RESOURCE_PATIENT_RELATIVE_PASSPORT_ID);
		this.patientConsciousStatus = objects.get(ATTRIBUTE.RESOURCE_PATIENT_CONSCIOUS_STATUS);
		this.patientRecordDepartment = objects.get(ATTRIBUTE.RESOURCE_PATIENT_RECORD_DEPARTMENT);
		this.patientRecordAssignedDoctorId = objects.get(ATTRIBUTE.RESOURCE_PATIENT_RECORD_ASSIGNED_DOCTOR_ID);
		this.patientRecordAssignedNurseId = objects.get(ATTRIBUTE.RESOURCE_PATIENT_RECORD_ASSIGNED_NURSE_ID);
		this.patientEmergencyAgreementDoctor = objects.get(ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_DOCTOR);
		this.patientEmergencyAgreementRelativePassportId = objects.get(ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID);
		this.patientEmergencyAgreementStartTime = objects.get(ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_START_TIME);
		this.patientEmergencyAgreementEndTime = objects.get(ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_END_TIME);
		
		this.actionType = objects.get(ATTRIBUTE.ACTION_ACTION_TYPE);
		
		this.location = objects.get(ATTRIBUTE.ENVIRONMENT_LOCATION);
		this.currentTime = objects.get(ATTRIBUTE.ENVIRONMENT_CURRENT_TIME);
		 
	}
	
}
