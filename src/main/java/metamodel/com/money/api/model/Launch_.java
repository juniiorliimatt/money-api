package com.money.api.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Launch.class)
public abstract class Launch_ {

	public static volatile SingularAttribute<Launch, Long> code;
	public static volatile SingularAttribute<Launch, String> observation;
	public static volatile SingularAttribute<Launch, Person> person;
	public static volatile SingularAttribute<Launch, LocalDate> dueDate;
	public static volatile SingularAttribute<Launch, String> description;
	public static volatile SingularAttribute<Launch, LocalDate> paymentDate;
	public static volatile SingularAttribute<Launch, LaunchType> type;
	public static volatile SingularAttribute<Launch, Category> category;
	public static volatile SingularAttribute<Launch, BigDecimal> valueLaunch;

	public static final String CODE = "code";
	public static final String OBSERVATION = "observation";
	public static final String PERSON = "person";
	public static final String DUE_DATE = "dueDate";
	public static final String DESCRIPTION = "description";
	public static final String PAYMENT_DATE = "paymentDate";
	public static final String TYPE = "type";
	public static final String CATEGORY = "category";
	public static final String VALUE_LAUNCH = "valueLaunch";

}

