package com.test;

import java.util.List;

import junit.framework.TestCase;

import com.lunatech.assessment.bean.CountryBean;
import com.lunatech.assessment.helper.DataHelper;

public class CodeAssessentTest extends TestCase{


	public void testGetReport() {
		DataHelper dh = new DataHelper();
		
		List<CountryBean> cb = dh.getReport();
		
		assertTrue(cb.size() > 0);
	}

	public void testSearchAirportBasedOnCountryCode() {
		DataHelper dh = new DataHelper();
		
		List<CountryBean> cb = dh.getAirportDetail(null,"AD");
		
		assertTrue(cb.size() > 0);
	}
	
	public void testSearchAirportBasedOnCountryName() {
		DataHelper dh = new DataHelper();
		
		List<CountryBean> cb = dh.getAirportDetail("Andorra",null);
		
		assertTrue(cb.size() > 0);
	}
	
	public void testSearchAirportForUnavailableCounryCode() {
		DataHelper dh = new DataHelper();
		
		List<CountryBean> cb = dh.getAirportDetail(null,"KK");
		
		assertTrue(cb.size() == 0);
	}
	public void testSearchAirportForUnavailableCounryName() {
		DataHelper dh = new DataHelper();
		
		List<CountryBean> cb = dh.getAirportDetail("TEST",null);
		
		assertTrue(cb.size() == 0);
	}
	

}
