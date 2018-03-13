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

  private static final long TestTimeout = 2; /* Timeout at x seconds */
  private static final int  NUM_TESTS=10;

  /*--------------------------------------------------------------------------------*/ 
  /* Return a randomly selected method to be tests !.                               */
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
  /* Return a randomly selected appointments to recur forever or Never recur  !.    */
  /*--------------------------------------------------------------------------------*/ 
  public static int RandomSelectRecurForEverNever(Random random){

    /* The list of the of setting appointments to recur RECUR_NUMBER_FOREVER, or RECUR_NUMBER_NEVER */
    int[] RecurArray = new int[] {Appt.RECUR_NUMBER_FOREVER,Appt.RECUR_NUMBER_NEVER};

    /* get a random number between 0 (inclusive) and  RecurArray.length (exclusive) */
    int n = random.nextInt(RecurArray.length);

    return RecurArray[n] ;  /* return appointments to recur forever or Never recur  */
  }
  
  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests Appt Class.                                   */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
    public void radnomtest()  throws Throwable  {
     
      LinkedList<Appt> listAppts = new LinkedList<Appt>();


      System.out.println("ApptRandomTest ==> Start testing...");
      System.out.println("-------------------------------------------------------");  
     

       for (int iteration = 0; iteration < 50000; iteration++) { 
 
        Random random = new Random();
        
        int startHour   = ValuesGenerator.getRandomIntBetween(random, 0, 23);
        int startMinute = ValuesGenerator.getRandomIntBetween(random, 0, 59);
        
        int startYear   = ValuesGenerator.getRandomIntBetween(random, 1, 3000);
        int startMonth  = ValuesGenerator.getRandomIntBetween(random, 1, 12);

        int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear, startMonth-1);
        int startDay    = ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);

        String title="t";
        String description="d.";

        /*Construct a new Appointment object with the initial data   */
        
        Appt appt  = new Appt(10,30,15,3,2018,"Tltle ","Description ");
        try {
            appt  = new Appt(startHour, startMinute, startDay, startMonth, startYear, title, description);
         } catch(Exception e){ continue; }
        
        
        
        /* Check sets methods actually worked */

        assertEquals(startHour,   appt.getStartHour());
        assertEquals(startMinute, appt.getStartMinute());
        assertEquals(startDay,    appt.getStartDay());
        assertEquals(startMonth,  appt.getStartMonth());
        assertEquals(startYear,   appt.getStartYear());
        assertEquals(title,       appt.getTitle());
        assertEquals(description, appt.getDescription()); 
        assertFalse(appt.isRecurring());
        String st = appt.toString();
        if (startYear==2018)   listAppts.add(appt);
        

        /*----------------------------------------------------------------------*/     
        
        Appt appt2 = new Appt( 10, 30, 15, 3, 2018, "", ""); 
        int newHour   = ValuesGenerator.getRandomIntBetween(random, 0, 23);
        int newMinute = ValuesGenerator.getRandomIntBetween(random, 0, 59);
        
        int newYear   = ValuesGenerator.getRandomIntBetween(random, 1, 3000);
        int newMonth  = ValuesGenerator.getRandomIntBetween(random, 1, 12);
        int newDaysInMonth= CalendarUtil.NumDaysInMonth(newYear,newMonth-1);
        int newDay    = ValuesGenerator.getRandomIntBetween(random, 1, newDaysInMonth);
 
        try {
        appt2 = new Appt( newHour, newMinute,  newDay, newMonth,  newYear, "", ""); 
        } catch(Exception e){ continue; }
        
          
        if ( !appt2.getValid()) continue;
        
        switch (random.nextInt(5)) {
          case 0: /* Hour Random */
              int hh   = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
              appt2.setStartHour(hh);
              if(hh<0 || hh>23) 
                   { if ( appt2.getValid()) System.out.println("Bug Found  Hour  ="+newMonth+"/"+newDay+"/"+newYear + "\t"+hh+":"+newMinute+"\t is True");}
              else { if (!appt2.getValid()) System.out.println("Bug Found  Hour  ="+newMonth+"/"+newDay+"/"+newYear + "\t"+hh+":"+newMinute+"\t is False");}
              break;

          case 1: /* Minute Random */
              int m = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
              appt2.setStartMinute(m);
              if(m<0 || m>59) 
                   { if ( appt2.getValid()) System.out.println("Bug Found  Minute="+newMonth+"/"+newDay+"/"+newYear + "\t"+newHour+":"+m+"\t is True");}
              else { if (!appt2.getValid()) System.out.println("Bug Found  Minute="+newMonth+"/"+newDay+"/"+newYear + "\t"+newHour+":"+m+"\t is False");}
              break;

          case 2: /* Day Random */
              NumDaysInMonth= CalendarUtil.NumDaysInMonth(appt2.getStartYear() , (appt2.getStartMonth())-1);
              int dd  = ValuesGenerator.getRandomIntBetween(random, -1000, 1000);
              appt2.setStartDay(dd);
                if(dd<1 || dd>NumDaysInMonth) 
                     { if ( appt2.getValid()) System.out.println("Bug Found  Day  ="+newMonth+"/"+dd+"/"+newYear + "\t"+newHour+":"+newMinute+"\t is True");}
      	        else { if (!appt2.getValid()) System.out.println("Bug Found  Day  ="+newMonth+"/"+dd+"/"+newYear + "\t"+newHour+":"+newMinute+"\t is False");}
             
              break;

          case 3: /* Month Random */
              int mm  = ValuesGenerator.getRandomIntBetween(random, -1, 13);
              try {
                 appt2.setStartMonth(mm);
              } catch(Exception e){   
                  System.out.println("Bug Found  Month ="+mm+"/"+newDay+"/"+newYear + "\t"+newHour+":"+newMinute+"\t ArrayIndex OutOfBounds Exception:"+mm);
                continue;
              }
              break;
	      	
         case 4: /* Year Random */
              int yy = ValuesGenerator.getRandomIntBetween(random, -100000, 100000);
              appt2.setStartYear(yy);
              break;
           }
  
        /*----------------------------------------------------------------------*/ 
        for (int i = 0; i < NUM_TESTS; i++) {
          String methodName = ApptRandomTest.RandomSelectMethod(random);
            if (methodName.equals("setTitle")){  

              String newTitle= (String) ValuesGenerator.getString(random);
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
        } /* for int i */
        
      } /*for int iteration */
   
      
      
      /*----------    CalendarUtil.java    ----------*/
      CalendarUtil Util=new CalendarUtil();
      assertEquals(28, Util.NumDaysInMonth(1900,1)); /* Not a leap year */
      assertEquals(29, Util.NumDaysInMonth(2000,1)); /* -- leap year -- */
      assertEquals(28, Util.NumDaysInMonth(2100,1)); /* Not a leap year */
      assertEquals(28, Util.NumDaysInMonth(2200,1)); /* Not a leap year */
      assertEquals(28, Util.NumDaysInMonth(2300,1)); /* Not a leap year */
      assertEquals(29, Util.NumDaysInMonth(2400,1)); /* -- leap year -- */
      assertEquals(28, Util.NumDaysInMonth(2018,1)); /* Not a leap year */
      
      assertEquals(29, Util.NumDaysInMonth(2012,1)); /* -- leap year -- */
      assertEquals(29, Util.NumDaysInMonth(2016,1)); /* -- leap year -- */
      assertEquals(29, Util.NumDaysInMonth(2020,1)); /* -- leap year -- */
      assertEquals(29, Util.NumDaysInMonth(2024,1)); /* -- leap year -- */
   
      /*---------------Sort test-----------------------------------------*/ 
      //System.out.println("The Appointments are not sorted!");
      //System.out.println(listAppts.toString());

      Collections.sort(listAppts);   // Sort
      System.out.println("-------------------------------------------------------");  
      System.out.println("The Appointments are sorted!");
      System.out.println(listAppts.toString());
      

      System.out.println("ApptRandomTest ==> end testing...");
      System.out.println("-------------------------------------------------------");

    }  /* void radnomtest() */
  

  }
      
   
/*================================================================================*/

