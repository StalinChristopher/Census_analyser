package com.yml.censusanalyser;

import java.util.List;

public class CensusData {
	private String[] header;
	private List<String[]> stateCensusData;
	
	CensusData(String[] header, List<String[]> stateCensusData){
		this.header = header;
		this.stateCensusData = stateCensusData;
	}

	public String[] getHeader() {
		return header;
	}

	public void setHeader(String[] header) {
		this.header = header;
	}

	public List<String[]> getStateCensusData() {
		return stateCensusData;
	}

	public void setStateCensusData(List<String[]> stateCensusData) {
		this.stateCensusData = stateCensusData;
	}
}
