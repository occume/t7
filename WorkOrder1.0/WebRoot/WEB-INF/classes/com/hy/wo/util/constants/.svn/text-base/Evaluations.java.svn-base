package com.hy.wo.util.constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 服务质量评价
 * @author dong_jin
 *
 */
public enum Evaluations {
	WEL{
		public String getName() {
			return "满意";
		}
	},
	GEN{
		public String getName() {
			return "一般";
		}
	},
	RES{
		public String getName() {
			return "对处理结果不满意";
		}
	},
	ATI{
		public String getName() {
			return "对服务态度不满意";
		}
	}
	;
	public static Evaluations getEvaByName(String name){
		for(Evaluations evals:Evaluations.values()){
			if(evals.name().equals(name))
				return evals;
		}
		return null;
	}
	public abstract String getName();
	public static String getCHName(String n){
		for(Evaluations evals:Evaluations.values()){
			if(evals.name().equals(n))
				return evals.getName();
		}
		return null;
	}
	public static List<String> getValues(){
		List<String> list=new ArrayList<String>();
		for(Evaluations evals:Evaluations.values()){
			list.add(evals.getName());
		}
		return list;
	} 
}
