package org.au.careercove.api.data.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ValueHelp {

	public enum ALLOWED_TYPES {
		USER,
		GENDER,
		ORGANIZATION, 
		PAY, 
		SKILLS
	}

	private static List<ValueType> userType = new ArrayList<>();
	private static List<ValueType> genderType = new ArrayList<>();
	private static List<ValueType> organizationType = new ArrayList<>();
	private static List<ValueType> payType = new ArrayList<>();
	private static List<ValueType> skillTypes = new ArrayList<>();

	static {
		userType.add(new ValueType("10001", "Administrator", "Administrator"));
		userType.add(new ValueType("10002", "Student", "Student"));

		genderType.add(new ValueType("20001", "Male", "Male"));
		genderType.add(new ValueType("20002", "Female", "Female"));
		genderType.add(new ValueType("20003", "Prefer not to answer", "Prefer not to answer"));

		organizationType.add(new ValueType("30001", "Education Institute", "Education Institute"));
		organizationType.add(new ValueType("30002", "Corportate", "Corportate"));

		payType.add(new ValueType("40001", "Paid", "Paid"));
		payType.add(new ValueType("40002", "Not Paid", "Not Paid"));

		skillTypes.add(new ValueType("50001", "Python", "Python"));
		skillTypes.add(new ValueType("50002", "Java", "Java"));
		skillTypes.add(new ValueType("50003", "JavaScript", "JavaScript"));
		skillTypes.add(new ValueType("50004", "C++", "C++"));
		skillTypes.add(new ValueType("50005", "HTML", "HTML"));
		skillTypes.add(new ValueType("50006", "SQL", "SQL"));
		skillTypes.add(new ValueType("50007", "Docker", "Docker"));
		skillTypes.add(new ValueType("50008", "Kubernetes", "Kubernetes"));
		skillTypes.add(new ValueType("50009", "AWS", "AWS"));
		skillTypes.add(new ValueType("50010", "Ruby", "Ruby"));
		skillTypes.add(new ValueType("50011", "Rails", "Rails"));
		skillTypes.add(new ValueType("50012", "PostgreSQL", "PostgreSQL"));
		skillTypes.add(new ValueType("50013", "Elixir", "Elixir"));
		skillTypes.add(new ValueType("50014", "Phoenix", "Phoenix"));
		skillTypes.add(new ValueType("50015", "Haskell", "Haskell"));
		skillTypes.add(new ValueType("50016", "Elm", "Elm"));
		skillTypes.add(new ValueType("50017", "Scala", "Scala"));
		skillTypes.add(new ValueType("50018", "Spark", "Spark"));
		skillTypes.add(new ValueType("50019", "TypeScript", "TypeScript"));
		skillTypes.add(new ValueType("50020", "React", "React"));
		skillTypes.add(new ValueType("50021", "Node.js", "Node.js"));
		skillTypes.add(new ValueType("50022", "Swift", "Swift"));
		skillTypes.add(new ValueType("50023", "Objective-C", "Objective-C"));
		skillTypes.add(new ValueType("50024", "Xcode", "Xcode"));
		skillTypes.add(new ValueType("50025", "Tableau", "Tableau"));
		skillTypes.add(new ValueType("50026", "Django", "Django"));
		skillTypes.add(new ValueType("50027", "Git", "Git"));
		skillTypes.add(new ValueType("50028", "Terraform", "Terraform"));
		skillTypes.add(new ValueType("50029", "Ansible", "Ansible"));
		skillTypes.add(new ValueType("50030", "CUDA", "CUDA"));
		skillTypes.add(new ValueType("50031", "Snowflake", "Snowflake"));
		skillTypes.add(new ValueType("50032", "Salesforce", "Salesforce"));
	}

	public static boolean isValidType(ALLOWED_TYPES type, String id) {

		List<ValueType> typeToValidate = null;

		switch (type) {
			case GENDER:
				typeToValidate = genderType;
				break;
			case USER:
				typeToValidate = userType;
				break;
			case ORGANIZATION:
				typeToValidate = organizationType;
				break;
			case PAY:
				typeToValidate = payType;
				break;
			case SKILLS:
				typeToValidate = skillTypes;
				break;
		}

		if (typeToValidate == null) {
			return false;
		}

		for (ValueType vType : typeToValidate) {
			if (vType.getId().equalsIgnoreCase(id)) {
				return true;
			}
		}

		return false;
	}

	public static List<ValueType> getValueHelps(ALLOWED_TYPES type) {

		switch (type) {
			case GENDER:
				return genderType;
			case USER:
				return userType;
			case ORGANIZATION:
				return organizationType;
			case PAY:
				return payType;
			case SKILLS:
				return skillTypes;
		}

		return new ArrayList<>();
	}
}