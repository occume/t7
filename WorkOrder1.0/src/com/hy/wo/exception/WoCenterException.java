package com.hy.wo.exception;

public class WoCenterException extends Exception{

	private static final long serialVersionUID = -7400485598759868647L;
	/** 异常状态代码* */
    private String            state;
    /** 异常状态描述* */
    private String           stateDesc;
    private String msg;
    
    public WoCenterException(){}
    
    public WoCenterException(String state) {
    	this(state,"");
    	this.msg=state;
    }

    public WoCenterException(String state, String stateDesc) {
    	super(stateDesc);
        this.stateDesc = stateDesc;
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public String getStateDesc() {
		return stateDesc;
	}

	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setState(String state) {
        this.state = state;
    }
}
