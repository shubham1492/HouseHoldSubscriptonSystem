package subscriptions;

import commons.SubscriptionCommons;

//Daily subscription class
public class DailySubcription extends SubscriptionCommons {

	// Method to calculate Daily Subscription
	public void calculateDailySubcription(String subcriptionType,String[] items) {
		calculateBy(subcriptionType,items);
	}
}
