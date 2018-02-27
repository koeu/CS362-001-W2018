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

        int[] recurDaysArr={1,3,5};  // Every Monday, Wednesday, Friday 
        appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 10);

        listAppts.add(appt);

	/*  assertions  */
        assertTrue(appt.isRecurring());
        assertEquals(1,  appt.getRecurBy());
        assertEquals(2,  appt.getRecurIncrement());
        assertEquals(10, appt.getRecurNumber());

        int[] recurDaysArr1= appt.getRecurDays();

        //--------------------------------------------------------

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
 
        recurDaysArr=null;  // every week  
        appt.setRecurrence( recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);

        //-------------    Sort test   ----------------------
        System.out.println("The Appointments are not sorted!");
        System.out.println(listAppts.toString());
    
        Collections.sort(listAppts);   // Sort   
        System.out.println("The Appointments are sorted!");
        System.out.println(listAppts.toString());

      }
    
}
  

