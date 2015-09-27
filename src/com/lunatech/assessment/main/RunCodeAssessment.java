package com.lunatech.assessment.main;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.lunatech.assessment.bean.AirportBean;
import com.lunatech.assessment.bean.CountryBean;
import com.lunatech.assessment.bean.LatitudeBean;
import com.lunatech.assessment.bean.RunwayBean;
import com.lunatech.assessment.common.ApplicationConstants;
import com.lunatech.assessment.helper.DataHelper;

/**
* This class is entry class for the application contains main method and displays all the details.
*
* @author  M Jha
* @version 1.0
* @since   2015-09-26 
*/

public class RunCodeAssessment {
	public static void main(String app[]) {

		DataHelper q = new DataHelper();

		String userInput = "";
		Scanner s = new Scanner(System.in);
		System.out.println("Please select option ....");
		System.out.println("Press " + ApplicationConstants.QUERY
				+ " for query or " + ApplicationConstants.REPORT
				+ " for report");
		userInput = s.nextLine();
		if (ApplicationConstants.QUERY.equalsIgnoreCase(userInput)) {
			System.out.println("Select search option...");
			System.out.println("1 for Search by Country code ");
			System.out.println("2 for Search by Country Name ");
			userInput = s.nextLine();
			List<CountryBean> cbList = null;
			if (ApplicationConstants.BYCODE.equalsIgnoreCase(userInput)) {
				System.out.println("Enter Country Code:");
				userInput = s.nextLine();
				cbList = q.getAirportDetail(null, userInput);
			} else if (ApplicationConstants.BYNAME.equalsIgnoreCase(userInput)) {
				System.out.println("Enter Country Name");
				userInput = s.nextLine();
				cbList = q.getAirportDetail(userInput, null);
			} else {
				System.out
						.println("Sorry Wrong Input.. Exiting application...");
				System.exit(0);
			}
			Iterator<CountryBean> cIterate = cbList.iterator();
			while (cIterate.hasNext()) {
				CountryBean cb = cIterate.next();
				System.out.println("Country - " + cb.getName());
				System.out
						.println("-------------------------------------------------------");
				Iterator<AirportBean> aIterate = cb.getAirportList().iterator();
				while (aIterate.hasNext()) {
					AirportBean ab = aIterate.next();
					System.out.println("Airport - " + ab.getName());
					System.out
							.println("-------------------------------------------------------");
					Iterator<RunwayBean> rIterate = ab.getRunwayList()
							.iterator();
					while (rIterate.hasNext()) {
						RunwayBean rb = rIterate.next();
						System.out.println("Runway No : " + rb.getId());
						System.out.println("Runway Length - "
								+ rb.getLength_ft());
						System.out
								.println("Runway Width - " + rb.getWidth_ft());

					}
					System.out
							.println("-------------------------------------------------------");

				}
			}

		} else if (ApplicationConstants.REPORT.equalsIgnoreCase(userInput)) {
			System.out.println("PRINTING REPORTS......");
			List<CountryBean> cbList = q.getReport();
			Collections.sort(cbList);
			System.out
					.println("Top 10 Countried with Highest number of Airports Starts:");
			System.out
					.println("-------------------------------------------------------");
			int size = cbList.size();
			for (int i = 1; i <= 10; i++) {
				CountryBean cb = cbList.get(size - i);
				System.out.println(cb.getName() + " - " + cb.getAirportCount());
			}
			System.out
					.println("-------------------------------------------------------");
			System.out
					.println("Top 10 Countried with Highest number of Airports Ends:");
			System.out
					.println("10 Countries with Lowest number of Airports Starts: ");
			System.out
					.println("-------------------------------------------------------");

			for (int i = 1; i <= 10; i++) {
				CountryBean cb = cbList.get(i);
				System.out.println(cb.getName() + " - " + cb.getAirportCount());
			}
			System.out
					.println("-------------------------------------------------------");
			System.out
					.println("10 Countries with Lowest number of Airports Ends: ");
			System.out.println("Countries with Count of runway Types Starts: ");
			System.out
					.println("-------------------------------------------------------");
			for (int i = 1; i < cbList.size(); i++) {
				CountryBean cb = cbList.get(i);
				System.out.println(cb.getName() + " - " + cb.getRunwayCount());
			}
			System.out
					.println("-------------------------------------------------------");
			System.out.println("Countries with Count of runway Types Ends: ");
			List<LatitudeBean> lList = q.getCommonRunwayLatitude();
			System.out.println("Top 10 common Latitudes Starts: ");
			System.out
					.println("-------------------------------------------------------");
			size = lList.size();
			for (int i = 1; i <= 10; i++) {
				LatitudeBean lb = lList.get(size - i);
				System.out
						.println(lb.getLatitudeName() + " - " + lb.getCount());
			}
			System.out
					.println("-------------------------------------------------------");
			System.out.println("Top 10 common Latitudes Ends: ");
		} else {
			System.out.println("Sorry Wrong Input.. Exiting application...");
			System.exit(0);
		}

	}

}
