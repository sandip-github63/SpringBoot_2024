package com.validation.validator.factoryvalidator;

import java.util.List;

public interface Validator {

	List<String> validate(String validatorName, Object obj);

}
