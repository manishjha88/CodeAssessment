package com.lunatech.assessment.bean;

/**
* Java beans for runway latitude implements Comparable<T>.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/

public class LatitudeBean implements Comparable<LatitudeBean> {

	private String latitudeName;
	private int count;
	/**
	 * @return the latitudeName
	 */
	public String getLatitudeName() {
		return latitudeName;
	}
	/**
	 * @param latitudeName the latitudeName to set
	 */
	public void setLatitudeName(String latitudeName) {
		this.latitudeName = latitudeName;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	@Override
	public int compareTo(LatitudeBean o) {
		int compareCount = ((LatitudeBean) o).getCount(); 
		
		return this.count - compareCount;
		
	}
}
