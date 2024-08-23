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
		MODE
	}

	private static List<ValueType> userType = new ArrayList<>();
	private static List<ValueType> genderType = new ArrayList<>();
	private static List<ValueType> organizationType = new ArrayList<>();
	private static List<ValueType> modeType = new ArrayList<>();

	static {
		userType.add(new ValueType("10001", "Administrator", "Administrator"));
		userType.add(new ValueType("10002", "Student", "Student"));

		genderType.add(new ValueType("20001", "Male", "Male"));
		genderType.add(new ValueType("20002", "Female", "Female"));
		genderType.add(new ValueType("20003", "Prefer not to answer", "Prefer not to answer"));

		organizationType.add(new ValueType("30001", "Education Institute", "Education Institute"));
		organizationType.add(new ValueType("30002", "Corportate", "Corportate"));

		modeType.add(new ValueType("40001", "Paid", "Paid"));
		modeType.add(new ValueType("40001", "Not Paid", "Not Paid"));
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
			case MODE:
				typeToValidate = modeType;
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
			case MODE:
				return modeType;

		}

		return new ArrayList<>();
	}
}