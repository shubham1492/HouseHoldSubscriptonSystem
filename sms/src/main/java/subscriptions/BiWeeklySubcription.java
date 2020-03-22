package subscriptions;

import commons.SubscriptionCommons;

// Biweekly subscription class
public class BiWeeklySubcription extends SubscriptionCommons {

	// Method to calculate Biweekly Subscription
	public void calculateBiWeeklySubcription(String subcriptionType,String[] items) {
		calculateBy(subcriptionType,items);
	}
}
