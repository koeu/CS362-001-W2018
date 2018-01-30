package calendar;
/* 
 *  This class provides a basic set of test cases for the
 *  CalDay class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.*;

import org.junit.Test;

import static org.junit.Assert.*;

public class CalDayTest {

    @Test
	/* 
 	 * Test that the CalDay(GregorianCalendar cal), addAppt(), toString(), 
 	 * iterator() and getSizeAppts() methods worked as expected.
 	 */ 
      public void test01()  throws Throwable {
 
        Calendar rightnow = Calendar.getInstance();  /* get todays date */
        int thisMonth = rightnow.get(Calendar.MONTH)+1;
        int thisYear  = rightnow.get(Calendar.YEAR);
        int thisDay   = rightnow.get(Calendar.DAY_OF_MONTH);
 
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

        int startHour   = 20;
        int startMinute = 10;
        int startDay    = thisDay;
        int startMonth  = thisMonth;
        int startYear   = thisYear;

        String title="Birthday Party ";
        String description="This is my birthday party 20:10.";

        Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);

        LinkedList<Appt> listAppts = new LinkedList<Appt>();
        listAppts.add(appt);


        /* TEST CalDay(GregorianCalendar cal)            */
        /* TEST setAppts(), setDay() setMonth()setYear() */ 
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays.add(new CalDay(today));  

        /* TEST addAppt() , getAppts() */
        CalDay calDayOfAppt = (CalDay) calDays.get(0); 
        calDayOfAppt.addAppt(appt);

        /*---------------------------------------------------------*/ 
        startHour   = 10;
        startMinute = 20;
        title="Team meeting";
        description="Team meeting  AM 10:20";

        appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);

        listAppts.add(appt);

        calDayOfAppt = (CalDay) calDays.get(0); 
        calDayOfAppt.addAppt(appt);

        /*---------------------------------------------------------*/

        /* TEST addAppt()  For loop  test , isValid() */
        startHour   = 14;
        startMinute = 30;

        title="CS362 class";
        description="CS362 Class time 14:30.";

        appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);

        listAppts.add(appt);

        calDayOfAppt = (CalDay) calDays.get(0); 
        calDayOfAppt.addAppt(appt);
 
        /* TEST  isValid(), getDay(),getMonth(), getYear(),toString() */
        for (int i = 0; i < calDays.size(); i++)
          System.out.println(calDays.get(i).toString());

        /* TEST  Iterator<?> iterator()  if (isValid()) */
        for (int i = 0; i < calDays.size(); i++)
          assertNotNull(calDays.get(i).iterator()); 

        /* TEST getSizeAppts()  */
        calDayOfAppt = (CalDay) calDays.get(0);
        int a = calDayOfAppt.getSizeAppts();

      }

    /*---------------------------------------------------------------------*/
 
    @Test
      /**
       * Test that the CalDay() and Iterator<?> if else methods worked as expected.
       */
	/* TEST CalDay(), Iterator<?> iterator()  if else */ 
      public void test02()  throws Throwable  {

        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays.add(new CalDay());    /* Constructs an invalid CalDay object */

        for (int i = 0; i < calDays.size(); i++)
           System.out.println(calDays.get(i).toString());

        for (int i = 0; i < calDays.size(); i++)
           assertNull(calDays.get(i).iterator()); 
      }
 
    /*---------------------------------------------------------------------*/

    @Test
       /**
	* Test that the addAppt() if (appt.getValid()) methods worked as expected.
        */

	/* TEST addAppt(Appt appt) {if (appt.getValid()) } */
      public void test03()  throws Throwable  {
        Calendar rightnow = Calendar.getInstance();  
        int thisMonth = rightnow.get(Calendar.MONTH)+1;
        int thisYear  = rightnow.get(Calendar.YEAR);
        int thisDay   = rightnow.get(Calendar.DAY_OF_MONTH);
 
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);

        int startHour   = 25;  /*  Time Fail */
        int startMinute = 10;
        int startDay    = thisDay;
        int startMonth  = thisMonth;
        int startYear   = thisYear;

        String title="TEST appt.getValid()  ";  
        String description="";

        Appt appt = new Appt(startHour,
                  startMinute ,
                  startDay ,
                  startMonth ,
                  startYear ,
                  title,
                 description);

        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays.add(new CalDay(today));  

        CalDay calDayOfAppt = (CalDay) calDays.get(0); 
        calDayOfAppt.addAppt(appt);

        assertFalse(appt.getValid());  /* TEST */
      }
}



