package calendar;

import java.util.Calendar;
import org.junit.Test;


import static org.junit.Assert.*;

import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;


/*============================================*/
/*  Random Test Generator  for CalDay Class.  */
/*============================================*/

public class CalDayRandomTest {
  private static final long TestTimeout = 3; /* Timeout at x seconds */

  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests CalDay Class.                 */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
  public void radnomtest()  throws Throwable  {

    LinkedList<CalDay> calDays = new LinkedList<CalDay>();

    long startTime = Calendar.getInstance().getTimeInMillis();
    long elapsed =   Calendar.getInstance().getTimeInMillis() - startTime;
    int sum =0;

    String st = null;  

    System.out.println("CalDayRandomTest ==> Start testing("+TestTimeout+" seconds)...");

    /*----------------------------------------------------------------------*/     
    try{ 
      for (int iteration = 0; elapsed < TestTimeout*1000; iteration++) {
         long randomseed =System.currentTimeMillis(); 

         Random random = new Random(randomseed);

         if((random.nextInt(10)<1) && (sum < 1000)) {  
           sum++; 
           CalDay calDay0 = new CalDay();
           calDays.add(calDay0); 
           st = calDay0.toString();
           continue;
         }

         int startHour   = random.nextInt(26)-1; 
         int startMinute = random.nextInt(62)-1; 
         int startDay  = random.nextInt(33)-1; 
         int startMonth  = random.nextInt(12)+1; 
         int startYear   = random.nextInt(5000)+1; 
         String title    =(String) ValuesGenerator.getString(random);
         String description=(String) ValuesGenerator.getString(random);

         Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);
         if(!appt.getValid()) continue;
        
         GregorianCalendar today = new GregorianCalendar(startYear,startMonth-1,startDay);
         CalDay calDay = new CalDay(today);

         assertEquals(startYear,  calDay.getYear());   
         assertEquals(startMonth, calDay.getMonth()+1);  
         assertEquals(startDay,   calDay.getDay()); 
  
         /*----------------------------------------------------------------------*/  
         int appt_nums  = random.nextInt(500);
         for (int i = 0; i < appt_nums; i++) {

           int newHour   = random.nextInt(26)-1; 
           int newMinute = random.nextInt(62)-1; 
           String newTitle=(String) ValuesGenerator.getString(random);
           String newDescription=(String) ValuesGenerator.getString(random);

           appt.setStartHour(newHour);
           appt.setStartMinute(newMinute);
           appt.setTitle(newTitle); 
           appt.setDescription(newDescription);  
           appt = new Appt(newHour, newMinute, startDay, startMonth, startYear,newTitle,newDescription);
           calDay.addAppt(appt);
         }
         /*----------------------------------------------------------------------*/  
         calDays.add(calDay);
         st = calDay.toString();
         sum++;
 
         int sizeappts = calDay.getSizeAppts();   
 
         elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);

         if((iteration % 5000)==0 && iteration != 0 )
          System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout*1000);

      }  /* for int iteration */
   

    }catch(NullPointerException e){  }
    /*----------------------------------------------------------------------*/   

    System.out.println("calDays.size = "+calDays.size());
    for (int i = 0; i < calDays.size(); i++) {
      if (!calDays.get(i).isValid()) 
         assertNull(calDays.get(i).iterator()); 
      else assertNotNull(calDays.get(i).iterator()); 
      if((i % 5000)==0  )
        System.out.println("elapsed time: "+ i + " of "+calDays.size());
    }

    System.out.println("CalDayRandomTest ==> end testing...");
    System.out.println("-------------------------------------------------------");
  }

}
	 
   
