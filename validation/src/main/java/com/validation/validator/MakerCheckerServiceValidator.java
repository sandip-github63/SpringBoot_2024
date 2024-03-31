package com.validation.validator;

public class MakerCheckerServiceValidator extends AbstractValidator {

	private static MakerCheckerServiceValidator validator;

	private MakerCheckerServiceValidator() {

	}

	/* singleton object is created */
	public static MakerCheckerServiceValidator getInstance() {
		if (MakerCheckerServiceValidator.validator == null) {
			synchronized (MakerCheckerServiceValidator.class) {
				if (MakerCheckerServiceValidator.validator == null) {
					MakerCheckerServiceValidator.validator = new MakerCheckerServiceValidator();
				}
			}

		}

		return MakerCheckerServiceValidator.validator;
	}

}
