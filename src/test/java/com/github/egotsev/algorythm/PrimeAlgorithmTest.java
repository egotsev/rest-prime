package com.github.egotsev.algorythm;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

import com.github.egotsev.service.PrimeCache;

public class PrimeAlgorithmTest {

	private PrimeCache cache = new PrimeCache();

	private PrimeAlgorithm primeAlgorithm = new PrimeAlgorithm(cache) {

		@Override
		public Integer getNth(int n) {
			return null;
		}

		@Override
		public String getName() {
			return null;
		}

		@Override
		protected List<Integer> calculateAllBetween(int from, int to) {
			return Arrays.asList(3, 5, 7, 11, 13).stream().filter(x -> x >= from && x < to)
					.collect(Collectors.toList());
		}
	};

	@Test
	public final void testGetAllBetween() {
		assertEquals("Excludes 'to' value from primes", Collections.emptyList(), primeAlgorithm.getAllBetween(0, 2));
		assertEquals("Uses cache when 'to' is <= than last value in cache + 1", Arrays.asList(2), primeAlgorithm.getAllBetween(0, 3));
		assertEquals("Calls abstract calculateAllBetween when the values are not in the cache",
				Arrays.asList(2, 3, 5, 7), primeAlgorithm.getAllBetween(0, 10));
		assertEquals("Updates cache", Arrays.asList(2, 3, 5, 7), cache.getAllBetween(0, 10));
	}

}
