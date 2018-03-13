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

  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests CalDay Class.                                 */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
  public void radnomtest()  throws Throwable  {

    LinkedList<CalDay> calDays = new LinkedList<CalDay>();


    /*----------------------------------------------------------------------*/     

    try{ 

      for (int iteration = 0; iteration < 1; iteration++) {  
         Random random = new Random();

         if(random.nextInt(10)<1)  {  /* 10% */
           CalDay calDay0 = new CalDay();
           calDays.add(calDay0); 
           String st = calDay0.toString();
           continue;
         }

         int startHour   = random.nextInt(24); 
         int startMinute = random.nextInt(60); 
         int startDay    = random.nextInt(31)+1; 
         int startMonth  = random.nextInt(11)+1; 
         int startYear   = random.nextInt(3000)+1; 
         String title    =  (String) ValuesGenerator.getString(random);
         String description=(String) ValuesGenerator.getString(random);

         Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);
         if(!appt.getValid()) continue;
        
         GregorianCalendar today = new GregorianCalendar(startYear, startMonth-1, startDay);
         CalDay calDay = new CalDay(today);

         assertEquals(startYear,  calDay.getYear());   

  
         /*----------------------------------------------------------------------*/  

         for (int i = 0; i < 100; i++) {

           int newHour   = random.nextInt(24); 
           int newMinute = random.nextInt(60); 
           String newTitle="no.("+i+")  "+(String) ValuesGenerator.getString(random);
           String newDescription=(String) ValuesGenerator.getString(random);

           appt = new Appt(newHour, newMinute, startDay, startMonth, startYear,newTitle,newDescription);
           calDay.addAppt(appt);
         }
         /*----------------------------------------------------------------------*/  
         calDays.add(calDay);
         System.out.println(calDay.toString());

         int sizeappts = calDay.getSizeAppts();   
 
       }  /* for int iteration */
   

    }catch(NullPointerException e){  }
    /*----------------------------------------------------------------------*/   

    System.out.println("calDays.size = "+calDays.size());
    for (int i = 0; i < calDays.size(); i++) {
      if (!calDays.get(i).isValid()) 
         assertNull(calDays.get(i).iterator()); 
      else assertNotNull(calDays.get(i).iterator()); 
    }

    System.out.println("CalDayRandomTest ==> end testing...");
    System.out.println("-------------------------------------------------------");
  }

}
	 
   

