package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;

/*===============================================*/
/*  Random Test Generator  for TimeTable Class.  */
/*===============================================*/

public class TimeTableRandomTest {

  private static final long TestTimeout = 20; /* Timeout at x seconds */

  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests TimeTable Class.                */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
  public void radnomtest()  throws Throwable  {
  	 

    long startTime = Calendar.getInstance().getTimeInMillis();
    long elapsed =   Calendar.getInstance().getTimeInMillis() - startTime;
      
    System.out.println("TimeTableRandomTest ==> Start testing("+TestTimeout+" seconds)...");
    for (int iteration = 0; elapsed < TestTimeout*1000; iteration++) {  

      long randomseed =System.currentTimeMillis(); 
      Random random = new Random(randomseed);

      LinkedList<Appt> listAppts = new LinkedList<Appt>();
      /*----------------------------------------------------------------------*/ 
      int appts_num   = random.nextInt(200); 
      for (int k = 0; k < appts_num; k++) {   
				
        int startHour   = random.nextInt(24); 
        int startMinute = random.nextInt(60); 
        int startDay  = random.nextInt(31)+1; 
        int startMonth  = random.nextInt(12)+1; 
        int startYear   = random.nextInt(2)+2018; 
        String title="";
        String description="";
        Appt appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);
                
        String methodName = ApptRandomTest.RandomSelectMethod(random);

        if (methodName.equals("setTitle")){  
          String newTitle=(String) ValuesGenerator.getString(random);
          appt.setTitle(newTitle);   
              
          String newDescription=(String) ValuesGenerator.getString(random);
          appt.setDescription(newDescription);  
        }
        else if (methodName.equals("setRecurrence")){
          int   sizeArray    = random.nextInt(10);
          int[] recurDays    = ValuesGenerator.generateRandomArray(random, sizeArray); 
          int   recur      = random.nextInt(5);
          int   recurIncrement = random.nextInt(10);
          int   recurNumber  = random.nextInt(100);
  
          appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
        }
        listAppts.add(appt);
          
      }  /* for k  */

      /*--------------------------------------------------------------------------------*/ 
      /* Test public LinkedList<CalDay> getApptRange (....)               */
      /*--------------------------------------------------------------------------------*/ 
      int startDay  = random.nextInt(31)+1; 
      int startMonth  = random.nextInt(12)+1; 
      int startYear   = random.nextInt(1)+2018; 
      GregorianCalendar startday = new GregorianCalendar(startYear,startMonth,startDay);
      GregorianCalendar lastday  = (GregorianCalendar)startday.clone();
      lastday.add(Calendar.DAY_OF_MONTH,random.nextInt(1000)-10);

      TimeTable timeTable=new TimeTable();
      LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        

      try { 
        calDays = timeTable.getApptRange(listAppts, startday, lastday);
      }
      catch ( Exception e) { 
         /* System.out.println("Second date specified is not  before the first date specified."); */
      }
    

      /*--------------------------------------------------------------------------------*/ 
      /* Test public LinkedList<Appt> permute(LinkedList<Appt> appts, int[] pv)     */
      /*--------------------------------------------------------------------------------*/ 
      int   pvArray = random.nextInt(2)+listAppts.size();
      int[] pv    = ValuesGenerator.generateRandomArray(random, pvArray); 

      try {
        timeTable.permute(listAppts, pv);
      }
      catch(Exception e){
        /* System.out.println(e.toString()); */
      }      


      /*--------------------------------------------------------------------------------*/ 
      /* Test public LinkedList<Appt> deleteAppt(LinkedList<Appt> appts,Appt appt)    */
      /*--------------------------------------------------------------------------------*/ 
      for (int k = 0; k < listAppts.size(); k++) {   
		
        int delNo = random.nextInt(listAppts.size()); 
        if (random.nextInt(100)<5)     /* 5% */
          timeTable.deleteAppt(null, listAppts.get(delNo));
        else if (random.nextInt(100)<5)  /* 5% */
              timeTable.deleteAppt(listAppts, null);
           else timeTable.deleteAppt(listAppts, listAppts.get(delNo));
      }      

    
      elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);

      if((iteration % 100)==0 && iteration != 0 )
         System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout*1000);

    }  /* for int iteration */
   
    System.out.println("TimeTableRandomTest ==> end testing...");
    System.out.println("-------------------------------------------------------");    
     
  }
}



