package org.apache.openaz.xacml.pdp.test.healthcare;

import java.util.Map;

import org.apache.openaz.xacml.std.annotations.XACMLAction;
import org.apache.openaz.xacml.std.annotations.XACMLEnvironment;
import org.apache.openaz.xacml.std.annotations.XACMLRequest;
import org.apache.openaz.xacml.std.annotations.XACMLResource;
import org.apache.openaz.xacml.std.annotations.XACMLSubject;

@XACMLRequest(ReturnPolicyIdList=true)
public class HealthCareRequest {
	public enum ATTRIBUTE {
		SUBJECT_USER_ID(String.class, "Doctor-Patient", "Nurse-Patient", "Doctor-Emer-Agreement", "Doctor-Non-Patient", "Nurse-Non-Patient"),
		SUBJECT_ROLE(String.class, "Doctor", "Nurse", "External"),
		SUBJECT_PARTY_TYPE(String.class, "Pharmacy", "Insurance"),
		
		RESOURCE_RESOURCE_TYPE(String.class, "Patient.Record", "Patient.Record.Billing", "Patient.Record.Medicines"),
		RESOURCE_PATIENT_STATUS(String.class, "NotAvailableDoctorNurse", "AvailableDoctorNurse"),
		RESOURCE_PATIENT_RELATIVE_PASSPORT_ID(String.class, "Patient-Relative"),
		RESOURCE_PATIENT_CONSCIOUS_STATUS(String.class, "Conscious", "Unconscious"),
		RESOURCE_PATIENT_RECORD_DEPARTMENT(String.class, "Heart"),
		RESOURCE_PATIENT_RECORD_ASSIGNED_DOCTOR_ID(String.class, "Doctor-Patient"),
		RESOURCE_PATIENT_RECORD_ASSIGNED_NURSE_ID(String.class, "Doctor-Nurse"),
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_DOCTOR(String.class, "Doctor-Emer-Agreement"),
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID(String.class, "Patient-Relative"),
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_START_TIME(Double.class),
		RESOURCE_PATIENT_EMERGENCY_AGREEMENT_END_TIME(Double.class),
		
		ACTION_ACTION_TYPE(String.class, "Read"),
		
		ENVIRONMENT_LOCATION(String.class, "Heart"),
		ENVIRONMENT_CURRENT_TIME(Double.class);

		public String[] attValues;
		public Class<?> clazz;

		private ATTRIBUTE(Class<?> clazz, String... attValues) {
			this.clazz = clazz;
			this.attValues = attValues;
		}
	}
	
	@XACMLSubject(attributeId = "user-id")
	@AttributeNamespace(namespace = ATTRIBUTE.SUBJECT_USER_ID)
	public Object userId;
	@AttributeNamespace(namespace = ATTRIBUTE.SUBJECT_ROLE)
	@XACMLSubject(attributeId = "role")
	public Object role;
	@AttributeNamespace(namespace = ATTRIBUTE.SUBJECT_PARTY_TYPE)
	@XACMLSubject(attributeId = "party-type")
	public Object partyType;

	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_RESOURCE_TYPE)
	@XACMLResource(attributeId="resource-type")
	public Object resourceType;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_STATUS)
	@XACMLResource(attributeId="patient:management-info:status")
	public Object patientStatus;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_RELATIVE_PASSPORT_ID)
	@XACMLResource(attributeId="patient:management-info:relative-passport-id")
	public Object patientRelativePassportId;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_CONSCIOUS_STATUS)
	@XACMLResource(attributeId="patient:management-info:conscious-status")
	public Object patientConsciousStatus;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_RECORD_DEPARTMENT)
	@XACMLResource(attributeId="patient:record:department")
	public Object patientRecordDepartment;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_RECORD_ASSIGNED_DOCTOR_ID)
	@XACMLResource(attributeId="patient:record:assigned-doctor-id")
	public Object patientRecordAssignedDoctorId;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_RECORD_ASSIGNED_NURSE_ID)
	@XACMLResource(attributeId="patient:record:assigned-nurse-id")
	public Object patientRecordAssignedNurseId;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_DOCTOR)
	@XACMLResource(attributeId="patient:emgergency-agreement:doctor")
	public Object patientEmergencyAgreementDoctor;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_RELATIVE_PASSPORT_ID)
	@XACMLResource(attributeId="patient:emgergency-agreement:relative-passport-id")
	public Object patientEmergencyAgreementRelativePassportId;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_START_TIME)
	@XACMLResource(attributeId="patient:emgergency-agreement:start-time")
	public Object patientEmergencyAgreementStartTime;
	@AttributeNamespace(namespace = ATTRIBUTE.RESOURCE_PATIENT_EMERGENCY_AGREEMENT_END_TIME)
	@XACMLResource(attributeId="patient:emgergency-agreement:end-time")
	public Object patientEmergencyAgreementEndTime;
	
	@AttributeNamespace(namespace = ATTRIBUTE.ACTION_ACTION_TYPE)
	@XACMLAction(attributeId="action-type")
	public Object actionType;
	
	@AttributeNamespace(namespace = ATTRIBUTE.ENVIRONMENT_LOCATION)
	@XACMLEnvironment(attributeId="location")
	public Object location;
	@AttributeNamespace(namespace = ATTRIBUTE.ENVIRONMENT_CURRENT_TIME)
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
