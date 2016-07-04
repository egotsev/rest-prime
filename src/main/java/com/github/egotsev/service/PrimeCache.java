package com.github.egotsev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PrimeCache {

	private final List<Integer> primes = new ArrayList<>(500);

	{
		primes.add(2);
	}
	
	public void parallelForEach(Consumer<Integer> function) {
		primes.parallelStream().forEach(function::accept);
	}

	public Integer getNth(int n) {
		if (primes.size() <= n)
			return null;
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

	public synchronized void update(List<Integer> newPrimes) {
		// TODO make this async
		int indexOfLast = newPrimes.indexOf(getLast());
		primes.addAll(indexOfLast != -1 ? 
				newPrimes.subList(indexOfLast, newPrimes.size()) : 
				newPrimes);
	}

	public List<Integer> getAllBetween(int from, int to) {
		return primes.stream().filter(x -> x >= from && x < to).collect(Collectors.toList());
	}
}
