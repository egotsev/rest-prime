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
		int firstNumberToCheck = to - primes.length + 1;
		double limit = Math.sqrt(to) - firstNumberToCheck;
		for (int i = 0; i < limit; i++) {
			if (!primes[i]) {
				int actualNumber = i + firstNumberToCheck;
				for (int j = actualNumber * actualNumber
						- firstNumberToCheck * 2; j < primes.length; j += actualNumber) {
					primes[j] = true;
				}
			}
		}
		List<Integer> result = getCache().getAllBetween(from, firstNumberToCheck);
		result.addAll(IntStream.range(firstNumberToCheck, to).filter(x -> !primes[x - firstNumberToCheck]).boxed()
				.collect(Collectors.toList()));
		return result;
	}

	@Override
	public Integer getNth(int n) {
		throw new UnsupportedOperationException("Getting nth prime number is not supported by this algorithm");
	}

	private boolean[] constructBooleanArray(int n) {
		Integer lastPrimeInCache = getCache().getLast();
		int firstToCheck = lastPrimeInCache + 1;
		int numbersToCheck = n - lastPrimeInCache;
		boolean[] primes = new boolean[numbersToCheck];
		primes[0] = firstToCheck % 2 == 0;
		getCache().parallelForEach(x -> {
			for (int i = (firstToCheck / x + 1) * x; i < n; i += x) {
				primes[i - firstToCheck] = true;
			}
		});
		return primes;
	}
}
