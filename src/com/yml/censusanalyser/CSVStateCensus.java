package com.yml.censusanalyser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

public class CSVStateCensus {
	CensusData censusData;

	public void loadCSVData(String path) throws CensusAnalyserException {
		String fileType = path.substring(path.lastIndexOf("."));
		if (!fileType.equals(".csv")) {
			throw new CensusAnalyserException("Invalid filetype");
		}
		File file = new File(path);
		if (!file.exists()) {
			System.out.println("File doesnot exist");
			return;
		}
		try {
			CSVReader reader = new CSVReader(new FileReader(file));
			String[] header = reader.readNext();
			if(!checkHeaders(header)) {
				throw new CensusAnalyserException("Invalid header");
			}
			List<String[]> stateCensusData = reader.readAll();
			if (!checkDelimitters(stateCensusData)) {
				throw new CensusAnalyserException("Invalid Delimitter");
			}
			censusData = new CensusData(header, stateCensusData);
			reader.close();
		} catch (FileNotFoundException f) {
			throw new CensusAnalyserException("Incorrect fileName");
		} catch (IOException | CsvException e) {
			e.printStackTrace();
		}
	}

	private boolean checkDelimitters(List<String[]> data) {
		if (data.size() == 0) {
			return true;
		}
		int checkLength = 5;
		if (data.size() < 5) {
			checkLength = data.size();
		}
		int prevDataLength = data.get(0).length;
		for (int i = 1; i < checkLength; i++) {
			if (data.get(i).length != prevDataLength) {
				return false;
			}
		}
		return true;
	}

	private boolean checkHeaders(String[] headers) {
        for (String header : headers) {
            if (header.equals("null") || header.equals(" ") || header.length() == 0) {
                return false;
            }
        }
        return true;
    }

	public CensusData getData() {
		return censusData;
	}
}
