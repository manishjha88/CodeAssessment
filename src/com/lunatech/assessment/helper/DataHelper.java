package com.lunatech.assessment.helper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lunatech.assessment.bean.AirportBean;
import com.lunatech.assessment.bean.CountryBean;
import com.lunatech.assessment.bean.LatitudeBean;
import com.lunatech.assessment.bean.RunwayBean;
import com.lunatech.assessment.dao.ReadCSVFileDao;
import com.lunatech.assessment.dao.ReadCSVFileDaoImpl;

/**
* Class which process all the details and returns output.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/

public class DataHelper {

	/**
	 * @param countryName
	 * @return list of country details searched along with airport and runway
	 *         details
	 */
	public List<CountryBean> getAirportDetail(String countryName, String code) {

		try {
			ReadCSVFileDao r = new ReadCSVFileDaoImpl();

			// retrieve data from csv files 
			List<CountryBean> cb = r.getCountryDataList();
			List<AirportBean> ab = r.getAirportDataList();
			List<RunwayBean> rb = r.getRunwayDataList();
			if (cb != null && cb.size()>0) {
				CountryBean cBean = null;
				String countryCode = "";
				List<CountryBean> tempCountrytList = new ArrayList<CountryBean>();
				Iterator<CountryBean> cbIt = cb.iterator();
				while (cbIt.hasNext()) {
					cBean = cbIt.next();

					boolean check = false;
					if (countryName == null)
						check = code.equalsIgnoreCase(cBean.getCode());
					else
						// make a substring of country name same as size of
						// input if
						// input
						// size is less than actual country name to support
						// partial
						// search
						// case
						// for ex: zimb should retrieve details for zimbabwe,
						// united
						// should
						// retrieve details for UK & US both
						check = countryName.length() <= cBean.getName()
								.length()
								&& countryName.equalsIgnoreCase(cBean.getName()
										.substring(0, countryName.length()));
					if (check ) {
						countryCode = cBean.getCode();
						if (ab != null  && ab.size()>0) {
							Iterator<AirportBean> abIt = ab.iterator();
							List<AirportBean> tempAirportList = new ArrayList<AirportBean>();
							while (abIt.hasNext()) {

								AirportBean aBean = abIt.next();
								// get airport details for a country
								if (countryCode.equalsIgnoreCase(aBean
										.getIso_country())) {
									List<RunwayBean> tempRunWayList = new ArrayList<RunwayBean>();
									if (rb != null) {
										Iterator<RunwayBean> rbIt = rb
												.iterator();
										while (rbIt.hasNext()) {
											RunwayBean rBean = rbIt.next();
											// get runways details for
											// respective airport
											if (aBean.getId().equalsIgnoreCase(
													rBean.getAirport_ref())) {
												tempRunWayList.add(rBean);
											}
										}
									}
									aBean.setRunwayList(tempRunWayList);
									tempAirportList.add(aBean);
								}

							}

							cBean.setAirportList(tempAirportList);
						}
						tempCountrytList.add(cBean);
					}

				}
				return tempCountrytList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return list of countries with aiport counts, runways surface count
	 */
	public List<CountryBean> getReport() {
		try {
			List<CountryBean> cbList = new ArrayList<CountryBean>();

			ReadCSVFileDao r = new ReadCSVFileDaoImpl();

			// retrieve data from csv files
			List<CountryBean> cb = r.getCountryDataList();
			List<AirportBean> ab = r.getAirportDataList();
			List<RunwayBean> rb = r.getRunwayDataList();
			Set<String> set = new HashSet<String>();
			if (cb != null) {
				Iterator<CountryBean> cbIt = cb.iterator();
				while (cbIt.hasNext()) {
					int airportCount = 0;
					int runwayCount = 0;
					CountryBean bean = cbIt.next();
					if (ab != null) {
						Iterator<AirportBean> abIt = ab.iterator();
						while (abIt.hasNext()) {
							AirportBean aBean = abIt.next();
							// increase count of airport for a country
							if (bean.getCode().equalsIgnoreCase(
									aBean.getIso_country())) {
								airportCount++;
								if (rb != null) {
									Iterator<RunwayBean> rbIt = rb.iterator();
									while (rbIt.hasNext()) {
										RunwayBean rBean = rbIt.next();

										// increase count of unique runway type
										// for the
										// country
										if (rBean
												.getAirport_ref()
												.equalsIgnoreCase(aBean.getId())) {
											if (set.add(rBean.getSurface()))
												runwayCount++;

										}

									}
								}
							}
						}
					}
					bean.setAirportCount(airportCount);
					bean.setRunwayCount(runwayCount);
					cbList.add(bean);
				}
			}
			return cbList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 
	 * @return list of common runway latitudes
	 */
	public List<LatitudeBean> getCommonRunwayLatitude() {
		try {
			Map<String, Integer> map = new HashMap<String, Integer>();

			ReadCSVFileDao r = new ReadCSVFileDaoImpl();
			// retrieve data from csv files
			List<RunwayBean> rb = r.getRunwayDataList();
			if (rb != null) {
				Iterator<RunwayBean> rbIt = rb.iterator();
				rbIt.next();
				while (rbIt.hasNext()) {
					RunwayBean rBean = rbIt.next();
					// count latitudes for each runway.
					Integer count = map.get(rBean.getLe_ident());
					if (count == null)
						map.put(rBean.getLe_ident(), 1);
					else
						map.put(rBean.getLe_ident(), count.intValue() + 1);

				}

				Iterator<Map.Entry<String, Integer>> entries = map.entrySet()
						.iterator();
				LatitudeBean lBean = null;
				List<LatitudeBean> lList = new ArrayList<LatitudeBean>();
				while (entries.hasNext()) {
					lBean = new LatitudeBean();
					Map.Entry<String, Integer> entry = entries.next();
					String key = entry.getKey();
					int value = entry.getValue().intValue();
					lBean.setLatitudeName(key);
					lBean.setCount(value);
					lList.add(lBean);
				}

				Collections.sort(lList);

				return lList;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
