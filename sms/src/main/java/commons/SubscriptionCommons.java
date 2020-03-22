package commons;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import core.SubcriptionCore;

// Common class which provide business logic  
public class SubscriptionCommons {
	private Calendar calendar;
	private SimpleDateFormat sdFormat;
	private Date date;
	final static Logger logger = Logger.getLogger(SubscriptionCommons.class);


	LinkedHashMap<String, Integer> dayMap = new LinkedHashMap<>();

	public SubscriptionCommons() {
		PropertyConfigurator.configure("log4j.properties");

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
	public void calculateBy(String subcriptionType,String[] userItemList) {
		boolean isDaily = false;
		boolean isBiWeekly = false;
		boolean isWeekly = false;
		boolean isMonthly = false;
		String currentDay;
		//LinkedHashMap<String,LinkedHashMap<String, LinkedHashMap<String, String>>> data = SubcriptionCore.itemsDetails;
		if (subcriptionType.equalsIgnoreCase("daily")) {
			logger.info("Calculating price on \"Daily Subcription\"");
			isDaily = true;
			//getCalculatedCurrentDaySubscriptionPrice(currentDay,category, userItemList);
			getCalculatedSubscriptionPrice(isDaily,isWeekly,isBiWeekly,isMonthly,userItemList);

		} else if (subcriptionType.equalsIgnoreCase("biweekly")) {
			logger.info("Calculating price on \"BiWeekly Subcription\"");
			isBiWeekly = true;
			getCalculatedSubscriptionPrice(isDaily,isWeekly,isBiWeekly,isMonthly,userItemList);

		} else if (subcriptionType.equalsIgnoreCase("monthly")) {
			logger.info("Calculating price on \"Monthly Subcription\"");
			isMonthly = true;
			getCalculatedSubscriptionPrice(isDaily,isWeekly,isBiWeekly,isMonthly,userItemList);
		}
		else {
			logger.info("Calculating price on \"Weekly Subcription\"");
			isWeekly = true;
			getCalculatedSubscriptionPrice(isDaily,isWeekly,isBiWeekly,isMonthly,userItemList);
		}
	}

/*
	public void getCalculatedCurrentDaySubscriptionPrice(String currentDay ,String category, String [] userItemList){

		LinkedHashMap<String, LinkedHashMap<String, String>> data = SubcriptionCore.itemsDetails;

		for (String userItem : userItemList) {
			Set<String> itemLevel = data.keySet();
			for (String itm : itemLevel) {
				if (itm.equalsIgnoreCase(userItem)) {
					logger.info(category+" --> "+itm);
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
*/
	public void getCalculatedSubscriptionPrice(boolean isDaily,boolean isWeekly, boolean isBiWeekly , boolean isMonthly,String[] userItemList) {
 		int total = 0;
		Set<String> dayPriceLevel = null;
		HashMap<String, String> dayPriceMap = null;
		sdFormat = new SimpleDateFormat("EEEE");
		String currentDay = sdFormat.format(date);
		LinkedHashMap<String, LinkedHashMap<String, String>> data = SubcriptionCore.itemsDetails;
		for (String userItem : userItemList) {
			Set<String> itemLevel = data.keySet();
			for (String itm : itemLevel) {
				if (itm.equalsIgnoreCase(userItem)) {
					logger.info("Item : "+itm);
					logger.info("--------------------------");
						dayPriceMap = data.get(itm);
						dayPriceLevel = dayPriceMap.keySet();
						for (String day : dayPriceLevel) {
							int price = Integer.parseInt(dayPriceMap.get(day));
							if(isDaily) {
							   if(day.equalsIgnoreCase(currentDay))
								   logger.info(day + " : Price = " + price);
							}else {
								total = total + price;
							}		
						}
					if(isWeekly) {
						logger.info("Weekly Total Price :"+total);
					}
					if(isBiWeekly) {
						total = total*2;
						logger.info("BiWeekly Total Price: "+ total);
						total=0;
					}
					if(isMonthly) {
                        total =0;
						ArrayList<Integer> weekprice = new ArrayList<>();
						dayPriceMap = data.get(itm);
						dayPriceLevel = dayPriceMap.keySet();
						for (String day : dayPriceLevel) {
							int price = Integer.parseInt(dayPriceMap.get(day));
							weekprice.add(price);
							total = total + price;
						}
						calculateBy2(weekprice, total);
						total =0;						
						
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

						
					}

				}
			}

			
		}
		//total = 0;
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
		logger.info("total value for month : " + total);
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
