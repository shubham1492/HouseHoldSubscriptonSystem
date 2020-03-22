package sms;

import java.util.HashMap;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

// Excel Reading class 
public class ExcelManager {

	private Fillo fillo;
	private Connection conn;
	private Recordset rs;
	private String subcriptionFile;
	protected static HashMap<String, HashMap<String, String>> itemsDetails = new HashMap<String, HashMap<String, String>>();

	public ExcelManager() {
		fillo = new Fillo();
		subcriptionFile = System.getProperty("user.dir") + SubcriptionConstant.FILE_PATH;
		System.out.println("user dir : " + subcriptionFile);
		createConnection();
		readExcel();
		closeConnection();
	}

	public void createConnection() {
		try {
			conn = fillo.getConnection(subcriptionFile);
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		conn.close();
	}

	// Read Excel data by Fillo libraray
	public void readExcel() {
		String query = "select * from subcription_table";
		try {
			rs = conn.executeQuery(query);
			while (rs.next()) {
				String key = rs.getField("items");
				String monday = rs.getField("Monday");
				String tuesday = rs.getField("Tuesday");
				String wednesday = rs.getField("Wednesday");
				String thrusday = rs.getField("Thrusday");
				String friday = rs.getField("Friday");
				String saturday = rs.getField("Saturday");
				String sunday = rs.getField("Sunday");
				HashMap<String, String> rateChart = new HashMap<>();
				rateChart.put("Monday", monday);
				rateChart.put("Tuesday", tuesday);
				rateChart.put("Wednesday", wednesday);
				rateChart.put("Thrusday", thrusday);
				rateChart.put("Friday", friday);
				rateChart.put("Saturday", saturday);
				rateChart.put("Sunday", sunday);
				itemsDetails.put(key, rateChart);
			}
		} catch (FilloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
