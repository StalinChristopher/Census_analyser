package com.yml.censusanalysertest;

import org.junit.Before;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Assert;

import com.yml.censusanalyser.CSVStateCensus;
import com.yml.censusanalyser.CensusAnalyserException;
import com.yml.censusanalyser.StateCensusAnalyser;

public class CensusAnalyserTeset {
	CSVStateCensus csvStateCensus;

	@Before
	public void initialize() {
		csvStateCensus = new CSVStateCensus();
	}

	/**
	 * Test case to match number of records
	 * 
	 * @throws CensusAnalyserException
	 */
	@Test
	public void givenStateCensusFileMatchNumberOfRecords() throws CensusAnalyserException {
		String path = "data/stateCensus.csv";
		csvStateCensus.loadCSVData(path);
		int expectedRecord = 8;
		int actual = csvStateCensus.getData().getStateCensusData().size();
		Assert.assertEquals(expectedRecord, actual);
	}

	@Test
	public void givenFileNameIncorrect() {
		String path = "data/incorrect.csv";
		try {
			csvStateCensus.loadCSVData(path);
		} catch (CensusAnalyserException e) {
			String expected = "Incorrect filename";
			String actual = e.getMessage();
			Assert.assertEquals(expected, actual);
		}
	}

	@Test
	public void givenFileNameCorrectButTypeIncorrect() {
		String path = "data/stateCensus.csv";
		try {
			csvStateCensus.loadCSVData(path);
		} catch (CensusAnalyserException e) {
			String expected = "Invalid fileType";
			String actual = e.getMessage();
			Assert.assertEquals(expected, actual);
		}
	}

	@Test
    public void throwCustomExceptionIfInvalidDelimitter() {
        try {
            String path = "data/incorrectDelimitter.csv";
            csvStateCensus.loadCSVData(path);
        } catch (CensusAnalyserException e) {
            String expected = "Invalid Delimitter";
            String actual = e.getMessage();
            Assert.assertEquals(expected, actual);
        }
    }
	
	@Test
	public void throwCustomexceptionIfInvalidHeader() {
		try {
            String path = "data/incorrectHeaders.csv";
            csvStateCensus.loadCSVData(path);
        } catch (CensusAnalyserException e) {
            String expected = "Invalid header";
            String actual = e.getMessage();
            Assert.assertEquals(expected, actual);
        }
	}

}
