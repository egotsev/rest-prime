package com.github.egotsev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return algorithmService.get(Eratosthenes.NAME).getAllBetween(from, to);
	}

	@RequestMapping("/is_prime/{id}")
	public IsPrime isPrime(@PathVariable("id") int number) {
		Boolean isPrime = primeCache.isPrime(number);
		if (isPrime == null) {
			Integer nth = algorithmService.get(Eratosthenes.NAME).getNth(number + 1);
			isPrime = nth.equals(number);
		}
		return new IsPrime(number, isPrime);
	}
}
