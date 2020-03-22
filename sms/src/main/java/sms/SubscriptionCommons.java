package sms;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Set;

// Common class which provide business logic  
public class SubscriptionCommons {
	private Calendar calendar;
	private SimpleDateFormat sdFormat;
	private Date date;



	LinkedHashMap<String, Integer> dayMap = new LinkedHashMap<>();

	public SubscriptionCommons() {
		dayMap.put("Tuesday", 6);
		dayMap.put("Wednesday", 5);
		dayMap.put("Thrusday", 4);
		dayMap.put("Friday", 3);
		dayMap.put("Saturday", 2);
		dayMap.put("Sunday", 1);

		calendar = Calendar.getInstance();
		date = calendar.getTime();
	}


	// Business Logic for  all subscription type
	public void calculateBy(String subcriptionType, String[] userItemList) {
		boolean isDaily = false;
		boolean isBiWeekly = false;
		boolean isWeekly = false;
		boolean isMonthly = false;
		int total = 0;
		String currentDay;
		LinkedHashMap<String, LinkedHashMap<String, String>> data = SubcriptionCore.itemsDetails;
		if (subcriptionType.equalsIgnoreCase("daily")) {
			System.out.println("Calculating price on \"Daily Subcription\"");
			sdFormat = new SimpleDateFormat("EEEE");
			currentDay = sdFormat.format(date);
			isDaily = true;
			getCalculatedCurrentDaySubscriptionPrice(currentDay, userItemList);


		} else if (subcriptionType.equalsIgnoreCase("biweekly")) {
			System.out.println("Calculating price on \"BiWeekly Subcription\"");
			isBiWeekly = true;
			getCalculatedSubscriptionPrice(isWeekly,isBiWeekly,isMonthly,userItemList);

		} else if (subcriptionType.equalsIgnoreCase("monthly")) {
			System.out.println("Calculating price on \"Monthly Subcription\"");
			isMonthly = true;
			getCalculatedSubscriptionPrice(isWeekly,isBiWeekly,isMonthly,userItemList);
		}
		else {
			isWeekly = true;
			getCalculatedSubscriptionPrice(isWeekly,isBiWeekly,isMonthly,userItemList);
		}
	}


	public void getCalculatedCurrentDaySubscriptionPrice(String currentDay , String [] userItemList){

		LinkedHashMap<String, LinkedHashMap<String, String>> data = SubcriptionCore.itemsDetails;
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
						if(day.equalsIgnoreCase(currentDay)) {
							int price = Integer.parseInt(dayPriceMap.get(day));
							System.out.println(day + " : Price = " + price);

						}
					}

				}
			}

		}

	}



	public void getCalculatedSubscriptionPrice(boolean isWeekly, boolean isBiWeekly , boolean isMonthly, String[] userItemList) {
		int total = 0;
		Set<String> dayPriceLevel = null;
		HashMap<String, String> dayPriceMap = null;
		LinkedHashMap<String, LinkedHashMap<String, String>> data = SubcriptionCore.itemsDetails;
		for (String userItem : userItemList) {
			Set<String> itemLevel = data.keySet();
			for (String itm : itemLevel) {
				if (itm.equalsIgnoreCase(userItem)) {
					System.out.println(itm);
					System.out.println("=================================================");
					if(isWeekly) {
						dayPriceMap = data.get(itm);
						dayPriceLevel = dayPriceMap.keySet();
						for (String day : dayPriceLevel) {
							int price = Integer.parseInt(dayPriceMap.get(day));
							System.out.println(day + " : Price = " + price);
							total = total + price;
						}
						System.out.println("=================================================");
					}
					if(isBiWeekly) {
						total = total*2;
						System.out.print("BiWeekly ");
					}
					if(isMonthly) {
						
						/**
						 *  We can also Use below mentioned code for calculate monthly Expenses 
						 */
						
						/*int monthDayCount = calendar.getActualMaximum(calendar.DAY_OF_MONTH);
						for(int i=1;i<=monthDayCount ;i++) {

							int year = calendar.get(Calendar.YEAR);
							int month = calendar.get(Calendar.MONTH)+1;
							int day = i; 
							// First convert to Date. This is one of the many ways.
							String dateString = String.format("%d-%d-%d", year, month, day);
							Date date;
							try {
								date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
								// Then get the day of week from the Date based on specific locale.
								String dayOfWeek = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date);
								dayPriceLevel = dayPriceMap.keySet();
								for (String dayString : dayPriceLevel) {
									if(dayString.equalsIgnoreCase(dayOfWeek)) {
										int price = Integer.parseInt(dayPriceMap.get(dayString));
										System.out.println(day + " : Price = " + price);
										total = total + price;
										break;
									}
								}
							} catch (ParseException e) {
								e.printStackTrace();
							}
						}*/

                        // Second logic
						ArrayList<Integer> weekprice = new ArrayList<>();
						dayPriceMap = data.get(itm);
						dayPriceLevel = dayPriceMap.keySet();
						for (String day : dayPriceLevel) {
							int price = Integer.parseInt(dayPriceMap.get(day));
							weekprice.add(price);
							System.out.println(day + " : Price = " + price);
							total = total + price;
						}
						System.out.println("=================================================");
						calculateBy2(weekprice, total);
						total = 0;
						System.out.println("=================================================");
						System.out.println(
								"--------------------------------------------------------------------------------------");
					}

				}
			}

			System.out.print("Monthly ");
		}
		System.out.println("Total : " + total);
		total = 0;
		System.out.println("=================================================");
		System.out.println(
				"--------------------------------------------------------------------------------------");
	}



public void calculateBy2(ArrayList<Integer> priceList, int weekValue) {
	int TDM = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	calendar.set(Calendar.DAY_OF_MONTH, 1);
	String day = new SimpleDateFormat("EEEE").format(calendar.getTime());
	int x = dayMap.get(day);
	int y = 28;
	int z = (TDM - (x + y));
	x = calculatePartialSubcription(priceList, (7 - x), false);
	z = z != 0 ? calculatePartialSubcription(priceList, z, true) : 0;
	y = weekValue * 4;
	int total = x + y + z;
	System.out.println("total value for month : " + total);
}

public int calculatePartialSubcription(ArrayList<Integer> price, int day, boolean isZ) {
	int count = 0;
	int start = -1;
	int max = -1;
	if (!isZ) {
		start = day;
		max = price.size();
	} else {
		start = 0;
		max = day;
	}
	if (day > 0) {
		for (int i = start; i < max; i++) {
			count = count + price.get(i);
		}
	} else {
		for (int i = price.size() - 1; i > Math.abs(day); i--) {
			count = count + price.get(i);
		}
		count = -(count);
	}
	return count;
}

























}
