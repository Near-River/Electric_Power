package electric.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static Date stringToDate(String sDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = dateFormat.parse(sDate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

}
