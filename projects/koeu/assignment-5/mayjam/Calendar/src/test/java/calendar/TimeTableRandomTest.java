package calendar;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.Random;

import org.junit.Test;


/*===============================================*/
/*  Random Test Generator  for TimeTable Class.  */
/*===============================================*/

public class TimeTableRandomTest {

  /*--------------------------------------------------------------------------------*/  
  /* Generate Random Tests that tests TimeTable Class.                              */
  /*--------------------------------------------------------------------------------*/ 
  @Test 
  public void radnomtest()  throws Throwable  {
  	 
    for (int iteration = 0; iteration < 1; iteration++) {  

      Random random = new Random();

      LinkedList<Appt> listAppts = new LinkedList<Appt>();
      /*----------------------------------------------------------------------*/ 

      for (int k = 0; k < 200; k++) {   
				
        int startHour   = random.nextInt(24); 
        int startMinute = random.nextInt(60); 
        int startYear   = 2018; 
        int startMonth  = random.nextInt(12)+1; 
        int NumDaysInMonth= CalendarUtil.NumDaysInMonth(startYear,startMonth-1);
        int startDay    = ValuesGenerator.getRandomIntBetween(random, 1, NumDaysInMonth);

        String title="";
        String description="";
        
        Appt appt  = new Appt(10,30,15,3,2018,"Tltle ","Description ");
        try {
            appt = new Appt(startHour,startMinute,startDay,startMonth,startYear,title,description);
         } catch(Exception e){  continue;  }
               
        String methodName = ApptRandomTest.RandomSelectMethod(random);

        if (methodName.equals("setTitle")){  
            appt.setTitle("");   
            appt.setDescription("");  
        }
        else if (methodName.equals("setRecurrence")){
          int   recur      = random.nextInt(5);
          int   sizeArray  = random.nextInt(10);
          if       ((recur==1)&&(random.nextInt(10)<3)) sizeArray = 0;    /* 30%  */
          else if  ((recur==1)&&(random.nextInt(10)<3)) sizeArray = 1;    /* 30%  */
          
          int[] recurDays    = ValuesGenerator.generateRandomArray(random, sizeArray); 

          int   recurIncrement = random.nextInt(10);
          int   recurNumber    = random.nextInt(100);
          
          String week[] = {"Sun ", "Mon ", "Tue ", "Wed ", "Thu ", "Fri ", "Sat "};
          
          switch (recur) {
            case Appt.RECUR_BY_WEEKLY:
            	appt.setTitle("RECUR_BY_WEEKLY "); 
            	String desc = "";
            	for (int i=0; i<recurDays.length; i++) {
                    int w = recurDays[i];
            	    if (w>0 && w<8) desc += week[w-1]; 
            	}
            	appt.setDescription(desc); 
                break;
                
            case Appt.RECUR_BY_MONTHLY:
            	appt.setTitle("RECUR_BY_MONTHLY "); 
                break;
                
            case Appt.RECUR_BY_YEARLY:
            	appt.setTitle("RECUR_BY_YEARLY "); 
                break;
          } // case
  
           appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);
        }
        if (appt.getValid())  listAppts.add(appt);

          
      }  /* for k  */
      
      Collections.sort(listAppts);   // Sort
      System.out.println("listAppts  No."+(iteration+1));
      System.out.println(listAppts.toString());
      System.out.println("-------------------------------------------------------");  
      
      /*--------------------------------------------------------------------------------*/ 
      /* Test public LinkedList<CalDay> getApptRange (....)               */
      /*--------------------------------------------------------------------------------*/ 
      int startDay    = random.nextInt(31)+1; 
      int startMonth  = random.nextInt(12)+1; 
      int startYear   = 2018; 
      GregorianCalendar startday = new GregorianCalendar(startYear, startMonth-1, startDay);
      GregorianCalendar lastday  = (GregorianCalendar)startday.clone();
      lastday.add(Calendar.DAY_OF_MONTH,random.nextInt(300)+100);

      System.out.println("Start Day = "+startYear+"/"+startMonth+"/"+ startDay);  
      System.out.println("End   Day = "+lastday.get(Calendar.YEAR)+"/"+(lastday.get(Calendar.MONTH)+1)+"/"+ lastday.get(Calendar.DAY_OF_MONTH)); 
      
      TimeTable timeTable=new TimeTable();
      LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        

      try { 
        calDays = timeTable.getApptRange(listAppts, startday, lastday);
      }
      catch ( Exception e) { 
         /* System.out.println("Second date specified is not  before the first date specified."); */
      }
    
      System.out.println("calDays  No."+iteration);
      System.out.println(calDays.toString());
      System.out.println("-------------------------------------------------------");  

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
      int size = listAppts.size();
      for (int k = 0; k < size; k++) {   
		
        int delNo = random.nextInt(listAppts.size()); 
        int old_size =  listAppts.size();
        timeTable.deleteAppt(listAppts, listAppts.get(delNo));
        int new_size =  listAppts.size();
        
        if (old_size != (new_size+1)) {
        	 System.out.println("Bug Found  deleteAppt()   listAppts.size="+old_size+" delete No=("+delNo+") "); 
        }
        old_size =  listAppts.size();
      }      

    }  /* for int iteration */
   
    System.out.println("TimeTableRandomTest ==> end testing...");
    System.out.println("-------------------------------------------------------");    
     
  }
}



