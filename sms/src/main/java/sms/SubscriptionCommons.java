package sms;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

// Common class which provide business logic  
public class SubscriptionCommons {
	private Calendar calendar;
	private SimpleDateFormat sdFormat;
	private Date date;

	public SubscriptionCommons() {
		calendar = Calendar.getInstance();
		date = calendar.getTime();
	}

	// Business Logic for  all subscription type
	public void calculateBy(String subcriptionType, String[] userItemList) {
		boolean isDaily = false;
		boolean isBiWeekly = false;
		int total = 0;
		String currentDay;
		HashMap<String, HashMap<String, String>> data = SubcriptionCore.itemsDetails;
		if (subcriptionType.equalsIgnoreCase("daily")) {
			System.out.println("Calculating price on \"Daily Subcription\"");
			sdFormat = new SimpleDateFormat("E");
			currentDay = sdFormat.format(date);
			isDaily = true;
		} else if (subcriptionType.equalsIgnoreCase("biweekly")) {
			System.out.println("Calculating price on \"BiWeekly Subcription\"");
		} else if (subcriptionType.equalsIgnoreCase("monthly")) {
			System.out.println("Calculating price on \"Monthly Subcription\"");
		}
		System.out.println("Calculating price on \"Weekly Subcription\"");
		for (String userItem : userItemList) {
			Set<String> itemLevel = data.keySet();
			for (String itm : itemLevel) {
				if (itm.equalsIgnoreCase(userItem)) {
					System.out.println(itm);
					System.out.println("=================================================");
					HashMap<String, String> dayPriceMap = data.get(itm);
					Set<String> dayPriceLevel = dayPriceMap.keySet();
					for (String day : dayPriceLevel) {
						int price = Integer.parseInt(dayPriceMap.get(day));
						System.out.println(day + " : Price = " + price);
						total = total + price;
					}
					System.out.println("=================================================");
					System.out.println("Total : " + total);
					total = 0;
					System.out.println("=================================================");
					System.out.println(
							"--------------------------------------------------------------------------------------");
				}
			}
		}

	}
}
