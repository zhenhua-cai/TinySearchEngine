package scraping;

public class AnalyzeString {
    /**
     * get the paragraph that contains the keyword in the str.
     * @param str the str to be analyzed
     * @param keyword the keyword
     * @return the final paragraph that contains the keyword.
     */
    public static String getPara(String str,String keyword){


    	String hold = "";
        
       hold = test1(str, keyword);
        
        return hold;
    }
    
    public static String test1(String str, String keyword)
    {
    	
    	String hold1 = "";
    	
    	int stringHead, stringTail;

        if(!str.contains(keyword))

            hold1 = null;

        else
        {
        	
        	stringHead = str.indexOf(keyword);
        	
        	System.out.println(stringHead+"@@");
        	
        	
        	stringTail = stringHead + keyword.length();
        	
        	System.out.println(stringTail+"##");
        	
        	System.out.println(str.length()+"$$");
        	
        	
        	if(stringHead==0)
        	{
        		
        		hold1 = str.substring(stringHead, stringTail+20);
        		
        		System.out.println("head shows");
        		
        	}
        	
        	if(stringTail == str.length())
        	{
        		
        		hold1 = str.substring(stringHead-20);// need correct;
        		
        		System.out.println("tail shows");
        	
        	}
        	
        	if(stringHead != 0 && stringTail!=str.length())
        	{
        		
        		hold1 = str.substring(stringHead-10, stringTail+10);
        		
        	}
        	
        	
        		
        }
        
        return hold1;
    }
}
