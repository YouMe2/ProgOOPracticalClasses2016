package programming.set6.date;

/**
 * This class provides a simple representation of a date in the form af a year,
 * a month, and a day. It provides a basic set of operations for the dates.
 * 
 * @author Yannik
 *
 */
public class Date {


	private static final String[] MONTH_NAMES = new String[] { "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" };
	
	private int year;
	private int month;
	private int day;

	/**
	 * Constructs a new Date object with the given year, month and day.
	 * 
	 * @param year
	 *            The year
	 * @param month
	 *            The month as a number from 1 to 12
	 * @param day
	 *            The day
	 */
	public Date(int year, int month, int day) {
		if (!validate(year, month, day))
			throw new IllegalArgumentException("The given date is not valid!");

		this.year = year;
		this.month = month;
		this.day = day;

	}

	/**
	 * This method returns the number of days in the specified month of the
	 * specified year. If {@code month} is not a valid number between 1 and 12
	 * the return will always be 0.
	 * 
	 * @param year
	 *            The year
	 * @param month
	 *            The number of the month starting with 1 for January
	 * @return The number of days in the given month of the given year
	 */
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

	/**
	 * This method returns true when the given year is a leap year and false if
	 * not.
	 * 
	 * @param year
	 *            The year
	 * @return true when the given year is a leap year and false if not
	 */
	public static boolean isLeapYear(int year) {
		return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
	}

	/**
	 * This method determines whether the given year, month and day are a valid
	 * date.
	 * 
	 * @param year
	 *            The year
	 * @param month
	 *            The month as a number from 1 to 12
	 * @param day
	 *            The day
	 * @return true if the date is valid, false if not
	 */
	public static boolean validate(int year, int month, int day) {

		return (year > -1 && month > 0 && month <= 12 && day > 0 && day <= getDaysInMonth(year, month));

	}

	/**
	 * Returns the year of the current date.
	 * 
	 * @return The year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Returns the month of the current date as a number.
	 * 
	 * @return The month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Returns the day of the current date.
	 * 
	 * @return The day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * This method returns the number of the current date's day in the current
	 * date's year.
	 * 
	 * @return The number of the day in the year
	 */
	public int dayOfYear() {

		int days = 0;

		for (int i = 1; i < getMonth(); i++) {
			days += getDaysInMonth(getYear(), i);
		}

		days += getDay();

		return days;
	}

	/**
	 * This method returns the number of days that the other date is off of the
	 * current date. If the year of the other date is different to the current
	 * date's year the return will always be 0.
	 * 
	 * 
	 * @param other
	 *            The other date
	 * @return The days between the current and the other date
	 */
	public int sameYearDiff(Date other) {

		if (other.getYear() != this.getYear())
			return 0;

		return other.dayOfYear() - this.dayOfYear();
	}

	@Override
	public String toString() {

		return MONTH_NAMES[getMonth()-1] + " " + getDay() + ", " + getYear();
	}

}
