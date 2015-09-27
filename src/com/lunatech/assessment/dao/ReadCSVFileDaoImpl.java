package com.lunatech.assessment.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

import com.lunatech.assessment.bean.AirportBean;
import com.lunatech.assessment.bean.CountryBean;
import com.lunatech.assessment.bean.RunwayBean;

/**
* Dao implentation for reading csv file.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/

public class ReadCSVFileDaoImpl implements ReadCSVFileDao {

	/**
	 * @return list of country data
	 */
	public List<CountryBean> getCountryDataList() throws Exception {

		List<CountryBean> countryList = null;
		CsvToBean<CountryBean> csv = new CsvToBean<CountryBean>();
		try {

			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("data/countries.csv");

			CSVReader csvReader = new CSVReader(new InputStreamReader(in));

			// Set column mapping strategy
			countryList = csv.parse(setCountryMapping(), csvReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return countryList;
	}

	/**
	 * @return list of airport data
	 */
	public List<AirportBean> getAirportDataList() throws Exception {

		List<AirportBean> airportList = null;
		CsvToBean<AirportBean> csv = new CsvToBean<AirportBean>();

		try {

			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("data/airports.csv");

			CSVReader csvReader = new CSVReader(new InputStreamReader(in));
			// Set column mapping strategy
			airportList = csv.parse(setAirportMapping(), csvReader);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return airportList;
	}

	/**
	 * @return list of runway data
	 */
	public List<RunwayBean> getRunwayDataList() throws Exception {

		List<RunwayBean> runwayList = null;

		CsvToBean<RunwayBean> csv = new CsvToBean<RunwayBean>();
		try {

			InputStream in = this.getClass().getClassLoader()
					.getResourceAsStream("data/runways.csv");

			CSVReader csvReader = new CSVReader(new InputStreamReader(in));
			// Set column mapping strategy
			runwayList = csv.parse(setRunwayMapping(), csvReader);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return runwayList;
	}

	private static ColumnPositionMappingStrategy<CountryBean> setCountryMapping() {
		ColumnPositionMappingStrategy<CountryBean> strategy = new ColumnPositionMappingStrategy<CountryBean>();
		strategy.setType(CountryBean.class);
		String[] columns = new String[] { "id", "code", "name", "continent",
				"wikipedia_link", "keywords" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

	private static ColumnPositionMappingStrategy<AirportBean> setAirportMapping() {
		ColumnPositionMappingStrategy<AirportBean> strategy = new ColumnPositionMappingStrategy<AirportBean>();
		strategy.setType(AirportBean.class);
		String[] columns = new String[] { "id", "ident", "type", "name",
				"latitude_deg", "longitude_deg", "elevation_ft", "continent",
				"iso_country", "iso_region", "municipality",
				"scheduled_service", "gps_code", "iata_code", "local_code",
				"home_link", "wikipedia_link", "keywords" };
		strategy.setColumnMapping(columns);
		return strategy;
	}

	private static ColumnPositionMappingStrategy<RunwayBean> setRunwayMapping() {
		ColumnPositionMappingStrategy<RunwayBean> strategy = new ColumnPositionMappingStrategy<RunwayBean>();
		strategy.setType(RunwayBean.class);
		String[] columns = new String[] { "id", "airport_ref", "airport_ident",
				"length_ft", "width_ft", "surface", "lighted", "closed",
				"le_ident", "le_latitude_deg", "le_longitude_deg",
				"le_elevation_ft", "le_heading_degT",
				"le_displaced_threshold_ft", "he_ident", "he_latitude_deg",
				"he_longitude_deg", "he_elevation_ft", "he_heading_degT",
				"he_displaced_threshold_ft", };
		strategy.setColumnMapping(columns);
		return strategy;
	}

}
