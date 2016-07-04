package com.github.egotsev.service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class PrimeCache {

	private final SortedSet<Integer> primes = new TreeSet<>();

	{
		primes.add(2);
	}
	
	public void parallelForEach(Consumer<Integer> function) {
		primes.parallelStream().forEach(function::accept);
	}

	public Boolean isPrime(int value) {
		if (getLast() < value) {
			return null;
		}
		return primes.contains(value);
	}

	public Integer getLast() {
		return primes.last();
	}

	public void update(List<Integer> newPrimes) {
		int indexOfLast = newPrimes.indexOf(getLast());
		primes.addAll(indexOfLast != -1 ? 
				newPrimes.subList(indexOfLast, newPrimes.size()) : 
				newPrimes);
	}

	public List<Integer> getAllBetween(int from, int to) {
		return primes.stream().filter(x -> x >= from && x < to).collect(Collectors.toList());
	}
}
