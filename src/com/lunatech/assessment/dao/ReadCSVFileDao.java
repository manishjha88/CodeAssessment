package com.lunatech.assessment.dao;

import java.util.List;

import com.lunatech.assessment.bean.AirportBean;
import com.lunatech.assessment.bean.CountryBean;
import com.lunatech.assessment.bean.RunwayBean;

/**
* Dao interface for csv file read.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/
public interface ReadCSVFileDao {

	/**
	 * @return list of country data
	 * @throws Exception 
	 */
	public List<CountryBean> getCountryDataList() throws Exception;

	/**
	 * @return list of airport data
	 * @throws Exception 
	 */
	public List<AirportBean> getAirportDataList() throws Exception;

	/**
	 * @return list of runway data
	 * @throws Exception 
	 */
	public List<RunwayBean> getRunwayDataList() throws Exception;


}
