package com.github.egotsev.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

public class PrimeCacheTest {

	private PrimeCache cache;
	
	@Before
	public final void setUp() {
		cache = new PrimeCache();
	}
	
	@Test
	public final void testParallelForEach() {
		cache.update(Arrays.asList(3, 5, 7));
		ArrayList<Integer> result = new ArrayList<>();
		cache.parallelForEach(result::add);
		assertTrue(Arrays.asList(2, 3, 5, 7).containsAll(result));
	}

	@Test
	public final void testGetNth() {
		assertEquals("has 2 as initial first value", (Integer) 2, cache.getNth(0));
		cache.update(Arrays.asList(3));
		assertEquals("returns correct nth value", (Integer) 3, cache.getNth(1));
		assertNull("returns null when there's not yet nth value in cache", cache.getNth(2));
	}

	@Test
	public final void testIsPrime() {
		assertTrue("has 2 as initial first value and it is prime", cache.isPrime(2));
		cache.update(Arrays.asList(3, 5, 7));
		assertFalse("correctly falsy for not prime number", cache.isPrime(4));
		assertNull("returns null when value is out of the cache scope", cache.isPrime(8));
	}

	@Test
	public final void testGetLast() {
		assertEquals((Integer) 2, cache.getLast());
	}

	@Test
	public final void testUpdate() {
		cache.update(Arrays.asList(3, 5, 7));
		assertEquals("updates cache correctly", Arrays.asList(2, 3, 5, 7), cache.getAllBetween(0, 10));
	}

	@Test
	public final void testGetAllBetween() {
		assertEquals("has 2 as initial first value", Arrays.asList(2), cache.getAllBetween(0, 3));
		cache.update(Arrays.asList(3, 5));
		assertEquals("returns only values in the range", Arrays.asList(2), cache.getAllBetween(0, 3));
	}

}
