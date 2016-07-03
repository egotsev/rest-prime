package com.github.egotsev.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.egotsev.algorythm.PrimeAlgorithm;
import com.github.egotsev.algorythm.impl.Eratosthenes;

@Service
public class AlgorithmService {

	private Map<String, PrimeAlgorithm> algorithms = new HashMap<>();

	@Autowired
	private PrimeCache cache;
	
	@PostConstruct
	public void init() {
		// TODO load all algorithms with reflection
		add(new Eratosthenes(cache));
	}
	
	public PrimeAlgorithm get(String name) {
		return algorithms.get(name);
	}
	
	public void add(PrimeAlgorithm algorythm) {
		algorithms.put(algorythm.getName(), algorythm);
	}
}
