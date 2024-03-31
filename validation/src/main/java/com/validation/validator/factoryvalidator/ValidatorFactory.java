package com.validation.validator.factoryvalidator;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.validation.validator.AbstractValidator;

public class ValidatorFactory {

	private static Map<String, Validator> validatorMap = new HashMap<String, Validator>();

	static {

		InputStream stream = null;
		Document validatorDoc;

		/*
		 * Reading file Validator.xml from src/main/resources to get Validator class by
		 * controller name
		 */

		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

			stream = classLoader.getResourceAsStream("src/main/resources/Validator.xml");

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			validatorDoc = dBuilder.parse(stream);
			validatorDoc.getDocumentElement().normalize();
			NodeList validatorList = validatorDoc.getElementsByTagName("validator");

			for (int i = 0; i < validatorList.getLength(); i++) {
				Node validatorNode = validatorList.item(i);

				if (validatorNode.getNodeType() == Node.ELEMENT_NODE) {
					Element validatorElement = (Element) validatorNode;
					String name = validatorElement.getAttribute("name");
					String validatorClass = validatorElement.getAttribute("validatorClass");
					Class<AbstractValidator> c = null;
					Validator interfaceType = null;

					try {

						c = (Class<AbstractValidator>) Class.forName(validatorClass);
						Method m = c.getMethod("getInstance", new Class[] {});
						interfaceType = (Validator) m.invoke(null, new Object[] {});

					} catch (ClassNotFoundException e) {
						// TODO: handle exception
						System.out.println("Class not found exception :" + e.getMessage());
					} catch (IllegalAccessException e) {
						// TODO: handle exception

						System.out.println(e);
					}

					validatorMap.put(name, interfaceType);

				}
			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		} finally {
			if (null != stream) {
				try {
					stream.close();
				} catch (IOException e) {
					// TODO: handle exception
				}
			}
		}

	}

	public static Validator findValidatory(String validatorName) {

		return validatorMap.get(validatorName);
	}

}
