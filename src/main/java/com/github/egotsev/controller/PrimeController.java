package com.github.egotsev.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.egotsev.model.Prime;

@RestController
public class PrimeController {

	@RequestMapping("/nth/{id}")
	public Prime getNth(@PathVariable("id") Long number) {
		throw new UnsupportedOperationException("The operation is not yet implemented.");
	}
}
