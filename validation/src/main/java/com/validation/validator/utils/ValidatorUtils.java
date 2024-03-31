package com.validation.validator.utils;

import com.validation.expections.ValidationExpection;
import com.validation.validator.factoryvalidator.Validator;
import com.validation.validator.factoryvalidator.ValidatorFactory;

/**
 * @author sandip
 * 
 *         By controller name you are getting right validator's object By
 *         methodName and Request you are validate your request
 *
 */
public class ValidatorUtils {

	/**
	 * @param request
	 * @param controllerName
	 * @param methodName
	 * @throws ValidationExpection
	 */
	public static void validate(Object request, String controllerName, String methodName) throws ValidationExpection {

		Validator validator = ValidatorFactory.findValidatory(controllerName);

		if (null != validator) {
			validator.validate(methodName, request);
		}

	}

}
