package com.validation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.validation.annotations.ProductType;
import com.validation.annotations.ProductType.PRODUCT;

@Controller("com.validation.controller.CurrencyController")
@RequestMapping("commons/currencyService")
@ProductType(product = PRODUCT.COMMONS)
public class CurrencyController extends MakerCheckerServiceController {

	private String GET_SOMETHIG = "/private/getSomething";

}
