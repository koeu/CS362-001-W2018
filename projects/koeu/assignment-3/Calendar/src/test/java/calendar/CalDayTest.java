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
    /*================================================================================*/
    @Test
	/* 
         * Test that the CalDay(GregorianCalendar cal), addAppt(), toString(), 
         * iterator() and getSizeAppts() methods worked as expected.
         */ 
      public void test01()  throws Throwable {
 
        Calendar rightnow = Calendar.getInstance();  /* get todays date */
        int thisMonth = 2;
        int thisYear  = 2018;
        int thisDay   = 9;
 
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

    /*================================================================================*/
 
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
 
    /*================================================================================*/
 
    @Test
       /**
        * Test that the addAppt() if (appt.getValid()) methods worked as expected.
        */

	/* TEST addAppt(Appt appt) {if (appt.getValid()) } */
      public void test03()  throws Throwable  {
        Calendar rightnow = Calendar.getInstance();  
        int thisMonth = 2;
        int thisYear  = 2018;
        int thisDay   = 9;
 
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
    /*================================================================================*/

   @Test

      /*************************************************************************
       *  Branch Coverage = 83%    Mutation Coverage=38% (10/26)
       *  Final mutation rates =>  Mutation Coverage=88% (23/26)
       *  Add new unit tests to improve the mutation rate 
       *************************************************************************/
 
      public void Add_New_test01()  throws Throwable  {
  


        Calendar rightnow = Calendar.getInstance();  
        int thisMonth = 2;
        int thisYear  = 2018;
        int thisDay   = 9;
 
        GregorianCalendar today = new GregorianCalendar(thisYear,thisMonth,thisDay);
        GregorianCalendar tomorrow = (GregorianCalendar)today.clone();
        tomorrow.add(Calendar.DAY_OF_MONTH,1);

        /*----------  CalDay(GregorianCalendar cal) , toString()  Mutation Coverage  ----------*/
        CalDay calDay1 = new CalDay(today);
        assertEquals(thisYear,  calDay1.getYear());   
        assertEquals(thisMonth, calDay1.getMonth());  
        assertEquals(thisDay,   calDay1.getDay());   
        assertTrue(calDay1.isValid());
        assertEquals("\t --- " + thisMonth + "/" + thisDay + "/" + thisYear + " --- \n"+
               " --- -------- Appointments ------------ --- \n\n", calDay1.toString());


        Appt appt1 = new Appt( 12, 10, thisDay, thisMonth, thisYear,"title-1","description-1");
        Appt appt2 = new Appt( 12, 20, thisDay, thisMonth, thisYear,"title-2","description-2"); 
        Appt appt3 = new Appt( 10, 30, thisDay, thisMonth, thisYear,"title-3","description-3");  

        calDay1.addAppt(appt1);
        assertEquals(12, calDay1.appts.get(0).getStartHour());
 
        assertEquals("\t --- " + thisMonth + "/" + thisDay + "/" + thisYear + " --- \n"+
               " --- -------- Appointments ------------ --- \n"+
               "\t"+"2/9/2018 at 12:10pm ,title-1, description-1\n \n", 
               calDay1.toString());
    
        calDay1.addAppt(appt2);
        assertEquals(12, calDay1.appts.get(0).getStartHour());
 
        assertEquals("\t --- " + thisMonth + "/" + thisDay + "/" + thisYear + " --- \n"+
               " --- -------- Appointments ------------ --- \n"+
               "\t"+"2/9/2018 at 12:10pm ,title-1, description-1\n "+
               "\t"+"2/9/2018 at 12:20pm ,title-2, description-2\n "+"\n",
               calDay1.toString());
 
        /*   Line 78  Mutation Coverage */
        calDay1.addAppt(appt3);
        assertEquals(10, calDay1.appts.get(0).getStartHour());
 
        assertEquals("\t --- " + thisMonth + "/" + thisDay + "/" + thisYear + " --- \n"+
               " --- -------- Appointments ------------ --- \n"+
               "\t"+"2/9/2018 at 10:30am ,title-3, description-3\n "+
               "\t"+"2/9/2018 at 12:10pm ,title-1, description-1\n "+
               "\t"+"2/9/2018 at 12:20pm ,title-2, description-2\n "+"\n",
               calDay1.toString());
 
        assertEquals(3, calDay1.getAppts().size());  /*  Line 74,80  Mutation Coverage */
        assertEquals(3, calDay1.getSizeAppts());     /*  Line 142    Mutation Coverage */
      }

}

   
