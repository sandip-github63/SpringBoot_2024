package com.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.validation.controller.CurrencyController;
import com.validation.request.RequestListing;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @author sandip
 * 
 * @Mock vs @InjectMocks
 * 
 *       Generally kya hota hai ki , jab v ham kisi v member k upar @Mock
 *       annotation nhi lagate hai, tab us ka actual object banta hai aur wo
 *       inject hota hai. so actual object bane se us class ka actual method run
 *       hota hai.
 * 
 *       example me suppose currencyservice k pass ek method hai getAllCurrency
 *       aur ye method repository ka use kar k repo.findall() database se
 *       interact karega.
 * 
 *       to es me jab ham mock nhi lagate tab acutal object banega ga
 *       currencyservice ka aur wo apne method ko v call karga ie
 *       getAllCurrency() and ye method acutal repo ka use kar k database se
 *       interact karga.
 * 
 *       But agar ham databse se interact karna hi nhi chahte tab kya hona
 *       chahiye use situation me ham @Mock ka use karte hai es si ham ye kahte
 *       hai ki tum actual method k jagah pe mera method use karna.us case me
 *       database call nhi hota hai.
 * 
 * @Mock vs @InjectMock
 * 
 * @Mock lagane se ham us field ko hi mock karte hai jis pe ye apply rhta hai.
 * @InjectMock annotation se ham kahte hai ki es class field k jitne v dependent
 *             member hai wo mocking honge.
 * 
 */
@ExtendWith(MockitoExtension.class)
public class CurrencyControllerTest {

	/*
	 * @Autowired private CurrencyController currencyController;
	 */

	/*
	 * @Autowired private HttpServletRequest request;
	 */

	@Test
	public void getPendingListTest() {

		// MTA
		CurrencyController currencyController = new CurrencyController();

		HttpServletRequest request = mock(HttpServletRequest.class);

		// Mocking
		RequestListing requestListing = new RequestListing();
		requestListing.setData("Sandip");

		// Testing
		ResponseEntity<?> response = currencyController.getPendingList(requestListing, request);

		// Assertion

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("not fine", response.getBody());

	}

}
