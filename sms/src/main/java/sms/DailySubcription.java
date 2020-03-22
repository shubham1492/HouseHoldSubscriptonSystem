package sms;

public class DailySubcription extends SubscriptionCommons {

	public void calculateDailySubcription(String subcriptionType, String[] items) {
		calculateBy(subcriptionType, items);
		/*
		 * int total = 0; HashMap<String, HashMap<String, String>> data =
		 * SubcriptionCore.itemsDetails; for (String userItem : items) { Set<String>
		 * itemLevel = data.keySet(); for (String itm : itemLevel) { if
		 * (itm.equalsIgnoreCase(userItem)) { System.out.println(itm);
		 * System.out.println("=================================================");
		 * HashMap<String, String> dayPriceMap = data.get(itm); Set<String>
		 * dayPriceLevel = dayPriceMap.keySet(); for (String day : dayPriceLevel) { int
		 * price = Integer.parseInt(dayPriceMap.get(day)); System.out.println(day +
		 * " : Price = " + price); total = total + price; }
		 * System.out.println("=================================================");
		 * System.out.println("Total : " + total); total = 0;
		 * System.out.println("=================================================");
		 * System.out.println(
		 * "--------------------------------------------------------------------------------------"
		 * ); } } }
		 */
	}
}
