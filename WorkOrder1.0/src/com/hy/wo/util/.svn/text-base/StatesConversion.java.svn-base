package com.hy.wo.util;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;
import com.hy.wo.util.constants.States;

public class StatesConversion extends StrutsTypeConverter {

		@SuppressWarnings("unchecked")
		@Override
		public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
			States[] states = States.values();
			for (States sex : states) {
				if(sex.getName().equals(arg1[0])){
					return sex;
				}
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String convertToString(Map arg0, Object arg1) {
			States states = (States) arg1;
			return states.getName();
		}


}


