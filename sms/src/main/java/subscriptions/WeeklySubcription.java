package subscriptions;

import commons.SubscriptionCommons;

//Weekly subscription class
public class WeeklySubcription extends SubscriptionCommons {

	// Method to calculate Weekly Subscription
	public void calculateWeeklySubcription(String subcriptionType,String[] items) {
		calculateBy(subcriptionType,items);
	}
}
