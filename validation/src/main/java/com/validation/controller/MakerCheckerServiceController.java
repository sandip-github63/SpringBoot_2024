package com.validation.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.validation.expections.ValidationExpection;
import com.validation.request.RequestListing;
import com.validation.validator.utils.ValidatorUtils;

import jakarta.servlet.http.HttpServletRequest;

public abstract class MakerCheckerServiceController {

	protected final String GET_PENDING_LIST = "/private/getPendingList";

	@PostMapping(value = GET_PENDING_LIST)
	public ResponseEntity<String> getPendingList(@RequestBody RequestListing listingRequest,
			HttpServletRequest httpRequest) throws ValidationExpection {

		System.out.println("ListingRequest Data is found :" + listingRequest.getData());

		ValidatorUtils.validate(listingRequest, MakerCheckerServiceController.class.getCanonicalName(),
				"getPendingList");

		return ResponseEntity.ok("fine");
	}

}
