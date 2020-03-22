package core;

import java.util.Calendar;

import subscriptions.BiWeeklySubcription;
import subscriptions.DailySubcription;
import subscriptions.MonthlySubcription;
import subscriptions.WeeklySubcription;

//Core class it will provide all subscription class object
public class SubcriptionCore extends ExcelManager {

	
	protected static DailySubcription dailySubcription;
	protected static WeeklySubcription weeklySubcription;
	protected static BiWeeklySubcription biWeeklySubcription;
	protected static MonthlySubcription monthlySubcription;
	protected static Calendar calendar = Calendar.getInstance();

	public SubcriptionCore() {
		dailySubcription = new DailySubcription();
		weeklySubcription = new WeeklySubcription();
		biWeeklySubcription = new BiWeeklySubcription();
		monthlySubcription = new MonthlySubcription();
	}
}
