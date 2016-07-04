package com.github.egotsev.algorythm;

import java.util.List;

import com.github.egotsev.service.PrimeCache;

public abstract class PrimeAlgorithm {

	private PrimeCache cache;

	public PrimeAlgorithm(PrimeCache cache) {
		this.cache = cache;
	}

	public PrimeCache getCache() {
		return cache;
	}
	
	public List<Integer> getAllBetween(int from, int to) {
		List<Integer> allFromCache = cache.getAllBetween(from, to);
		if (cache.getLast() >= to - 1) {
			return allFromCache;
		}
		List<Integer> missingPrimes = calculateAllBetween(cache.getLast() + 1, to);
		cache.update(missingPrimes);
		allFromCache.addAll(missingPrimes);
		return allFromCache;
	}
	
	protected abstract List<Integer> calculateAllBetween(int from, int to);
	
	public abstract Integer getNth(int n);

	public abstract String getName();

}
