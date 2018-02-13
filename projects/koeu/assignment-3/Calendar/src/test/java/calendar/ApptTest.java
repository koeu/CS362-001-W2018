package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  Appt class.
 */
import org.junit.Test;
import java.util.LinkedList;
import java.util.Collections;


import static org.junit.Assert.*;
public class ApptTest {
    /*================================================================================*/
    @Test
      /**
       * Test that the gets/sets methods work as expected.
       */
      public void test01()  throws Throwable  {
        int startHour=21;
        int startMinute=30;
        int startDay=15;
        int startMonth=1;  
        int startYear=2018;
        String title="Birthday Party";
        String description="This is my birthday party.";

        Appt appt = new Appt(startHour,
                             startMinute ,
                             startDay ,
                             startMonth ,
                             startYear ,
                             title,
                             description);
 
        /*  assertions  */
        assertTrue(appt.getValid());
        assertEquals(21, appt.getStartHour());
        assertEquals(30, appt.getStartMinute());
        assertEquals(15, appt.getStartDay());
        assertEquals(01, appt.getStartMonth());
        assertEquals(2018, appt.getStartYear());
        assertEquals("Birthday Party", appt.getTitle());
        assertEquals("This is my birthday party.", appt.getDescription()); 

        /* Test set methods  */ 
        appt.setStartHour(11);
        appt.setStartMinute(12);
        appt.setStartDay(13);
        appt.setStartMonth(2);
        appt.setStartYear(2018);
 
        /* null set   */ 
        appt.setTitle(null);
        appt.setDescription(null);

        appt.setTitle("Birthday Party2"); 
        appt.setDescription("This is my birthday party2."); 

	/* Check sets methods actually worked */
        assertTrue(appt.getValid());
        assertEquals(11, appt.getStartHour());
        assertEquals(12, appt.getStartMinute());
        assertEquals(13, appt.getStartDay());
        assertEquals(02, appt.getStartMonth());
        assertEquals(2018, appt.getStartYear());
        assertEquals("Birthday Party2", appt.getTitle());
        assertEquals("This is my birthday party2.", appt.getDescription()); 
        assertFalse(appt.isRecurring());

        System.out.println(appt.toString());

      }
    /*================================================================================*/
    @Test
      /**
       * Test that the isValid() methods work as expected.
       */
      public void test02()  throws Throwable  {
  
        int startHour=21;
        int startMinute=30;
        int startDay=15;
        int startMonth=1;  
        int startYear=2018;
        String title="Birthday Party";
        String description="This is my birthday party.";

        Appt appt = new Appt(startHour,
                             startMinute ,
                             startDay ,
                             startMonth ,
                             startYear ,
                             title,
                             description);

	/* TEST isValid() - startHour */
        startHour=24;
        appt.setStartHour(startHour);
        assertFalse(appt.getValid());
        System.out.println(appt.toString());  /* test code is "if (!getValid()) { return null; }" */

        startHour=-1;
        appt.setStartHour(startHour);
        assertFalse(appt.getValid());

        startHour=21;
        appt.setStartHour(startHour);
        assertEquals(21, appt.getStartHour());

	/* TEST isValid() - startMinute */
        startMinute = -1;
        appt.setStartMinute(startMinute);
        assertFalse(appt.getValid());

        startMinute = 60;
        appt.setStartMinute(startMinute);
        assertFalse(appt.getValid());

        startMinute = 30;
        appt.setStartMinute(startMinute);

	/* TEST isValid() - startDay */
        startDay = 0;
        appt.setStartDay(startDay);
        assertFalse(appt.getValid());

        startDay = 32;
        appt.setStartDay(startDay);
        assertFalse(appt.getValid());

        startDay = 15;
        appt.setStartDay(startDay);
      
      }
    /*================================================================================*/
    @Test
      /**
       * Test that the recurring events and sort methods work as expected.
       */
 
      public void test03()  throws Throwable  {
  
        LinkedList<Appt> listAppts = new LinkedList<Appt>();

        int startHour=12;
        int startMinute=10;
        int startDay=5;
        int startMonth=1;   
        int startYear=2018;
        String title="CS362 Class";
        String description="CS362 Class time Every Monday, Wednesday, Friday PM 12:10";

        Appt appt = new Appt(startHour,
                             startMinute ,
                             startDay ,
                             startMonth ,
                             startYear ,
                             title,
                             description);

        int[] recurDaysArr={1,3,5};  /*  Every Monday, Wednesday, Friday  */
        appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 10);

        listAppts.add(appt);

	/*  assertions  */
        assertTrue(appt.isRecurring());
        assertEquals(1,  appt.getRecurBy());
        assertEquals(2,  appt.getRecurIncrement());
        assertEquals(10, appt.getRecurNumber());

        int[] recurDaysArr1= appt.getRecurDays();

        /*---------------------------------------------------------*/ 

        startHour=11;
        startMinute=20;
        startDay=5;
        startMonth=1;    
        startYear=2018;
        title="CS362 Class";
        description="CS362 Class Team Meeting Every Tuesday AM 11:20";
        appt = new Appt(startHour,
                             startMinute ,
                             startDay ,
                             startMonth ,
                             startYear ,
                             title,
                             description);
 
        recurDaysArr=null;  /* every week  */
        appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);

        /*-------------    Sort test   ----------------------*/
        System.out.println("The Appointments are not sorted!");
        System.out.println(listAppts.toString());
    
        Collections.sort(listAppts);   /* Sort   */
        System.out.println("The Appointments are sorted!");
        System.out.println(listAppts.toString());

      }

    /*================================================================================*/

    @Test

      /*************************************************************************
       *  Branch Coverage = 93%    Line Coverage=99%  Mutation Coverage=51% 
       *  Final mutation rates =   Line Coverage=99%  Mutation Coverage=99% (66/67)
       *  Add new unit tests to improve the mutation rate 
       *************************************************************************/
 
      public void Add_New_test01()  throws Throwable  {
  

        /*----------  represntationApp() , toString()  Mutation Coverage  ----------*/
        Appt appt = new Appt(12 , 50, 6, 2 ,2018 ,"Test-Title","Test-Description");

        appt.setStartHour(11);
        appt.setStartMinute(12);
        appt.setStartDay(13);
        appt.setStartMonth(2);
        appt.setStartYear(2018);
        appt.setTitle("Test-Title");
        appt.setDescription("Test-Description");

        /*  Line 101  Mutation Coverage */
        assertEquals(0, appt.getRecurDays().length);  /* int[] recurringDays = new int[0]; */
        assertEquals(0, appt.getRecurNumber());       /* RECUR_NUMBER_NEVER = 0            */
        assertEquals(2, appt.getRecurBy());           /* RECUR_BY_MONTHLY = 2              */
        assertEquals(0, appt.getRecurIncrement());    /* 0                                 */


        assertEquals("\t"+"2/13/2018 at 11:12am ,Test-Title, Test-Description"+"\n",appt.toString());
 
	appt.setStartHour(23);   /*  Line 284  Mutation Coverage */
	assertNotNull(appt.toString());
        assertEquals("\t"+"2/13/2018 at 11:12pm ,Test-Title, Test-Description"+"\n",appt.toString());

        /*----------    Line 115  - startHour  Mutation Coverage     ----------*/
        /* if(startHour<0 || startHour>23) =>  (startHour>=0 , startHour<=23)  */
        appt.setStartHour(0);    assertTrue(appt.getValid());
        appt.setStartHour(1);    assertTrue(appt.getValid());
        appt.setStartHour(22);   assertTrue(appt.getValid());
        appt.setStartHour(23);   assertTrue(appt.getValid());

        /*----------  Line 118  - startMinute  Mutation Coverage           ----------*/
        /* if(startMinute<0 || startMinute>59) => (startMinute>=0 , startMinute<=59) */
        appt.setStartMinute(0);    assertTrue(appt.getValid());
        appt.setStartMinute(59);   assertTrue(appt.getValid());

        /*----------  Line 121  - startDay  Mutation Coverage  ----------*/
        /* if(startDay<1 || startDay>NumDaysInMonth) => (startDay>=1 ,startDay<=NumDaysInMonth) */
        appt.setStartYear(2018);          
        appt.setStartMonth(2);    /* February 28th */

        appt.setStartDay(1);   assertTrue(appt.getValid()); 
        appt.setStartDay(28);  assertTrue(appt.getValid()); 

        /*----------  Line 113,124 - startMonth  Mutation Coverage  ----------*/
        /* if(startMonth<1 || startMonth>12) => (startMonth>=1 , startMonth<=12) */

        appt.setStartMonth(1);   assertTrue(appt.getValid());
        appt.setStartMonth(12);  assertTrue(appt.getValid());


        /*----------  Line 221,227 - startMonth  Mutation Coverage  ----------*/
        int[] recurDaysArr={1,3,5};  /* Every Monday, Wednesday, Friday */
        appt.setRecurrence( recurDaysArr, 1, 2, 10);
        assertEquals(1,  appt.getRecurDays()[0]);  /*## Line 221, 227  Mutation Coverage */


        /*----------    compareTo(Appt compareAppt)  Mutation Coverage  ----------*/
        Appt appt1=new Appt(13, 30, 11, 2, 2018, "Title-1", "Description-1");
        Appt appt2=new Appt(14 ,29, 14, 1, 2016, "Title-2", "Description-2");

        int difference = appt1.compareTo(appt2);
        assertEquals(0, difference);

        /*-----------------------------------------------------------------------------------  
        appt1-appt2  => day 13-14=-1  month 30-29=1  
        ex)  return startMinute + startHour*60 + day*60*24 + month*60*24*31 +year*60*24*31*12;
        -------------------------------------------------------------------------------------*/

        /*----------    CalendarUtil.java   Line and Mutation Coverage  ----------*/
        CalendarUtil Util=new CalendarUtil();
        assertEquals(28, Util.NumDaysInMonth(1900,1)); /* Not a leap year */
        assertEquals(29, Util.NumDaysInMonth(2000,1)); /* -- leap year -- */

        assertEquals(28, Util.NumDaysInMonth(2100,1)); /* Not a leap year */
        assertEquals(28, Util.NumDaysInMonth(2200,1)); /* Not a leap year */
        assertEquals(28, Util.NumDaysInMonth(2300,1)); /* Not a leap year */
        assertEquals(28, Util.NumDaysInMonth(2018,1)); /* Not a leap year */ 
        assertEquals(29, Util.NumDaysInMonth(2020,1)); /* -- leap year -- */
   }

    /*================================================================================*/

    @Test(expected = ArrayIndexOutOfBoundsException.class)
      public void Add_New_test02()  throws Throwable  {
        Appt appt = new Appt(12 , 50, 8, 2 ,2018 ,"Test-Title","Test-Description");

        appt.setStartMonth(13);   /* Line 153  Mutation Coverage */
        assertFalse(appt.getValid());
        appt.setStartMonth(-1);
        assertFalse(appt.getValid());
	 
      }
    
}

 
