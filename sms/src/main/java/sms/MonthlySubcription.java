package sms;

//Daily subscription class
public class MonthlySubcription extends SubscriptionCommons {

	// Method to calculate Monthly Subscription
	public void calculateMonthlySubcription(String subcriptionType, String[] items) {
		calculateBy(subcriptionType, items);
	}
}
