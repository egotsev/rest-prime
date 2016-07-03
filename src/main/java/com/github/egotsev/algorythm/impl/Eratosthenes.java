package com.github.egotsev.algorythm.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.github.egotsev.algorythm.PrimeAlgorithm;
import com.github.egotsev.service.PrimeCache;

public class Eratosthenes extends PrimeAlgorithm {

	public static final String NAME = "Eratosthenes";

	public Eratosthenes(PrimeCache cache) {
		super(cache);
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	protected List<Integer> calculateAllBetween(int from, int to) {
		boolean[] primes = constructBooleanArray(to);
		for (int i = 2; i < Math.sqrt(to); i++) {
			if (primes[i]) {
				for (int j = i * i; j < to; j += i) {
					primes[j] = false;
				}
			}
		}
		return IntStream.range(from, to).filter(x -> primes[x]).boxed().collect(Collectors.toList());
	}

	@Override
	public Integer getNth(int n) {
		throw new UnsupportedOperationException("Getting nth prime number is not supported by this algorithm");
	}

	private boolean[] constructBooleanArray(int n) {
		boolean[] primes = new boolean[n];
		for (int i = 0; i < primes.length; i++) {
			primes[i] = true;
		}
		return primes;
	}
}
