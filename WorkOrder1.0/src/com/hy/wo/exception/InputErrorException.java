package com.hy.wo.exception;

/**
 * 系统对用户输入反馈不正确报告 InputErrorException
 * 
 * @author 赵卫东
 * @version 创建时间：2009-8-25 下午01:22:02
 */

public class InputErrorException extends Exception {

    private static final long serialVersionUID = -2250268284732290632L;

    public InputErrorException() {
        super();
    }

    private String messages;

    public String getMessages() {
        return this.messages;
    }

    public InputErrorException(String smg) {
        super(smg);
    }

    public InputErrorException(String smg, String messages) {
        super(smg);
        this.messages = messages;
    }
}
