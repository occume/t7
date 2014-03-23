package com.hy.wo.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts2.util.StrutsTypeConverter;
import com.hy.wo.util.constants.States;

public class DateConversion extends StrutsTypeConverter {

	private static String DEFAULT_DATE_TIME_FORMART = "yyyy-MM-dd HH:mm:ss";  
    
    private static String DATE_TIME_FORMART_IE = "yyyy-MM-dd HH:mm:ss";  
      
    private static String DATE_TIME_FORMART_FF = "yy-MM-dd";  
  
    @Override  
    public Object convertFromString(Map context,   
            String[] values, Class toClass) {  
          
        Date date = null;  
        String dateString = null;  
          
        if (values != null && values.length > 0) {  
            dateString = values[0];  
              
            if (dateString != null) {  
                SimpleDateFormat format = new SimpleDateFormat(DATE_TIME_FORMART_IE);  
                try {  
                    date = format.parse(dateString);  
                }catch(ParseException e){  
                    date = null;  
                }  
                  
                if (date == null){  
                    format = new SimpleDateFormat(DATE_TIME_FORMART_FF);  
                    try {  
                        date = format.parse(dateString);  
                    }catch(ParseException e){  
                        date = null;  
                    }  
                }  
            }  
        }  
          
        return date;  
    }  
  
    @Override  
    public String convertToString(Map context, Object o) {  
          
        Date date = (Date) o;  
        String dateTimeString = DateFormatUtils.format(date, DEFAULT_DATE_TIME_FORMART);  
        return dateTimeString;  
    }  


}


