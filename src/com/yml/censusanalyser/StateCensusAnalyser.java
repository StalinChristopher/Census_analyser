package com.yml.censusanalyser;

import java.util.Arrays;

public class StateCensusAnalyser {
	public static void main(String[] args) throws CensusAnalyserException {
		CSVStateCensus census = new CSVStateCensus();
		census.loadCSVData("data/stateCensus.csv");
		CensusData censusData = census.getData();
		
		System.out.println(Arrays.toString(censusData.getHeader()));
		
		for(String[] row : censusData.getStateCensusData()) {
			System.out.println(Arrays.toString(row));
		}
	}
}
