package sms;

public class SubcriptionManager extends SubcriptionCore {

	public void StartSubcriptionManager(String subcriptionType, String[] items) {

		switch (subcriptionType) {
		case "DAILY": {
			dailySubcription.calculateDailySubcription(subcriptionType, items);
			break;
		}
		case "WEEKLY": {
			weeklySubcription.calculateWeeklySubcription(subcriptionType, items);
			break;
		}
		case "BIWEEKLY": {
			biWeeklySubcription.calculateBiWeeklySubcription(subcriptionType, items);
			break;
		}
		case "MONTHLY": {
			monthlySubcription.calculateMonthlySubcription(subcriptionType, items);
			break;
		}
		default:
			break;
		}
	}

	public static void main(String[] args) {
		SubcriptionManager manager = new SubcriptionManager();
		manager.StartSubcriptionManager("DAILY", new String[] { "HI","TOI" });
	}
}
