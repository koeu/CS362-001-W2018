package calendar;
/**
 *  This class provides a basic set of test cases for the
 *  TimeTable class.
 */
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class TimeTableTest {

    @Test
        /*
 	 * Test that getApptRange(), getApptOccurences(), and getNextApptOccurrence 
 	 * methods worked as expected. 
 	 */ 
	public void test01()  throws Throwable  {

        LinkedList<Appt> listAppts = new LinkedList<Appt>();

        String title="1.birthday party ";
        String description="This is my birthday party(Schedule repeats  yearly).";
        Appt appt = new Appt(18, 10, 10, 3, 2018, title, description);

        int[] recurDaysArr=null;;
        appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);

        /*-----------------------------------------------------------------------*/
 
        title="2.Visit ";
        description="Visiting my parents(Schedule repeats monthly).";
        appt = new Appt(20, 20, 25, 1, 2018, title, description);

        recurDaysArr=null;;
        appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/
  
        title="3.CS362 Team Meeting ";
        description="CS362 Team Meeting(Schedule repeats every week).";
        appt = new Appt(16, 30, 26, 1, 2018, title, description);

        recurDaysArr=null;;
        appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, 4);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/
 
        title="4.CS362 class ";
        description="CS362 class time(Every Monday, Wednesday, Friday).";
        appt = new Appt(10, 40, 26, 1, 2018, title, description);

        int[] recurDaysArr1={1,3,5};
        appt.setRecurrence(recurDaysArr1, Appt.RECUR_BY_WEEKLY, 2, 10);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/

        title="5.Test -- Line 55 cover ";
        description=" ";
        appt = new Appt(12, 60, 26, 1, 2018, title, description);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/

        title="6.Appointment after the lastday  -- Line 105,106 cover ";
        description=" ";
        appt = new Appt(12, 50, 26, 1, 2020, title, description);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/

        title="7.Appointment before the startday  -- Line 115 cover ";
        description=" ";
        appt = new Appt(12, 50, 20, 1, 2018, title, description);
        listAppts.add(appt);
 
        /*-----------------------------------------------------------------------*/

        title="8.     -- Line 162, 178 cover ";
        description=" ";
        appt = new Appt(12, 55, 26, 1, 2018, title, description);

        int[] recurDaysArr2={8};
        appt.setRecurrence(recurDaysArr2, Appt.RECUR_BY_WEEKLY, 2, 10);
        listAppts.add(appt);

 
        /*-----------------------------------------------------------------------*/

        title="9.     -- Line 191 cover ";
        description=" ";
        appt = new Appt(13, 10, 27, 1, 2018, title, description);

        int[] recurDaysArr3={};
        appt.setRecurrence(recurDaysArr3, 4, 2, 10);
        listAppts.add(appt);

        /*-----------------------------------------------------------------------*/

        GregorianCalendar startday = new GregorianCalendar(2018,1,25);
        GregorianCalendar lastday  = (GregorianCalendar)startday.clone();

        lastday.add(Calendar.DAY_OF_MONTH,100);
 

        TimeTable timeTable=new TimeTable();
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();

        /* TEST  getApptRange(), getApptOccurences(), getNextApptOccurrence()  */  
        calDays = timeTable.getApptRange(listAppts, startday, lastday);
 
      }

    /*---------------------------------------------------------------------------*/

    @Test
	/*
 	 * Test that deletes an appointment and permutes methods, 
 	 * and test lastday is before the firstday.
 	 */
      public void test02()  throws Throwable  {
  
        TimeTable timeTable=new TimeTable();

        LinkedList<Appt> listAppts = new LinkedList<Appt>();

        /* TEST  201~202 line cover  */ 
        Appt appt = new Appt(24,10,1,1,2018,"schedule-1"," ");
        assertNull(timeTable.deleteAppt(null, appt));
        assertNull(timeTable.deleteAppt(listAppts, null));

        listAppts.add(appt);
        /* TEST  204~205 line cover */ 
        assertNull(timeTable.deleteAppt(listAppts, appt));

        appt = new Appt(14,20,2,2,2018,"schedule-2"," ");
        listAppts.add(appt);

        appt = new Appt(15,30,3,3,2018,"schedule-3"," ");
        listAppts.add(appt);

        appt = new Appt(16,40,4,4,2018,"schedule-4"," ");
        listAppts.add(appt);

        appt = new Appt(17,50,5,5,2018,"schedule-5"," ");
        listAppts.add(appt);

        /*----------  Delete Test ----------------------------------------------------*/
        System.out.println("The Appointments are before deletion!");
        System.out.println(listAppts.toString()+"\n");
    
        int x = listAppts.size();  /* last data delete Error */
        assertNull(timeTable.deleteAppt(listAppts, listAppts.get(x-1)));

        /* first data delete Error */
        assertNull(timeTable.deleteAppt(listAppts, listAppts.get(0)));

        System.out.println("The Appointments are after deletion!");
        System.out.println(listAppts.toString());

        System.out.println("TimeTable.java: Line number 210 must be modified because the first and last data can not be deleted.");
        System.out.println("for(int i=0;i<appts.size();i++)\n");

        /*  second  data delete  */
        assertNotNull(timeTable.deleteAppt(listAppts, listAppts.get(1)));

        /*----------------------------------------------------------------------------*/
        /* TEST  210, 218 line cover  */
        appt = new Appt(16,50,6,6,2018,"schedule-6"," ");
        assertNull(timeTable.deleteAppt(listAppts, appt));


        /* TEST  permute() */
        int[] pv={3,2,1,0};
        assertNotNull(timeTable.permute(listAppts, pv));
        System.out.println("appts.size()="+listAppts.size()+"   pv.length="+pv.length);

        /* TEST permute() Line 229,230 cover  */
        int[] pv1={1,3,2,1,0};
        // System.out.println("appts.size()="+listAppts.size()+"   pv1.length="+pv1.length);
        // assertNull(timeTable.permute(listAppts, pv1)); 
        


        /* TEST  39, 40 line cover  */
        GregorianCalendar startday = new GregorianCalendar(2018,1,25);
        GregorianCalendar lastday = (GregorianCalendar)startday.clone();

        lastday.add(Calendar.DAY_OF_MONTH,-1);
        System.out.println("\nTimeTable  Line 40 Error throw new DateOutOfRangeException...");

        try { 
            assertNull(timeTable.getApptRange(listAppts, startday, lastday)); 
        }
        catch ( Exception e) { 
            System.out.println("Second date specified is not  before the first date specified.\n");
        }
 
      }
}
