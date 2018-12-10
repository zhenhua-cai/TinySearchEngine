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
    	
    	int cutlength = 2;
    	
    	int stringHead, stringTail, dotcount = 0;

        if(!str.contains(keyword))

            return null;

        else
        {
        	
        	stringHead = str.indexOf(keyword);
        	
        	
        	stringTail = stringHead + keyword.length();
        	
        	
        	if(stringHead==0)
        	{
        		for(int i = stringTail; i<str.length();i++)
        		{
        			if(str.charAt(i) == '.')
        			{
        				dotcount++;
        				
        				if(dotcount == cutlength)hold1 = str.substring(stringHead, i);
        			}
        			
        		}
        	}
        	
        	if(stringTail == str.length() - 1)
        	{
        		
        		for(int i = stringHead; i > 0; i--)
        		{
        			if(str.charAt(i) == '.')
        			{
        				dotcount++;
        				
        				if(dotcount == cutlength)
        					
        				hold1 = str.substring(i + 1);
        				
        			}
        		}
        	
        	}
        	
        	if(stringHead != 0 && stringTail!=str.length()-1)
        	{
        		int spotHead = 0, spotTail = 0;
        		
        		for( int i = stringHead; i > 0; i--){
        			
        			if(str.charAt(i) == '.') 
        			{
        				
        				dotcount++;
        				
        				if(dotcount == cutlength - 1)
        				
        				spotHead = i;
        				
        				break;
        			}
        		}
        		
        		for( int i = stringTail; i < str.length(); i++)
        		{
        			if(str.charAt(i) == '.')
        			{
        				
        				dotcount++;
        				
        				if(dotcount == cutlength)
        					
        				spotTail = i;
        			}
        		}
        		
        		hold1 = str.substring(spotHead+1, spotTail+1);
        		
        	}	
        		
        }
        
        return hold1;
    }
}
