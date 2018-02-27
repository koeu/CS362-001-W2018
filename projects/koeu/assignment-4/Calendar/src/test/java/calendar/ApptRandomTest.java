package calendar;

import java.util.Calendar;
import java.util.Random;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.Test;
import static org.junit.Assert.*;


/*============================================*/
/*   Random Test Generator  for Appt Class.   */
/*============================================*/

public class ApptRandomTest {

  private static final long TestTimeout = 3; /* Timeout at x seconds */
  private static final int  NUM_TESTS=10;

  /*--------------------------------------------------------------------------------*/ 
  /* Return a randomly selected method to be tests !.                 */
  /*--------------------------------------------------------------------------------*/ 
  public static String RandomSelectMethod(Random random){

    /* The list of the of methods to be tested in the Appt class */
    String[] methodArray = new String[] {"setTitle","setRecurrence"};

    /* get a random number between 0 (inclusive) and  methodArray.length (exclusive) */
    int n = random.nextInt(methodArray.length);
          
    return methodArray[n] ; /* return the method name */ 
  }

  /*--------------------------------------------------------------------------------*/  
  /* Return a randomly selected appointments to recur Weekly,Monthly, or Yearly !.  */
  /*--------------------------------------------------------------------------------*/ 
  public static int RandomSelectRecur(Random random){

    /* The list of the of setting appointments to recur Weekly,Monthly, or Yearly */
    int[] RecurArray = new int[] {Appt.RECUR_BY_WEEKLY,Appt.RECUR_BY_MONTHLY,Appt.RECUR_BY_YEARLY};

    /* get a random number between 0 (inclusive) and  RecurArray.length (exclusive) */
    int n = random.nextInt(RecurArray.length);

    return RecurArray[n] ; /* return the value of the  appointments to recur  */
    }  

  /*--------------------------------------------------------------------------------*/  
  /* Return a randomly selected appointments to recur forever or Never recur  !.  */
  /*--------------------------------------------------------------------------------*/ 
  public static int RandomSelectRecurForEverNever(Random random){

    /* The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER */
    int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};

    /* get a random number between 0 (inclusive) and  RecurArray.length (exclusive) */
    int n = random.nextInt(RecurArray.length);

    return RecurArray[n] ; /* return appointments to recur forever or Never recur  */
  }
 
   
  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests Appt Class.                   */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
    public void radnomtest()  throws Throwable  {
     
      LinkedList<Appt> listAppts = new LinkedList<Appt>();

      long startTime = Calendar.getInstance().getTimeInMillis();
      long elapsed =   Calendar.getInstance().getTimeInMillis() - startTime;

      System.out.println("ApptRandomTest ==> Start testing("+TestTimeout+" seconds)...");
     
      try{ 
       for (int iteration = 0; elapsed < TestTimeout*1000; iteration++) { 
        long randomseed =System.currentTimeMillis(); 
  
        Random random = new Random(randomseed);
        
        int startHour   = ValuesGenerator.getRandomIntBetween(random, 0, 23);
        int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 59);
        int startDay  = ValuesGenerator.getRandomIntBetween(random, 1, 31);
        int startMonth  = ValuesGenerator.getRandomIntBetween(random, 1, 12);
        int startYear   = ValuesGenerator.getRandomIntBetween(random, 1, 5000);
        String title="Birthday Party";
        String description="This is my birthday party.";

        /*Construct a new Appointment object with the initial data   */
        Appt appt  = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);
        Appt appt2 = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);               
        /* Check sets methods actually worked */

        assertEquals(startHour,   appt.getStartHour());
        assertEquals(startMinute, appt.getStartMinute());
        assertEquals(startDay,  appt.getStartDay());
        assertEquals(startMonth,  appt.getStartMonth());
        assertEquals(startYear,   appt.getStartYear());
        assertEquals(title,     appt.getTitle());
        assertEquals(description, appt.getDescription()); 
        assertFalse(appt.isRecurring());
        String st = appt.toString();
        listAppts.add(appt);

        /*----------------------------------------------------------------------*/        
        switch (random.nextInt(5)) {
          case 0: /* Hour Random */
            startHour   = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
            appt2.setStartHour(startHour);

          case 1: /* Minute Random */
            startMinute = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
            appt2.setStartMinute(startMinute);

          case 2: /* Day Random */
             startDay  = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
             appt2.setStartDay(startDay);

          case 3: /* Month Random */
             startMonth  = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
              try {
                 appt2.setStartMonth(startMonth);
              } catch(Exception e){  /* System.out.println(e.toString()); */
                continue;
              }
	      	
           case 4: /* Year Random */
              startYear   = ValuesGenerator.getRandomIntBetween(random, -100000, 100000);
              appt2.setStartYear(startYear);
           }
  
        /*----------------------------------------------------------------------*/ 
        for (int i = 0; i < NUM_TESTS; i++) {
          String methodName = ApptRandomTest.RandomSelectMethod(random);
            if (methodName.equals("setTitle")){  

              String newTitle=(String) ValuesGenerator.getString(random);
              appt.setTitle(newTitle);   
              
              String newDescription=(String) ValuesGenerator.getString(random);
              appt.setDescription(newDescription);  
              
              if (newTitle     != null) assertEquals(newTitle , appt.getTitle() );
              if (newDescription != null) assertEquals(newDescription , appt.getDescription() );               
            }
            else if (methodName.equals("setRecurrence")){

              int  sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
              int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray); 
              int recur=ApptRandomTest.RandomSelectRecur(random);
              int recurIncrement = ValuesGenerator.RandInt(random);
              int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);

              if (sizeArray==0) appt.setRecurrence(null, recur, recurIncrement, recurNumber);
              else appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

              boolean tf = appt.isRecurring();

              assertEquals(recurDays.length, appt.getRecurDays().length);  
              assertEquals(recurNumber,    appt.getRecurNumber());    
              assertEquals(recur,      appt.getRecurBy());       
              assertEquals(recurIncrement,   appt.getRecurIncrement());   
              
            }        
        }
        
         elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
         if((iteration % 50000)==0 && iteration != 0 )
          System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout*1000);

         String st1 = appt.toString();

      } /*for int iteration */
   

      } catch(NullPointerException e){ }
   
      /*----------------------------------------------------------------------*/ 
      Collections.sort(listAppts);   /* Sort   */
      System.out.println("listAppts.size = "+listAppts.size());
      System.out.println("ApptRandomTest ==> end testing...");
      System.out.println("-------------------------------------------------------");

    }  /* void radnomtest() */
  
}
/*================================================================================*/
