package com.validation.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ProductType {

	public enum PRODUCT {

		COMMONS {

			@Override
			public String toString() {

				return "1";
			}
		},

		PAYPRO {

			@Override
			public String toString() {
				// TODO Auto-generated method stub
				return "2";
			}
		},

		COLLECTION {

			@Override
			public String toString() {

				return "3";
			}
		},

		LMS {

			@Override
			public String toString() {

				return "4";
			}
		}
	}

	PRODUCT product() default PRODUCT.COMMONS;

}
