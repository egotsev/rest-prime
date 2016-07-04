package com.github.egotsev.algorythm.impl;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.github.egotsev.service.PrimeCache;

public class EratosthenesTest {

	private Eratosthenes eratosthenes = new Eratosthenes(new PrimeCache());
	
	@Test
	public final void testCalculateAllBetween() {
		List<Integer> primes = eratosthenes.calculateAllBetween(1, 30);
		assertEquals(Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29), primes);
	}

	@Test(expected = UnsupportedOperationException.class)
	public final void testGetNth() {
		eratosthenes.getNth(1);
	}

	@Test
	public final void testGetName() {
		assertEquals("Eratosthenes", eratosthenes.getName());
	}

}
