package cs4800.calendar;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;


/*
 * Calendar class that can be used to create 
 * an arrayList to represent months 
 */
public class Calendar {

	/*
	 * ints are used to store the month and year currently 
	 * selected in the calendar
	 * 
	 */
	private int year;
	private int month;
	private int day;
	
	//boolean will store if the current year is a leap year 
	private boolean leapYear;
	
	//List that will hold all of the months of the year
	private List<Month> months = null;
	
	/* List that will be filled with DayOfWeek objects 
	 * that represents a given month
	 */
	private List<DayOfWeek> calendarMonth = null;
	
	/*
	 * Default constructor that will use the current 
	 * system time to instantiate the calendar
	 */
	public Calendar() {
		
		this.setMonths();
		
		LocalDate today = LocalDate.now();
		
		this.year = today.getYear();
		this.month = today.getMonthValue() - 1;
		this.day = today.getDayOfMonth();
		this.leapYear = today.isLeapYear();
		
		this.createCalendarMonth();
	}
	
	/*
	 * Constructor that will take three ints 
	 * to set the year month and day of the calendar
	 * 
	 * @Param Initial Year of calendar
	 * @Param Initial Month of calendar
	 * @Param Initial Day of calendar
	 */
	public Calendar(int year, int month, int day) {
		
		this.setMonths();
		
		LocalDate today = LocalDate.now();
		
		this.year = year;
		this.month = month - 1;
		this.day = day;
		this.leapYear = today.isLeapYear();
		
		this.createCalendarMonth();
	}
	
	/*
	 * Gets the name of the first day of the month 
	 * 
	 * @Return DayOfWeek of the first day of the month
	 */
	public DayOfWeek getFirstDayOfMonth() {
		LocalDate firstDayOfMonth = LocalDate.of(year, this.getMonth(), 1);
		
		return firstDayOfMonth.getDayOfWeek();
	}
	
	/*
	 * Gets the name of a specified day in a month
	 * 
	 * @Param Day of the month 
	 * @Return DayOfWeek of a specified day in a month
	 */
	public DayOfWeek getDayOfWeek(int day) {
		LocalDate dayOfWeek = LocalDate.of(year, this.getMonth(), day);
		
		return dayOfWeek.getDayOfWeek();
	}
	
	/*
	 * Returns the the length of the current month
	 * 
	 * @Return int with length of month 
	 */
	public int getMonthLength() {
		
		return this.getMonth().length(leapYear);
	}
	
	/*
	 * Sets the current month of the calendar with
	 * 1 being January and 12 being December
	 * 
	 * @Param int from 1-12 
	 */
	public void setMonth(int monthNumber) {
		
		this.month = monthNumber - 1;
		
		this.createCalendarMonth();
	}
	
	/*
	 * Sets the current month of the calendar to the next month
	 * e.g. if the current month is May nextMonth with set the 
	 * calendar month to June
	 */
	public void nextMonth() {
		
		this.month++;
		
		if(month > 11) {
			this.month = 0;
			this.year++;
			leapYear = LocalDate.of(getYear(), getMonth(), getDay()).isLeapYear();
		}
		
		this.createCalendarMonth();
	}
	
	/*
	 * Sets the current month of the calendar to the previous month
	 * e.g. if the current month is May lastMonth with set the 
	 * calendar month to April
	 */
	public void lastMonth() {
		
		this.month--;
		
		if(month < 0) {
			this.month = 11;
			this.year--;
			leapYear = LocalDate.of(getYear(), getMonth(), getDay()).isLeapYear();
		}
		
		this.createCalendarMonth();
	}
	
	/*
	 * Returns the current month the calendar is set to
	 * 
	 * @Return Month that the calendar is set to 
	 */
	public Month getMonth() {
		
		return months.get(month);
	}
	
	/*
	 * Returns the current year the calendar is set to
	 * 
	 * @Return int Year the calendar is set to
	 */
	public int getYear() {
		
		return this.year;
	}
	
	/*
	 * Sets the current year of the calendar to 
	 * specified year 
	 * 
	 * @Param int year to set calendar to
	 */
	public void setYear(int year) {
		
		this.year = year;
		leapYear = LocalDate.of(getYear(), getMonth(), getDay()).isLeapYear();
	}
	
	/*
	 * Sets the current day the calendar is set to 
	 * 
	 * @Param int day to set calendar to
	 */
	public void setDay(int day) {
		
		this.day = day;
	}
	
	/*
	 * Returns the current day the calendar is set to
	 * 
	 * @Return int day calendar is set to
	 */
	public int getDay() {
		
		return this.day;
	}
	
	/*
	 * Returns the List containing a list DayofWeek 
	 * objects that represent the currently selected month 
	 * 
	 * @Return List<DayOfWeek> that represents current month
	 */
	public List<DayOfWeek> getCalendarMonth() {
		
		return this.calendarMonth;
	}
	
	/*
	 * Loads the months list with all of the months 
	 * of the year. 
	 * 
	 * Called when a calendar object is created
	 */
	private void setMonths() {
		
		this.months = new ArrayList<Month>();
		months.add(Month.JANUARY);
		months.add(Month.FEBRUARY);
		months.add(Month.MARCH);
		months.add(Month.APRIL);
		months.add(Month.MAY);
		months.add(Month.JUNE);
		months.add(Month.JULY);
		months.add(Month.AUGUST);
		months.add(Month.SEPTEMBER);
		months.add(Month.OCTOBER);
		months.add(Month.NOVEMBER);
		months.add(Month.DECEMBER);
	}
	
	/*
	 * Loads the calendarMonth List with DayOfWeek objects 
	 * to represent the currently selected month 
	 * 
	 * Called when the current month is changed
	 */
	private void createCalendarMonth() {
		this.calendarMonth = new ArrayList<DayOfWeek>();
		
		DayOfWeek day = this.getFirstDayOfMonth();
		
		for(int i = 0; i < this.getMonthLength(); i++) {
			calendarMonth.add(day.plus(i));
		}
			
	}
	
	@Override
	public String toString() {
		
		String out = new String("Current year: " + this.getYear() + "\n" + "Current month: " + this.getMonth() + 
				"\n" + this.calendarMonth.toString());
	
		System.out.println("Current year: " + this.getYear());
		System.out.println("Current month: " + this.getMonth());
		
		for(int i = 0; i < this.getMonthLength(); i++) {
			if(this.calendarMonth.get(i).equals(DayOfWeek.SUNDAY))
				System.out.println(calendarMonth.get(i));
			else
				System.out.print(calendarMonth.get(i));
		}
		
		return out;
	}
	
	public static void main(String[] args) {
		
		Calendar cal = new Calendar();
		cal.setMonth(6);
		cal.lastMonth();
		List<DayOfWeek> month = cal.getCalendarMonth();
		
		System.out.println(cal.getYear());
		System.out.println(cal.getMonth());
		System.out.println(month.toString());
	}
}