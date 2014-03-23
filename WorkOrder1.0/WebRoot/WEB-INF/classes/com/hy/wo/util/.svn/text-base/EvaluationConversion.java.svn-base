package com.hy.wo.util;

import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;

import com.hy.wo.util.constants.Evaluations;

public class EvaluationConversion extends StrutsTypeConverter {

		@SuppressWarnings("unchecked")
		@Override
		public Object convertFromString(Map arg0, String[] arg1, Class arg2) {
			Evaluations[] evals = Evaluations.values();
			for (Evaluations eval : evals) {
				if(eval.getName().equals(arg1[0].trim())){
					return eval;
				}
			}
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public String convertToString(Map arg0, Object arg1) {
			Evaluations evals = (Evaluations) arg1;
			return evals.getName();
		}


}


