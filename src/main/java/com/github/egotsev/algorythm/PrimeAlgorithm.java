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
		if (cache.getLast() >= to) {
			return cache.getAllBetween(from, to);
		}
		List<Integer> result = calculateAllBetween(cache.getLast() + 1, to);
		cache.update(result);
		result = cache.getAllBetween(from, cache.getLast() + 1);
		result.addAll(result);
		return result;
	}
	
	protected abstract List<Integer> calculateAllBetween(int from, int to);
	
	public abstract Integer getNth(int n);

	public abstract String getName();

}
