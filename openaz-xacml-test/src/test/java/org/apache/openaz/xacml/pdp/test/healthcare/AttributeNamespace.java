package org.apache.openaz.xacml.pdp.test.healthcare;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.apache.openaz.xacml.pdp.test.healthcare.HealthCareRequest.ATTRIBUTE;

@Documented
@Target(ElementType.FIELD)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface AttributeNamespace {
	ATTRIBUTE namespace() default ATTRIBUTE.SUBJECT_USER_ID;
}
