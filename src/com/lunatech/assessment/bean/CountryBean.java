package com.lunatech.assessment.bean;

import java.util.List;

/**
* Java beans for country details implements Comparable<T>.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/
public class CountryBean implements Comparable<CountryBean>{
	
	private String id;
	private String code;
	private String name;
	private String continent;
	private String wikipedia_link;
	private String keywords;
	
	private int airportCount;
	
	private int runwayCount;
	
	private List<AirportBean> airportList;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the continent
	 */
	public String getContinent() {
		return continent;
	}
	/**
	 * @param continent the continent to set
	 */
	public void setContinent(String continent) {
		this.continent = continent;
	}
	/**
	 * @return the wikipedia_link
	 */
	public String getWikipedia_link() {
		return wikipedia_link;
	}
	/**
	 * @param wikipedia_link the wikipedia_link to set
	 */
	public void setWikipedia_link(String wikipedia_link) {
		this.wikipedia_link = wikipedia_link;
	}
	/**
	 * @return the keywords
	 */
	public String getKeywords() {
		return keywords;
	}
	/**
	 * @param keywords the keywords to set
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	/**
	 * @return the airportList
	 */
	public List<AirportBean> getAirportList() {
		return airportList;
	}
	/**
	 * @param airportList the airportList to set
	 */
	public void setAirportList(List<AirportBean> airportList) {
		this.airportList = airportList;
	}
	/**
	 * @return the airportCount
	 */
	public int getAirportCount() {
		return airportCount;
	}
	/**
	 * @param airportCount the airportCount to set
	 */
	public void setAirportCount(int airportCount) {
		this.airportCount = airportCount;
	}
	/**
	 * @return the runwayCount
	 */
	public int getRunwayCount() {
		return runwayCount;
	}
	/**
	 * @param runwayCount the runwayCount to set
	 */
	public void setRunwayCount(int runwayCount) {
		this.runwayCount = runwayCount;
	}
	
	@Override
	public int compareTo(CountryBean o) {
		int compareCount = ((CountryBean) o).getAirportCount(); 
		
		//ascending order
		return this.airportCount - compareCount;
		
	}
	
	

}
