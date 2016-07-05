package com.github.egotsev.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PrimeList {

	private Integer from;
	
	private Integer to;
	
	private List<Integer> result;
}
