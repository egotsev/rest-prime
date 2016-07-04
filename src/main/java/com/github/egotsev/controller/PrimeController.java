package com.github.egotsev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.HeadersBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.github.egotsev.algorythm.impl.Eratosthenes;
import com.github.egotsev.model.IsPrime;
import com.github.egotsev.model.Prime;
import com.github.egotsev.service.AlgorithmService;
import com.github.egotsev.service.PrimeCache;

@RestController
public class PrimeController {

	@Autowired
	private PrimeCache primeCache;

	@Autowired
	private AlgorithmService algorithmService;

	@RequestMapping("/nth/{id}")
	public Prime getNth(@PathVariable("id") int number) {
		// TODO make usage of algorithms choosable
		Integer nth = algorithmService.get(Eratosthenes.NAME).getNth(number);
		return new Prime(nth);
	}

	@RequestMapping("/between/{from}/and/{to}")
	public List<Integer> getAllBetween(@PathVariable("from") int from, @PathVariable("to") int to) {
		from = from < 0 ? 0 : from;
		return algorithmService.get(Eratosthenes.NAME).getAllBetween(from, to);
	}

	@RequestMapping("/is_prime/{id}")
	public IsPrime isPrime(@PathVariable("id") int number) {
		Boolean isPrime = primeCache.isPrime(number);
		if (isPrime == null) {
			isPrime = !algorithmService.get(Eratosthenes.NAME).getAllBetween(number, number + 1).isEmpty();
		}
		return new IsPrime(number, isPrime);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/clear_cache")
	public ResponseEntity<Void> clearCache() {
		primeCache.clear();
		return ResponseEntity.noContent().build();
	}
}
