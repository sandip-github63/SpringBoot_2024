package com.validation.validator;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.validation.validator.factoryvalidator.Validator;

public abstract class AbstractValidator<AbstractFieldValator> implements Validator {

	protected Map<String, Method> methodMap = new HashMap<String, Method>();

	public AbstractValidator() {
		InputStream stream = null;
		Document validatorDoc;

		try {
			Method[] declaredMethods = getClass().getDeclaredMethods();

			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			stream = classLoader.getResourceAsStream(getClass().getCanonicalName().replaceAll("\\.", "/") + ".xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			validatorDoc = dBuilder.parse(stream);
			validatorDoc.getDocumentElement().normalize();
			NodeList validatorList = validatorDoc.getElementsByTagName("validator");
			Map<String, Class<?>> validationClassMap = new HashMap<String, Class<?>>();
			for (int i = 0; i < validatorList.getLength(); i++) {
				Node validatorNode = validatorList.item(i);
				if (validatorNode.getNodeType() == Node.ELEMENT_NODE) {
					Element validatorElement = (Element) validatorNode;
					String validatorName = validatorElement.getAttribute("name");
					NodeList fieldList = validatorElement.getElementsByTagName("field");
					Map<String, String> internalDisplayMap = new HashMap<String, String>();
					HashMap<String, AbstractFieldValidator> internalValidatorMap = new HashMap<String, AbstractFieldValidator>();

					for (int j = 0; j < fieldList.getLength(); j++) {

						Node fieldNode = fieldList.item(j);
						if (fieldNode.getNodeType() == Node.ELEMENT_NODE) {
							Element fieldElement = (Element) fieldNode;
							String field_name = fieldElement.getAttribute("name");
							String display_key = fieldElement.getAttribute("displayKey");
							internalDisplayMap.put(field_name, display_key);
							NodeList validatorClassList = fieldElement.getElementsByTagName("validatorClass");
							AbstractFieldValidator currentFieldValidator = null;
							AbstractFieldValidator baseFieldValidator = null;

							for (int k = 0; k < validatorClassList.getLength(); k++) {
								Node validatorClass = validatorClassList.item(k);
								if (validatorClass.getNodeType() == Node.ELEMENT_NODE) {
									Element validatorClassElement = (Element) validatorClass;
									String class_name = validatorClassElement.getAttribute("name");
									String additionalData = validatorClassElement.getAttribute("additionalData");
									Class<?> c = null;
									AbstractFieldValidator interfaceType = null;

									try {
										c = validationClassMap.get(class_name);
										if (c == null) {
											c = Class.forName(class_name);
											validationClassMap.put(class_name, c);
										}

										if (null != additionalData && additionalData.length() > 0) {

											Constructor constructor = Class.forName(class_name)
													.getConstructor(String.class);
											interfaceType = (AbstractFieldValidator) constructor
													.newInstance(additionalData);

										} else {
											interfaceType = (AbstractFieldValidator) c.newInstance();

										}

									} catch (ClassNotFoundException e) {
										// TODO: handle exception
									} catch (InstantiationException e) {
										// TODO: handle exception
									} catch (IllegalAccessException e) {
										// TODO: handle exception
									}

								}

							}

						}

					}
				}
			}

		} catch (Exception e) {

		}

	}

	@Override
	public List<String> validate(String validatorName, Object obj) {

		return null;
	}

}
