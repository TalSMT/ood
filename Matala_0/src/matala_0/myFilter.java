package matala_0;

import java.util.ArrayList;

public abstract class myFilter implements Condition<String> {

	private NetWorks net;

	public myFilter(NetWorks net) {
		super();
		this.net = net;
	}

	// @Override public boolean test(Object s) {
	// return object.equals(s);
	// }

	// נבנה שיטה שתקבל תנאי ואת מערך הנטוורקס objectשלנו ותסנן אותו ע"פ קיום
	// התנאי

	public ArrayList<NetWorks> filter(ArrayList<NetWorks> mekori, Condition<NetWorks> condition) {
		// נבנה מערך עצמים שאליו יגיעו רק העצמים המסוננים
		ArrayList<NetWorks> mesunan = new ArrayList<>();
		// נעבור בלולאת פור על המערך המקורי ונראה באילו מקרים יש לנו את אותו זמן
		// כמו שנדרש, את הרשתות עם זמן זה נכניס למערך החדש
		for (int i = 0; i < mekori.size(); i++) {
			if (condition.test(mekori.get(i))) {
				System.out.println("זמן זהה");
				mesunan.add(mekori.get(i));
			}
		}

		return mesunan;
	}
}
