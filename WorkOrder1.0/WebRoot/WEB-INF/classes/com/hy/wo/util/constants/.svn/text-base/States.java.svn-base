package com.hy.wo.util.constants;

import java.util.ArrayList;
import java.util.List;
/**
 * 工单处理状态
 * @author dong_jin
 *
 */
public enum States {
	//DEAL,DEALING,DEALED,REPLIED,NOTCOMPLETE;
	UNDEAL{
		public String getName(){
			return "未处理";
		}
	},
	DELING{
		public String getName(){
			return "处理中";
		}
	},
	DEALED{
		public String getName(){
			return "处理完毕";
		}
	},
	REPED{
		public String getName(){
			return "已回复";
		}
	},
	UNCOMP{
		public String getName(){
			return "待补充资料";
		}
	};
	
	
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list=new ArrayList<String>();
		for(States sex:States.values()){
			list.add(sex.getName());
		}
		return list;
	} 
}
