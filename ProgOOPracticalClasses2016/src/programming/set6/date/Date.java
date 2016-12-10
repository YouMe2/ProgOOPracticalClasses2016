package programming.set6.date;

public class Date {

	private static final String[] MONTH_NAMES = new String[] { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	private int year;
	private int month;
	private int day;

	public Date(int year, int month, int day) {
		if (!validate(year, month, day))
			throw new IllegalArgumentException("The given date is not valid!");

		this.year = year;
		this.month = month;
		this.day = day;

	}

	public static int getDaysInMonth(int year, int month) {

		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
			return 31;
		}

		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30;
		}

		if (month == 2) {
			if (isLeapYear(year))
				return 29;
			else
				return 28;

		}

		return 0;

	}

	private static boolean isLeapYear(int year) {
		return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
	}

	private static boolean validate(int year, int month, int day) {

		return (year > -1 && month > 0 && month <= 12 && day > 0 && day <= getDaysInMonth(year, month));

	}

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDay() {
		return day;
	}

	public int dayOfYear() {

		int days = 0;

		for (int i = 1; i < getMonth(); i++) {
			days += getDaysInMonth(getYear(), i);
		}

		days += getDay();

		return days;
	}

	public int sameYearDiff(Date other) {

		if (other.getYear() != this.getYear())
			return 0;

		return other.dayOfYear() - this.dayOfYear();
	}

	@Override
	public String toString() {

		return MONTH_NAMES[getMonth()] + " " + getDay() + ", " + getYear();
	}

}
