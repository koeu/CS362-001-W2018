
package finalprojectB;

import junit.framework.TestCase;

public class UrlValidatorTest extends TestCase {

    String  T = "true";
    String  F = "false";
    
    int     k1,k2,k3,k4,k5 = 0;  
    int    test_count = 0;
    int    bug_count = 0;
    int    err1 , err2, err3 = 0; 
    
    int    v1 , v2, v3 , v4 = 0; 

    //======================================================================
 
    void  bug_print(String url, boolean prediction, boolean result, String msg ,int partition) {
        String[] Part = { "isValid()         ",  "isValidScheme()   ", "isValidAuthority()", "Port Number       " ,"isValidPath()     " ,"FisValidQuery()   " };             
        System.out.print(Part[partition]+"   prediction="+prediction+"  result=");
        if (msg == null) System.out.print(result);
        else System.out.print(msg);
        System.out.println("\t  "+url);
    }
       
    //======================================================================

    boolean  isValid( int partition, UrlValidator urlVal, boolean prediction, String url ) {
        boolean result = false;
        test_count++;
        try {
            switch (partition) {
                case 0: result=urlVal.isValid(url);          break; /* URL Full name */
                case 1: result=urlVal.isValidScheme(url);    break; /* Scheme        */
                case 2: result=urlVal.isValidAuthority(url); break; /* Authority     */
                case 3: result=urlVal.isValid(url);          break; /* Port Number   */
                case 4: result=urlVal.isValidPath(url);      break; /* Path          */
                case 5: result=urlVal.isValidQuery(url);     break; /* Query         */
            }
               
        } catch(ExceptionInInitializerError  e){  
          //    System.out.print("Error       => ");
          //   bug_print(url, prediction, result, e.toString() , partition);
            err1++;
            return result;
               
        } catch(NoClassDefFoundError e){  
         //    System.out.print("Error       => ");
          //  bug_print(url, prediction, result, e.toString() , partition );
            err2++;
            return result;   
               
        }  catch(NullPointerException e){  
          //    System.out.print("Error       => ");
          //   bug_print(url, prediction, result, e.toString() , partition );
            err3++;
            return result;   
        } //try
           
        if (result !=prediction) {
             System.out.print("Mismatch => ");
            bug_print(url, prediction, result, null , partition );
            bug_count++;
            
             if (result) v3++;   // false=>true
             else v4++;          // true=>false
             
        } 
        else{
         //    System.out.print("Success   => ");
         //    bug_print(url, prediction, result, null , partition );
             
             if (result) v1++;   // true=>true
             else v2++;          // false=>false
        }
      
    return result;
    }

    //======================================================================

    public void testIsValid() {  
    
        test_count = 0; 
        bug_count = 0;    // IsValid Mismatch Count
        err1 = 0;  err2 = 0;  err3 = 0;
        v1 = 0;  v2 = 0;  v3 = 0;  v4 = 0;
        
        UrlValidator urlVal = new UrlValidator();   
        // Degug Bug #1
        isValid( 1 , urlVal, true, "http");
 
        // Degug Bug #2
        isValid( 1 , urlVal, true, "http://a.com:80");
        
        
        Manual_Test();
        
        FirstPartition_Test();
        SecondPartition_Test();
        ThirdPartition_Test();
        FourthPartition_Test();
        FifthPartition_Test();
        
        Programming_Based_Test();

        Url_LoopTest();   

        System.out.println("------------------------------------------------------------------------------------------------------------------");      
        System.out.println("  Test Count = "+test_count+"    isValid() Success="+ (test_count-bug_count-err1-err2-err3)+
                                    "     isValid() Mismatch="+bug_count+"     Errors="+(err1+err2+err3));
        System.out.println("  ExceptionInInitializerError="+err1+"   NoClassDefFoundError="+err2+"   NullPointerExceptionError="+err3);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("  isValid() Test     True=>True : "+v1+"   False=>False : "+v2+"   False=>True : "+v3+"   True=>False : "+v4);
        System.out.println("------------------------------------------------------------------------------------------------------------------");
        System.out.println("  UrlValidatorTest Complete......");  
        System.out.println("------------------------------------------------------------------------------------------------------------------");             
    }

    //======================================================================
    public void Manual_Test() {
        //You can use this function to implement your manual testing       
 
        System.out.println("****************************************");
        System.out.println("   Test 1.      Manual Testing ");
        System.out.println("****************************************");

        UrlValidator urlVal = new UrlValidator();
        isValid(0 , urlVal, true , "http://www.google.com"); 
        isValid(0 , urlVal, true , "http://www.abc.com"); 
        isValid(0 , urlVal, true , "http://abc.com"); 
        isValid(0 , urlVal, true , "http://bbb1.com/"); 
        isValid(0 , urlVal, true , "http://abc1.org"); 
        isValid(0 , urlVal, true , "http://abc.net"); 
        isValid(0 , urlVal, true , "http://a.dhl"); 
        isValid(0 , urlVal, true , "HttP://abc.com"); 
        isValid(0 , urlVal, true , "hTtP://abc.com");   
        
        isValid(0 , urlVal, false , "http://aaa");  
        isValid(0 , urlVal, false , "http://abc1."); 
        isValid(0 , urlVal, false , "http://abc1.a"); 
        isValid(0 , urlVal, false , "http://abc1.ab"); 
        isValid(0 , urlVal, false , "http://aaa.sdfg");   

        isValid(0 , urlVal, true , "http://256.256.256.900.com"); 
        isValid(0 , urlVal, true , "http://s.abc.com/rc/#%!&@"); 
        isValid(0 , urlVal, true , "http://www.a.com:80/p"); 
        isValid(0 , urlVal, true , "http://www.a.com:8080/p"); 
        isValid(0 , urlVal, true , "http://www.a.com:/p"); 
        isValid(0 , urlVal, true , "http://b.com/serach?a=Up+"); 
        
        isValid(0 , urlVal, false, "http://b.com/serach?a=Up "); 
        isValid(0 , urlVal, false, "http:/www.google.com"); 
        isValid(0 , urlVal, false, "http:www.abc.com"); 
        isValid(0 , urlVal, false, "http//abc.com"); 
        isValid(0 , urlVal, false, "http://aaa.com../"); 
        isValid(0 , urlVal, false, "http://aaa.invalid"); 
       
        //--------------------------------------------------------------------------------
        // SCHEMES Test
        urlVal = new UrlValidator();  // DEFAULT_SCHEMES =  http, https, ftp 
        isValid(0 , urlVal, true ,"http://www.abc.com/"); 
        isValid(0 , urlVal, true ,"https://www.abc.com/"); 
        isValid(0 , urlVal, true ,"ftp://www.abc.com/"); 
        
        isValid(0 , urlVal, false ,"utp://www.abc.com/"); 
        isValid(0 , urlVal, false ,"stp://www.abc.com/"); 
        isValid(0 , urlVal, false ,"file://www.abc.com/"); 
        isValid(0 , urlVal, false ,"g3ht://www.abc.com"); 
        isValid(0 , urlVal, false ,"3ht2://www.abc.com");  

        urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

        isValid(0 , urlVal, true ,"utp://www.abc.com/"); 
        isValid(0 , urlVal, true ,"stp://www.abc.com/"); 
        isValid(0 , urlVal, true ,"file://www.abc.com/"); 
        isValid(0 , urlVal, true ,"g3ht://www.abc.com"); 
        isValid(0 , urlVal, false,"3ht2://www.abc.com");       
       
        
        String[] schemes = {"HTTPs", "file", "h2o" }; 
        urlVal = new UrlValidator(schemes);
        isValid(0 , urlVal, true ,"https://www.abc.com/"); 
        isValid(0 , urlVal, true ,"Https://www.abc.com/"); 
        isValid(0 , urlVal, true ,"hTTps://www.abc.com/"); 
        isValid(0 , urlVal, true ,"file://www.abc.com/"); 
        isValid(0 , urlVal, true ,"h2o://www.abc.com/"); 
        
        isValid(0 , urlVal, false ,"http://www.abc.com/"); 
        isValid(0 , urlVal, false ,"ftp://www.abc.com/"); 
        
        
        
        urlVal = new UrlValidator();   
        isValid(0 , urlVal, true, "http://192.168.20.1");
        isValid(0 , urlVal, true, "http://255.255.255.255");
        isValid(0 , urlVal, true, "http://256.256.256.256.com");
        isValid(0 , urlVal, true, "http://256.com");
        
        isValid(0 , urlVal, false, "http://256.256.256.256");       
        isValid(0 , urlVal, false, "http://.192.168.20.1");
        isValid(0 , urlVal, false, "http://255.0.0.0.");
        isValid(0 , urlVal, false, "http://0.-1.0.0");
        isValid(0 , urlVal, false, "http://1.1");
        isValid(0 , urlVal, false, "http://1.1.1");
        isValid(0 , urlVal, false, "http://1.1.1.1.");
        isValid(0 , urlVal, false, "http://.1.1.1.1");
        
        //--------------------------------------------------------------------------------
           
        isValid(0 , urlVal, false , "http://abc.com/path//file//.html"); 
        isValid(0 , urlVal, false , "http://abc.com/path//.//.//html"); 
        isValid(0 , urlVal, false , "http://abc.com/path//.///.//html"); 
        
        urlVal = new UrlValidator(UrlValidator.ALLOW_2_SLASHES); 
        isValid(0 , urlVal, true , "http://abc.com/path//file//.html"); 
        isValid(0 , urlVal, true , "http://abc.com/path//.//.//html"); 
        isValid(0 , urlVal, true , "http://abc.com/path//.///.//html"); 

        //--------------------------------------------------------------------------------

        urlVal = new UrlValidator();
        isValid(0 , urlVal, false, "file://some.file"); 
        isValid(0 , urlVal, false, "file://some.file"); 
        isValid(0 , urlVal, false, "file://etc/hosts"); 
        
        urlVal = new UrlValidator(new String[] {"http","https","file"}, UrlValidator.ALLOW_LOCAL_URLS);
        isValid(0 , urlVal, true,  "http://aaa.org/index.html"); 
        isValid(0 , urlVal, true,  "file:///C:/some.file"); 
        isValid(0 , urlVal, true,  "file:///etc/hosts"); 
        isValid(0 , urlVal, true,  "file://localhost/etc/hosts"); 
        isValid(0 , urlVal, true,  "file://localhost/c:/some.file"); 
        isValid(0 , urlVal, true,  "file://localhost/etc/hosts"); 
        isValid(0 , urlVal, true,  "file://localhost/c:/some.file"); 
        isValid(0 , urlVal, false, "file://C:/some.file");    
        isValid(0 , urlVal, false, "file://C:\\some.file");    
          
        //======================================================================
 
        urlVal = new UrlValidator();
        isValid(0 , urlVal, true,  "http://a.com:/"); 
        isValid(0 , urlVal, true,  "http://a.com:0/"); 
        isValid(0 , urlVal, true,  "http://a.com:65535/"); 
        isValid(0 , urlVal, false, "http://a.com:-10/");        
        isValid(0 , urlVal, false, "http://a.com:65536/"); 
        isValid(0 , urlVal, false, "http://a.com:100000/"); 
        isValid(0 , urlVal, false, "http://a.com:a"); 

    }

    //======================================================================
    public void  FirstPartition_Test() {
        System.out.println("\n****************************************");
        System.out.println("   Test 2-1.   FirstPartition Scheme Testing ");
        System.out.println("****************************************\n");


        System.out.println("\n--------------------------------------------------------------------------------");        
        System.out.println("#####  BUG #1 Found   Default SCHEMES  Error             #####");  
        System.out.println("--------------------------------------------------------------------------------"); 

        UrlValidator urlVal = new UrlValidator();   // DEFAULT_SCHEMES =  http, https, ftp  
        assertFalse(urlVal.isValidScheme("http"));  // Bug found
        assertFalse(urlVal.isValidScheme("https")); // Bug found
        assertFalse(urlVal.isValidScheme("ftp"));   // Bug found
        
        urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES); 
        assertTrue(urlVal.isValidScheme("http"));    
        assertTrue(urlVal.isValidScheme("https"));   
        assertTrue(urlVal.isValidScheme("ftp"));        
        assertTrue(urlVal.isValidScheme("file"));    
        assertTrue(urlVal.isValidScheme("h2o"));   
        assertTrue(urlVal.isValidScheme("FTP"));
        
        //--------------------------------------------------------------------------------

        urlVal = new UrlValidator();   // DEFAULT_SCHEMES =  http, https, ftp 
        isValid( 1 , urlVal, true, "http");
        isValid( 1 , urlVal, true, "https");
        isValid( 1 , urlVal, true, "ftp");
        isValid( 1 , urlVal, true, "hTTp");
        isValid( 1 , urlVal, true, "Ftp");

        urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES); 
        isValid( 1 , urlVal, true, "http");
        isValid( 1 , urlVal, true, "https");
        isValid( 1 , urlVal, true, "ftp");
        isValid( 1 , urlVal, true, "hTTp");
        isValid( 1 , urlVal, true, "Ftp");
        isValid( 1 , urlVal, true, "h2o");
        isValid( 1 , urlVal, true, "qqq2");
        
        
        urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES); 
        String[][] test_Scheme1 =  {
                {"http"   , T}, 
                {"https"  , T}, 
                {"ftp"    , T}, 
                {"hftp"   , T}, 
                {"htts"   , T},         
                {"h2o"    , T}, 
                {"Http"   , T},   
                {"HttPS"  , T}, 
                {"FTP"    , T}, 
                {"Hftp"   , T}, 
                {"hTTs"   , T},         
                {"H2O"    , T}, 
                {"2ho"    , F},          
                {"333"    , F} 
         };

        for(int k=0; k<test_Scheme.length; k++){
           isValid( 0 , urlVal, ( test_Scheme[k][1] == T) , test_Scheme[k][0]+"abc.com");
        }  
        
        for(int k=0; k<test_Scheme1.length; k++){
            isValid( 1 , urlVal, ( test_Scheme1[k][1] == T) , test_Scheme1[k][0]);
         }  
        
        //--------------------------------------------------------------------------------     
  
        System.out.println("    DEFAULT_SCHEMES =  http, https, ftp   Option is Set.");
        urlVal = new UrlValidator();  
        String[][] test_Scheme2 =  {
               { "http"     , T},
               { "https"    , T}, 
               { "ftp"      , T},
               { "hTTp"     , T},
               { "HttPs"    , T}, 
               { "fTp"      , T},
               { "file"     , F}, 
               { "httpd"    , F},
               { "localhost", F},
               { "open"     , F},              
               { "telnet"   , F}   
        } ;
               
        for(int k=0; k<test_Scheme2.length; k++){
           isValid( 1 , urlVal, ( test_Scheme2[k][1] == T), test_Scheme2[k][0]);
        }
        
        //--------------------------------------------------------------------------------     
        System.out.println("    SCHEMES = http , file,  localhost");
        String[] schemes = {"http", "file", "localhost" }; 
        urlVal = new UrlValidator(schemes);

        //--------------------------------------------------------------------------------
        String[][] test_Scheme3 =  {
               { "http"      , T},
               { "file"      , T}, 
               { "localhost" , T},
               { "Http"      , T},
               { "filE"      , T}, 
               { "LocalHost" , T},
 
               { "https"     , F},
               { "ftp"       , F},
               { "httpd"     , F},
               { "h2o"       , F},
        };
           
        for(int k=0; k<test_Scheme3.length; k++){
            isValid( 1 , urlVal, ( test_Scheme3[k][1] == T), test_Scheme3[k][0]);
        }
       
 
    }
   
    //======================================================================
    public void  SecondPartition_Test(){
        //You can use this function to implement your Second Partition testing       
        System.out.println("\n****************************************");
        System.out.println("   Test 2-2.   SecondPartition Authority Testing ");
        System.out.println("****************************************");

        UrlValidator urlVal = new UrlValidator();

          
        for(int k=0; k<test_Authority.length; k++){
           isValid( 2 , urlVal, ( test_Authority[k][1] == T), test_Authority[k][0]);
        }
        
        isValid( 2 , urlVal, true, "www.google.com");
        isValid( 2 , urlVal, true, "aaa.com");
        isValid( 2 , urlVal, true, "www.aaa.com");
        isValid( 2 , urlVal, true, "oregonstate.edu");
        isValid( 2 , urlVal, true, "256.com");
        isValid( 2 , urlVal, true, "aa.com.");
        isValid( 2 , urlVal, true, "aaa.org");
        isValid( 2 , urlVal, true, "aaa.net");
        isValid( 2 , urlVal, true, "aaa.gov");
        isValid( 2 , urlVal, true, "aaa.us");
        isValid( 2 , urlVal, true, "aaa.uk");
        isValid( 2 , urlVal, true, "aaa.cn");
        isValid( 2 , urlVal, true, "aaa.kr");
        
        
        isValid( 2 , urlVal, false, "www.google.com/");
        isValid( 2 , urlVal, false, "www.aaa.com-");
        isValid( 2 , urlVal, false, ".oregonstate.edu");
        isValid( 2 , urlVal, false, "aaa.a");
        isValid( 2 , urlVal, false, "aaa.a#");
        isValid( 2 , urlVal, false, "aaa.aaaa");
        isValid( 2 , urlVal, false, "aaa.a2a");

        isValid( 2 , urlVal, true, "0.0.0.0");
        isValid( 2 , urlVal, true, "255.0.0.0");
        isValid( 2 , urlVal, true, "192.168.20.1");
        isValid( 2 , urlVal, true, "255.255.255.255");
        
        isValid( 2 , urlVal, false, ".192.168.20.1");
        isValid( 2 , urlVal, false, "255.0.0.0.");
        isValid( 2 , urlVal, false,  "0.-1.0.0");
        isValid( 2 , urlVal, false, "1.1");
        isValid( 2 , urlVal, false, "1.1.1");
        isValid( 2 , urlVal, false, "1.1.1.1.");
        isValid( 2 , urlVal, false, ".1.1.1.1");
        isValid( 2 , urlVal, false, "256.1.1.1");
    }
   
    //======================================================================
    public void  ThirdPartition_Test(){
        //You can use this function to implement your Third Partition testing       
        System.out.println("\n****************************************");
        System.out.println("   Test 2-3.   ThirdPartition Port Testing ");
        System.out.println("****************************************");

        System.out.println("\n--------------------------------------------------------------------------------");        
        System.out.println("#####  BUG #2 Found   Port Number Error                       #####");  
        System.out.println("--------------------------------------------------------------------------------"); 
  
        UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES); 

        assertFalse(urlVal.isValid("http://a.com:0"));         // Bug found
        assertFalse(urlVal.isValid("http://a.com:1/"));        // Bug found 
        assertFalse(urlVal.isValid("http://a.com:2"));         // Bug found
        assertFalse(urlVal.isValid("http://a.com:80/"));       // Bug found   
        assertFalse(urlVal.isValid("http://a.com:8080/"));     // Bug found
        assertFalse(urlVal.isValid("http://a.com:21"));        // Bug found
        assertFalse(urlVal.isValid("http://1.1.1.1:0"));       // Bug found
        assertFalse(urlVal.isValid("http://1.1.1.1:1/"));      // Bug found 
        assertFalse(urlVal.isValid( "http://1.1.1.1:2"));      // Bug found 
        assertFalse(urlVal.isValid("http://1.1.1.1:80/"));     // Bug found        
        assertFalse(urlVal.isValid("http://1.1.1.1:8080/"));   // Bug found 
        assertFalse(urlVal.isValid("http://1.1.1.1:21"));      // Bug found 
        System.out.println("--------------------------------------------------------------------------------");       
        

        urlVal = new UrlValidator();

        isValid( 0 , urlVal, true, "http://a.com:80");         
        isValid( 0 , urlVal, true, "http://a.com:0");     
        isValid( 0 , urlVal, true, "http://a.com:8080");  
        isValid( 0 , urlVal, true, "http://a.com:65535"); 

        isValid( 0 , urlVal, false, "http://a.com:-1");  
        isValid( 0 , urlVal, false, "http://a.com:65536");
        isValid( 0 , urlVal, false, "http://a.com:8888888");
        isValid( 0 , urlVal, false, "http://a.com:aa");
        isValid( 0 , urlVal, false, "http://a.com:com");

        for(int k=0; k<test_Port.length; k++){
            isValid(0 , urlVal, ( test_Port[k][1] == T) , "http://aaa.com"+test_Port[k][0]);
        }

    }
       
    //======================================================================
    public void  FourthPartition_Test(){
        //You can use this function to implement your Fourth Partition testing      
        System.out.println("\n****************************************");
        System.out.println("   Test 2-4.   FourthPartition Path Testing ");
        System.out.println("****************************************");

        UrlValidator urlVal = new UrlValidator();
   
        isValid(4 , urlVal, true  , "/test1");  
        isValid(4 , urlVal, true  , "/test1/" );  
        isValid(4 , urlVal, true  , "/a/path..file" ); 
        isValid(4 , urlVal, true  , "/a/file/." );     
        isValid(4 , urlVal, true  , "/.../file" );   
        isValid(4 , urlVal, true  , "/..." );  
        
        isValid(4 , urlVal, false  , "/.." );
        isValid(4 , urlVal, false  , "/../" );  
        isValid(4 , urlVal, false  , "/.//." );  

        isValid(4 , urlVal, false  , "///" );     

        for(int k=0; k<test_Path.length; k++){
            isValid( 4 , urlVal, ( test_Path[k][1] == T) , test_Path[k][0]);
        }

    }
    //======================================================================
    public void  FifthPartition_Test(){
        //You can use this function to implement your Fifth Partition testing       
        System.out.println("\n****************************************");
        System.out.println("   Test 2-5.   FifthPartition Query Testing ");
        System.out.println("****************************************");

        UrlValidator urlVal = new UrlValidator();

        isValid(5 , urlVal, true  , "?action=view" );  
        isValid(5 , urlVal, true  , "?action=view" );  
        isValid(5 , urlVal, true  , "?action.view" ); 
        isValid(5 , urlVal, true  , "?" );     
        isValid(5 , urlVal, true  , "?#~'" );           
        
        isValid(5 , urlVal, false  , "? action=view" );    //space not allowed
        isValid(5 , urlVal, false  , "?action =view" );  
        isValid(5 , urlVal, false  , "?action= view" );  
        isValid(5 , urlVal, false  , "?action= view " );  
        isValid(5 , urlVal, false  , "?action view" );     
        
        for(int k=0; k<test_Query.length; k++){
             isValid( 5 , urlVal, ( test_Query[k][1] == T) , test_Query[k][0]);
        }

    }

    //======================================================================
   
    public void Programming_Based_Test() {
           
        System.out.println("\n****************************************");
        System.out.println("   Test 3.      Programming Based Testing ");
        System.out.println("****************************************");

        UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
          
        //--------------------------------------------------------------------------------
        System.out.println("   Test 3-1.   FirstPartition Scheme Loop Testing ");

        String url = "";
        String test_char = "";
          
        // The first letter of the Scheme can be uppercase or lowercase
        String  scheme1= "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";               //Scheme First letter
        String  scheme2= "+-.0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";  //Scheme Middle and last letter


        for (int k=32; k<127; k++){
            test_char = ""+(char)k;
              
            url=test_char+"http://abc.com";             // The first letter of the Scheme test
            isValid(0 , urlVal, (scheme1.contains(test_char) ) , url ); 
              
            url="h"+test_char+"ttp://abc.com";          // Intermediate character of Scheme test
            isValid(0 , urlVal, (scheme2.contains(test_char) ) , url ); 
              
            url="htt"+test_char+"://abc.com";           // The last character of the Scheme test
            isValid(0 , urlVal, (scheme2.contains(test_char) ) , url ); 
        }
          
        //--------------------------------------------------------------------------------
        System.out.println("   Test 3-2.   SecondPartition  Authority  Loop Testing ");

        // The first letter of the Authority is a number or alphabet
        String  authority1= "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";     //Authority First letter and last letter
        String  authority2= "-.0123456789@ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";  //Authority Middle 

        for (int k=32; k<127; k++){
            test_char = ""+(char)k;
              
            url="http://"+test_char+"abc.com";          // The first letter of the Authority test
            isValid(0 , urlVal, (authority1.contains(test_char) ) , url ); 
              
            url="http://a"+test_char+"bc.com";          // Intermediate character of Authority test
            isValid(0 , urlVal, (authority2.contains(test_char) ) , url ); 
              
            url="http://abc"+test_char+".com";          // The last character of the Authority test
            isValid(0 , urlVal, (authority1.contains(test_char) ) , url ); 
                 
        }

        //--------------------------------------------------------------------------------          
        String[] authority_true  =  { "com", "org", "net", "edu", "gov", "us", "uk", "cn", "kr", "jp", "ru"};
        String[] authority_false =  { "com1", "org2", "net3", "edu4", "gov5", "org6","us7", "uk8", "cn9", "kra", "jpb", "ru2"};
                   
        for(int k=0; k<authority_true.length; k++){
            isValid( 2 , urlVal, true, "google."+authority_true[k]);
        }
             
        for(int k=0; k<authority_false.length; k++){
            isValid( 2 , urlVal, false, "google."+authority_false[k]);
        }      
          
        //--------------------------------------------------------------------------------

        for(int k=-10; k<260; k++){
            isValid( 2 , urlVal, (k>=0 && k<256),  Integer.toString(k)+".1.1.1");       // x.1.1.1
            isValid( 2 , urlVal, (k>=0 && k<256),  "1."+Integer.toString(k)+".1.1");    // 1.x.1.1
            isValid( 2 , urlVal, (k>=0 && k<256),  "1.1."+Integer.toString(k)+".1");    // 1.1.x.1
            isValid( 2 , urlVal, (k>=0 && k<256),  "1.1.1."+Integer.toString(k)+"");    // 1.1.1.x
        }
 
        //--------------------------------------------------------------------------------
        
        System.out.println("   Test 3-3.   ThirdPartition Port Number  Loop Testing ");
        
        urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);
        for (int k=-10; k<10; k++){
            test_char =Integer.toString(k);
            url="http://abc.com:"+test_char;            // Port Number test   0~65535 
            isValid(0 , urlVal, (k>=0 && k<65536) , url ); 
        }
        
        for (int k=65530; k<65550; k++){
            test_char =Integer.toString(k);
            url="http://abc.com:"+test_char;            // Port Number test   0~65535 
            isValid(0 , urlVal, (k>=0 && k<65536) , url ); 
        }
        
        for (int k=-1; k<100; k++){
            test_char =Integer.toString(k);
            url="https://abc.com:"+test_char;            // Port Number test   0~65535 
            isValid(0 , urlVal, (k>=0 && k<65536) , url ); 
        }
        
        for (int k=-1; k<100; k++){
            test_char =Integer.toString(k);
            url="ftp://abc.com:"+test_char;            // Port Number test   0~65535 
            isValid(0 , urlVal, (k>=0 && k<65536) , url ); 
        }

        //--------------------------------------------------------------------------------
        System.out.println("   Test 3-4.   FourthPartition Path  Loop Testing ");

        String  path1= "!#$%&'()*+,-.0123456789:;=?@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz~";   //Authority First letter 
        String  path2= "!#$%&'()*+,-./0123456789:;=?@ABCDEFGHIJKLMNOPQRSTUVWXYZ_abcdefghijklmnopqrstuvwxyz~";  //Authority Middle and last letter

          
        for (int k=32; k<127; k++){
            test_char = ""+(char)k;
              
            url="http://abc.com/"+test_char+"file";          // The first letter of the Path test
            isValid(0 , urlVal, (path1.contains(test_char) ) , url ); 
              
            url="http://abc.com/test"+test_char+"file";     // Intermediate character of Path test 
            isValid(0 , urlVal, (path2.contains(test_char) ) , url ); 
             
            url="http://abc.com/test"+test_char+"";         // The last character of the Path test 
            isValid(0 , urlVal, (path2.contains(test_char) ) , url ); 
              
        }
          
        //--------------------------------------------------------------------------------
        System.out.println("   Test 3-5.   FifthPartition  Query  Loop Testing ");

        for (int k=33; k<127; k++){
            test_char = ""+(char)k;
              
            url="?"+test_char+"action=view";                    // The first letter of the Query test
            isValid(5 , urlVal, true , url ); 
              
            url="http://abc.com/?act"+test_char+"view";      // Intermediate character of Query test
            isValid(5 , urlVal,true  , url ); 
              
            url="http://abc.com/?action=view"+test_char+"";  // The last character of the Query test
            isValid(5 , urlVal, true  , url ); 
        }
    
    }

    //======================================================================
  
    public void Url_LoopTest() {
        
        System.out.println("\n****************************************");
        System.out.println("   Test 4.      URL Loop Testing ");
        System.out.println("****************************************");
        UrlValidator urlVal = new UrlValidator(UrlValidator.ALLOW_ALL_SCHEMES);

        for(int k1=0; k1<test_Scheme.length;   k1++){
            for(int k2=0; k2<test_Authority.length;k2++){
                for(int k3=0; k3<test_Port.length;     k3++){
                    for(int k4=0; k4<test_Path.length;     k4++){
                        for(int k5=0; k5<test_Query.length; k5++){
                         
                            boolean prediction=(test_Scheme[k1][1] == T) & (test_Authority[k2][1] == T) & (test_Port[k3][1] == T) &
                                               (test_Path[k4][1] == T) & (test_Query[k5][1] == T);
                            
                            String url = test_Scheme[k1][0]+test_Authority[k2][0]+test_Port[k3][0]+test_Path[k4][0]+test_Query[k5][0];
                            isValid(0 , urlVal, prediction ,  url); 
                             
                        }
                         
                    }
                }
            }
        } // for k1

        System.out.println("------------------------------------------------------------------------------------------------------------------");

    }


    //======================================================================

    String[][] test_Scheme = {
        {"http://" , T}, 
        {"ftp://"  , T}, 
        {"h2o://"  , T}, 
        {"3ht://"  , F}, 
        {"http:/"  , F},         
        {"http:"   , F}, 
        {"http/"   , F},     
        {"://"     , F} };

   String[][] test_Authority = {
        {"www.aa.com", T}, 
        {"aa.com."   , T},  
        {"abc.com"   , T},  
        {"abc.kr"    , T},
        {"256.com"   , T},        
  
        {"aaa.a"     , F},
        {"aaa.aa"    , F},
        {"aaa.h2o"   , F},
        {"aaa."      , F},  
        {".aaa"      , F},  
        {"aaa"       , F},  
        
        {"0.0.0.0"   , T},  
        {"255.255.255.255", T}, 
        {"256.256.256.256", F},
        
        {"-1.0.0.0"  , F},       
        {"1.2.3.4.5" , F},
        {"1.2.3.4."  , F},
        {"1.2.3"     , F},  
        {".1.2.3.4"  , F},
        {""          , F} };  
 
   
    String[][] test_Port = {
        {":80"     , T},
        {":8080"   , T},
        {":0"      , T},
        {":65535"  , T}, 
        {""        , T},  

        {":-1"     , F},
        {":65636"  , F},
        {":9999999", F},
        {":com"    , F},
        {":21a"    , F}};
 

    
    String[][] test_Path = {
        {"/test1"     , T},
        {"/test1/"    , T},
        {"/t123"      , T},
        {"/$23"       , T},
        {"/a/"        , T},
        {"/a/file~"   , T},
        {"/a/file/."  , T},
        {"/path..file", T},
        {"/..file"    , T},
        {"/.../file"  , T},
        {""           , T},
        {"/.."        , F},  
        {"/../"       , F},
        {"/././.."    , F},
        {"/..."       , T},
        {"/.../.."    , T},

        };

    String[][] test_Query = {
        {"?a=up"       , T},
        {"?b=down&c=on", T},
        {"?"           , T},
        {""            , T},
        {"? a=up"      , F} };   
 

}
