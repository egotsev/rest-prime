package com.github.egotsev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PrimeCache {

	private List<Integer> primes = new ArrayList<>(500);

	{
		primes.add(2);
	}

	public Integer getNth(int n) {
		return primes.get(n);
	}

	public Boolean isPrime(int value) {
		if (getLast() < value) {
			return null;
		}
		return primes.contains(value);
	}

	public Integer getLast() {
		return primes.get(primes.size() - 1);
	}

	public boolean setNth(int n, Integer nthPrime) {
		if (n < primes.size())
			return false;
		primes.add(n, nthPrime);
		return true;
	}

	public synchronized void update(List<Integer> newPrimes) {
		int indexOfLast = newPrimes.indexOf(getLast());
		primes.addAll(indexOfLast != -1 ? 
				newPrimes.subList(indexOfLast, newPrimes.size()) : 
				newPrimes);
	}

	public List<Integer> getAllBetween(int from, int to) {
		return primes.stream().filter(x -> x >= from && x < to).collect(Collectors.toList());
	}
}
